package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
import nl.zoostation.database.exception.ObjectNotFoundException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import nl.zoostation.database.model.domain.Profile;
import nl.zoostation.database.model.view.ProfileView;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static nl.zoostation.database.tools.MockUtils.mockSecurityContext;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author valentinnastasi
 */
public class ProfileDetailsServiceTest extends BaseServiceTest {

    private static final long ID = 1L;
    private static final String ZS_NUMBER = "xtcv";
    private static final String USER = "a@a.a";

    @Autowired
    private IGenericReadOnlyEntityDAO<ProfileView, Long> profileDetailsViewDAO;

    @Autowired
    private IGenericEntityDAO<Profile, Long> profileDAO;

    @Autowired
    private IMailService mailService;

    @Autowired
    private ProfileDetailsService profileDetailsService;

    @SuppressWarnings("unchecked")

    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.setContext(mockSecurityContext(USER));
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(profileDetailsViewDAO, profileDAO, mailService);
        SecurityContextHolder.clearContext();
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testGetDetailsNotFound() throws Exception {
        when(profileDetailsViewDAO.findOne(ID)).thenReturn(Optional.empty());

        profileDetailsService.getDetails(ID);
    }

    @Test
    public void testGetDetails() throws Exception {
        ProfileView profileView = createProfileView();

        when(profileDetailsViewDAO.findOne(ID)).thenReturn(Optional.of(profileView));

        ProfileView result = profileDetailsService.getDetails(ID);
        assertThat(result).isNotNull().isSameAs(profileView);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testRequestMoreInfoProfileNotFound() throws Exception {
        when(profileDetailsViewDAO.findOne(ID)).thenReturn(Optional.empty());

        profileDetailsService.requestMoreInfo(ID);
    }

    @Test
    public void testRequestMoreInfo() throws Exception {
        when(profileDAO.findOne(ID)).thenReturn(Optional.of(createProfile()));

        profileDetailsService.requestMoreInfo(ID);

        ArgumentCaptor<EmailDescription> argumentCaptor = ArgumentCaptor.forClass(EmailDescription.class);
        verify(mailService).sendMailAsync(argumentCaptor.capture());

        List<EmailDescription> emailDescriptions = argumentCaptor.getAllValues();
        assertThat(emailDescriptions).isNotNull().hasSize(2);
    }

    private ProfileView createProfileView() {
        ProfileView profileView = new ProfileView();
        profileView.setId(ID);
        profileView.setZoostationNumber(ZS_NUMBER);
        return profileView;
    }

    private Profile createProfile() {
        Profile profile = new Profile();
        profile.setId(ID);
        profile.setZoostationNumber(ZS_NUMBER);
        return profile;
    }
}