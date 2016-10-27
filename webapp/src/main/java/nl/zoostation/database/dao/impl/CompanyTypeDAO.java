package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.ICompanyTypeDAO;
import nl.zoostation.database.model.domain.CompanyType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class CompanyTypeDAO extends GenericDAO<CompanyType, Long> implements ICompanyTypeDAO {

    @Autowired
    public CompanyTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<CompanyType> getEntityType() {
        return CompanyType.class;
    }
}
