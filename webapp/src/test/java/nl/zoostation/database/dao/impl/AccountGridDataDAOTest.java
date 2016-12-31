package nl.zoostation.database.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author val
 */
@DatabaseSetup({"/datasets/common/account_groups.xml", "/datasets/common/accounts.xml"})
public class AccountGridDataDAOTest extends BaseDAOTest {

    private static final String ONLINE_USER = "online@online.online";

    @Autowired
    private AccountGridDataDAO accountGridDataDAO;

    @Before
    public void setUp() throws Exception {
        setSecurityContextHolder();
    }

    @After
    public void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
    }

    @Test
    public void testGetRows() throws Exception {
        List<AccountGridRow> rows = accountGridDataDAO.getRows(new GridViewInputSpec());
        assertThat(rows).isNotNull().hasSize(5).extracting("login", "group", "creationDate", "activated", "online")
                .contains(tuple("a@b.c", "Administrator", toInstant("2016-12-31 12:30:10.000"), false, false),
                        tuple("d@e.f", "Administrator", toInstant("2016-12-31 12:20:10.000"), true, false),
                        tuple("g@h.i", "Company", toInstant("2016-12-31 12:10:10.000"), true, false),
                        tuple("g@k.l", "ZooStation", toInstant("2016-12-31 12:40:10.000"), false, false),
                        tuple("online@online.online", "Company", toInstant("2016-12-31 12:50:10.000"), false, true));
    }

    @Test
    public void testCount() throws Exception {
        Long result = accountGridDataDAO.count(new GridViewInputSpec(), false);
        assertThat(result).isEqualTo(5);
    }

    @Test
    public void testCountWithFilter() throws Exception {
        Long result = accountGridDataDAO.count(new GridViewInputSpec(), true);
        assertThat(result).isEqualTo(5);
    }

    private void setSecurityContextHolder() {
        SecurityContext mockSecurityContext = mock(SecurityContext.class);
        Authentication mockAuthentication = mock(Authentication.class);
        when(mockSecurityContext.getAuthentication()).thenReturn(mockAuthentication);
        when(mockAuthentication.getName()).thenReturn(ONLINE_USER);
        SecurityContextHolder.setContext(mockSecurityContext);
    }

    private Instant toInstant(String s) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        return localDateTime.toInstant(zonedDateTime.getOffset());
    }
}