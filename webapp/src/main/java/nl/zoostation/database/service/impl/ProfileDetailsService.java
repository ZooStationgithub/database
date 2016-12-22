package nl.zoostation.database.service.impl;

import nl.zoostation.database.annotations.NotNull;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
import nl.zoostation.database.exception.ObjectDescriptor;
import nl.zoostation.database.exception.ObjectNotFoundException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Profile;
import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.IProfileDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author valentinnastasi
 */
public class ProfileDetailsService extends TransactionAwareService implements IProfileDetailsService {

    @Value("${mail.server.app.box}")
    private String appMailBox;

    @Value("${mail.server.developers.box}")
    private String developersMailBox;

    private final IGenericReadOnlyEntityDAO<ProfileView, Long> profileDetailsViewDAO;
    private final IGenericEntityDAO<Profile, Long> profileDAO;
    private final IMailService mailService;

    public ProfileDetailsService(
            IGenericReadOnlyEntityDAO<ProfileView, Long> profileDetailsViewDAO,
            IGenericEntityDAO<Profile, Long> profileDAO,
            IMailService mailService) {
        this.profileDetailsViewDAO = profileDetailsViewDAO;
        this.profileDAO = profileDAO;
        this.mailService = mailService;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileView getDetails(@NotNull Long id) {
        return profileDetailsViewDAO.findOne(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectDescriptor.ofName(ProfileView.class).with("ID", id)));
    }

    @Transactional(readOnly = true)
    @Override
    public void requestMoreInfo(@NotNull Long id) {
        Profile profile = profileDAO.findOne(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectDescriptor.ofName(Profile.class).with("ID", id)));

        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String companyEmail = principal.getName();

        EmailDescription[] emailDescriptions = Stream.of(
                new EmailDescription.Builder()
                        .setFrom(appMailBox)
                        .addTo(developersMailBox)
                        .setSubject("Developer info")
                        .setTemplateName("moreInfo.ftl")
                        .addModelEntry("zsNumber", profile.getZoostationNumber())
                        .addModelEntry("companyEmail", companyEmail)
                        .build(),
                new EmailDescription.Builder()
                        .setFrom(developersMailBox)
                        .addTo(companyEmail)
                        .setSubject("Developer info")
                        .setTemplateName("moreInfoConfirmation.ftl")
                        .build()
        ).toArray(EmailDescription[]::new);

        doAfterCommit(() -> mailService.sendMailAsync(emailDescriptions));
    }
}
