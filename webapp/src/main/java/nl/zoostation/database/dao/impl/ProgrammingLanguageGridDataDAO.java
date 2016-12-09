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
@Repository
public class ProgrammingLanguageGridDataDAO extends SessionAwareDAO implements IGridDataDAO<IdNameGridRow> {

    private static final String SELECT_QUERY =
            "select new nl.zoostation.database.model.grid.IdNameGridRow(pl.id, pl.name) from ProgrammingLanguage pl";

    private static final String SELECT_COUNT =
            "select count(distinct pl.id) from ProgrammingLanguage pl";

    @Autowired
    public ProgrammingLanguageGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<IdNameGridRow> getRows(GridViewInputSpec gridViewInputSpec) {
        return getSession().createQuery(SELECT_QUERY, IdNameGridRow.class).list();
    }

    @Override
    public Long count(GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        return getSession().createQuery(SELECT_COUNT, Long.class).uniqueResult();
    }
}
