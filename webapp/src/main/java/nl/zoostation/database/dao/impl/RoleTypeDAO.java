package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IRoleTypeDAO;
import nl.zoostation.database.model.domain.RoleType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class RoleTypeDAO extends GenericDAO<RoleType, Long> implements IRoleTypeDAO {

    @Autowired
    public RoleTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<RoleType> getEntityType() {
        return RoleType.class;
    }
}
