package nl.zoostation.database.service.impl;

import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
import nl.zoostation.database.exception.ObjectDescriptor;
import nl.zoostation.database.exception.ObjectNotFoundException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Profile;
import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.IProfileDetailsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

/**
 * @author valentinnastasi
 */
public class ProfileDetailsService extends TransactionAwareService implements IProfileDetailsService {

    private static final Logger logger = LogManager.getLogger(ProfileDetailsService.class);

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
        logger.debug("Getting details for profile with ID {}", id);
        ProfileView profileView = profileDetailsViewDAO.findOne(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectDescriptor.ofName(ProfileView.class).with("ID", id)));
        logger.trace("profile details found: {}", profileView);
        return profileView;
    }

    @Transactional(readOnly = true)
    @Override
    public void requestMoreInfo(@NotNull Long id) {
        logger.debug("Requesting more info for profile with ID {}", id);
        Profile profile = profileDAO.findOne(id)
                .orElseThrow(() -> new ObjectNotFoundException(ObjectDescriptor.ofName(Profile.class).with("ID", id)));

        String companyEmail = SecurityContextHolder.getContext().getAuthentication().getName();

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
