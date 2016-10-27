package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IContractTypeDAO;
import nl.zoostation.database.model.domain.ContractType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author valentinnastasi
 */
@Repository
public class ContractTypeDAO extends GenericDAO<ContractType, Long> implements IContractTypeDAO {

    @Autowired
    public ContractTypeDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<ContractType> getEntityType() {
        return ContractType.class;
    }
}
