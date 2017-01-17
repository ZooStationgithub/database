package nl.zoostation.database.tools;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author valentinnastasi
 */
public final class MockUtils {

    private MockUtils() {
    }

    public static SecurityContext mockSecurityContext(String userName) {
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        Authentication mockAuthentication = mock(Authentication.class);
        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
        when(mockAuthentication.getName()).thenReturn(userName);
        return mockSecurityContext;
    }

}
