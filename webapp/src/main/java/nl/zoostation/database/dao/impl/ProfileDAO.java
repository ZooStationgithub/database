package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.NotNull;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.Profile;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;

/**
 * @author valentinnastasi
 */
public class ProfileDAO extends SimpleGenericEntityDAO<Profile, Long> implements IGenericEntityDAO<Profile, Long> {

    public ProfileDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Profile.class, Long.class);
    }

    @Override
    public void delete(@NotNull Profile entity) {
        getSession().refresh(entity, LockMode.PESSIMISTIC_WRITE);
        entity.getKnownFrameworks().clear();
        entity.getCustomFields().clear();
        entity.getPreferredCompanyTypes().clear();
        entity.getPreferredCountries().clear();
        super.delete(entity);
    }

}
