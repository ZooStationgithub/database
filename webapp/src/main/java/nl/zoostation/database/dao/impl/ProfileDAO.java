package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IProfileDAO;
import nl.zoostation.database.model.domain.Profile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository("profileDAO")
public class ProfileDAO extends HibernateGenericDAO<Profile, Long> implements IProfileDAO {

    public ProfileDAO(@Autowired SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Profile> getEntityType() {
        return Profile.class;
    }

}