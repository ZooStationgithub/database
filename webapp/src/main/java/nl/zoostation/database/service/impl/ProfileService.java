package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IProfileDAO;
import nl.zoostation.database.dao.IProfileViewDAO;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Profile;
import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.IProfileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author valentinnastasi
 */
@Service
public class ProfileService extends TransactionAwareService implements IProfileService {

    private static final Logger logger = LogManager.getLogger(ProfileService.class);

    @Value("${mail.server.app.box}") private String appMailBox;
    @Value("${mail.server.developers.box}") private String developersMailBox;

    private final IProfileDAO profileDAO;
    private final IProfileViewDAO profileViewDAO;
    private final IMailService mailService;

    @Autowired
    public ProfileService(IProfileDAO profileDAO, IProfileViewDAO profileViewDAO, IMailService mailService) {
        this.profileDAO = profileDAO;
        this.profileViewDAO = profileViewDAO;
        this.mailService = mailService;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileView findById(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        logger.debug("Finding profile with id {}", id);
        Optional<ProfileView> profileView = profileViewDAO.findById(id);
        if (!profileView.isPresent()) {
            throw new IllegalStateException("profile with id " + id + " does not exist");
        }
        return profileView.get();
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileView findByZsNumber(String zsNumber) {
        if (StringUtils.isEmpty(zsNumber)) {
            throw new IllegalArgumentException("Parameter 'zsNumber' is required");
        }
        logger.debug("Finding profile with ZS number {}", zsNumber);

        Optional<ProfileView> profileView = profileViewDAO.findByZSNumber(zsNumber);
        if (!profileView.isPresent()) {
            throw new IllegalStateException("Profile with ZS number " + zsNumber + " does not exist");
        }

        return profileView.get();
    }

    @Transactional(readOnly = true)
    @Override
    public void requestMoreInfo(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        logger.debug("Requesting developer info (id: {})", id);

        Optional<Profile> profile = profileDAO.findOne(id);
        if (!profile.isPresent()) {
            throw new IllegalStateException("Profile with id " + id + " does not exist");
        }

        // TODO use SecurityContextHolder when security is implemented!!!
        //Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String companyEmail = "valentin.nastasi@zoostation.nl"; //principal.getName();

        EmailDescription[] emailDescriptions = Stream.of(
                new EmailDescription.Builder()
                        .setFrom(appMailBox)
                        .addTo(developersMailBox)
                        .setSubject("Developer info")
                        .setTemplateName("moreInfo.ftl")
                        .addModelEntry("zsNumber", profile.get().getZoostationNumber())
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
