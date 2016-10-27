package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.ICountryDAO;
import nl.zoostation.database.model.domain.Country;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class CountryDAO extends GenericDAO<Country, Long> implements ICountryDAO {

    @Autowired
    public CountryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Country> getEntityType() {
        return Country.class;
    }
}
