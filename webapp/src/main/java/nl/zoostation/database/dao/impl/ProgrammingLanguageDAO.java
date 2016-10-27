package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IProgrammingLanguageDAO;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class ProgrammingLanguageDAO extends GenericDAO<ProgrammingLanguage, Long> implements IProgrammingLanguageDAO {

    @Autowired
    public ProgrammingLanguageDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<ProgrammingLanguage> getEntityType() {
        return ProgrammingLanguage.class;
    }
}
