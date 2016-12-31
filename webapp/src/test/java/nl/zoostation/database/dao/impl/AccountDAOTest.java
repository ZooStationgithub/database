package nl.zoostation.database.dao.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.model.domain.Account;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author val
 */
@DatabaseSetup({"/datasets/common/account_groups.xml", "/datasets/common/accounts.xml"})
public class AccountDAOTest extends BaseDAOTest {

    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void testFindByLogin() throws Exception {
        Optional<Account> accountOptional = accountDAO.findByLogin("online@online.online");
        assertThat(accountOptional).isNotEmpty();
    }

}