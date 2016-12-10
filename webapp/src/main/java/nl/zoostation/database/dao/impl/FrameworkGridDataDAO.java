package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.FrameworkGridRow;
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
public class FrameworkGridDataDAO extends SessionAwareDAO implements IGridDataDAO<FrameworkGridRow> {

    private static final String SELECT_QUERY =
            "select new nl.zoostation.database.model.grid.FrameworkGridRow(f.id, f.name, pl.name) " +
                    "from Framework f inner join f.programmingLanguage pl";

    private static final String COUNT_QUERY =
            "select count(distinct f.id) from Framework f";

    @Autowired
    public FrameworkGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<FrameworkGridRow> getRows(GridViewInputSpec gridViewInputSpec) {
        return getSession().createQuery(SELECT_QUERY, FrameworkGridRow.class).list();
    }

    @Override
    public Long count(GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        return getSession().createQuery(COUNT_QUERY, Long.class).uniqueResult();
    }
}
