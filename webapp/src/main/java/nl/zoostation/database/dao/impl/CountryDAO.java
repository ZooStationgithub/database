package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.ICountryDAO;
import nl.zoostation.database.model.domain.Country;
import nl.zoostation.database.model.input.SearchToken;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author valentinnastasi
 */
@Repository
public class CountryDAO extends GenericDAO<Country, Long> implements ICountryDAO {

    private static final String QUERY_TOKEN =
            "select new nl.zoostation.database.model.input.SearchToken(c.id, c.name) " +
                    "from Country c " +
                    "where lower(c.name) like '%' || lower(:" + PARAM_TERM + ") || '%'";

    @Autowired
    public CountryDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Country> getEntityType() {
        return Country.class;
    }

    @Override
    public List<? extends SearchToken> findTokens(String term, Map<String, Object> extras) {
        return getSession().createQuery(QUERY_TOKEN, SearchToken.class).setParameter(PARAM_TERM, term).list();
    }
}
