package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author valentinnastasi
 */
@SuppressWarnings("JpaQlInspection")
@Repository
public class ContractTypeGridDataDAO extends SessionAwareDAO implements IGridDataDAO<IdNameGridRow> {

    private static final String SELECT_QUERY =
            "select new nl.zoostation.database.model.grid.IdNameGridRow(c.id, c.name) from ContractType c";

    private static final String COUNT_QUERY =
            "select count(distinct c.id) from ContractType c";

    @Autowired
    public ContractTypeGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<IdNameGridRow> getRows(GridViewInputSpec gridViewInputSpec) {
        return getSession().createQuery(SELECT_QUERY, IdNameGridRow.class).list();
    }

    @Override
    public Long count(GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        return getSession().createQuery(COUNT_QUERY, Long.class).uniqueResult();
    }

}
