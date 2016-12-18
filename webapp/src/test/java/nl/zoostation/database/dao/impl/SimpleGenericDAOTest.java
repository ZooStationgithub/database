package nl.zoostation.database.dao.impl;

import nl.zoostation.database.model.domain.Profile;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

/**
 * @author valentinnastasi
 */
@RunWith(MockitoJUnitRunner.Silent.class)
public class SimpleGenericDAOTest {

    @Test
    public void testTypeResolver() throws Exception {
        SimpleGenericEntityDAO<Profile, Long> p = new SimpleGenericEntityDAO<>(mock(SessionFactory.class));
        Class<Profile> entityType = p.getEntityType();

    }
}