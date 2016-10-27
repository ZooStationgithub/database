package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IRankTypeDAO;
import nl.zoostation.database.model.domain.RankType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class RankTypeDAO extends GenericDAO<RankType, Long> implements IRankTypeDAO {

    @Autowired
    public RankTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<RankType> getEntityType() {
        return RankType.class;
    }
}
