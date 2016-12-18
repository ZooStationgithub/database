package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.BaseDAOTest;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.Account;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author valentinnastasi
 */
@Ignore
public class AccountDAOTest extends BaseDAOTest{

    @Autowired
    private IGenericEntityDAO<Account, Long> accountDAO;

    @Test
    public void testFindAll() throws Exception {
        List<Account> list = accountDAO.findAll();
        System.out.println(list);
    }
}