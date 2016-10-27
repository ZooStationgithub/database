package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IProfileDAO;
import nl.zoostation.database.model.domain.Profile;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class ProfileDAO extends GenericDAO<Profile, Long> implements IProfileDAO {

    @Autowired
    public ProfileDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Profile entity) {
        getSession().refresh(entity, LockMode.PESSIMISTIC_WRITE);
        entity.getKnownFrameworks().clear();
        entity.getCustomFields().clear();
        entity.getPreferredCompanyTypes().clear();
        entity.getPreferredCountries().clear();
        super.delete(entity);
    }

    @Override
    protected Class<Profile> getEntityType() {
        return Profile.class;
    }

}
