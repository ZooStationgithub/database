package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IFrameworkDAO;
import nl.zoostation.database.model.domain.Framework;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class FrameworkDAO extends GenericDAO<Framework, Long> implements IFrameworkDAO {

    @Autowired
    public FrameworkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Framework> getEntityType() {
        return Framework.class;
    }
}
