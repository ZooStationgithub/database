package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.domain.Identifiable;
import nl.zoostation.database.model.grid.IGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author valentinnastasi
 */
public class SimpleGridDataDAO<E extends Identifiable, T extends IGridRow> extends SessionAwareDAO implements IGridDataDAO<T> {

    private static final String QUERY_FIND_ALL = "from {0} order by id asc";
    private static final String QUERY_COUNT = "select count(distinct id) from {0}";

    private final Logger logger = LogManager.getLogger(getClass());

    private final Function<E, T> resultMapper;
    private final Class<E> entityClass;

    public SimpleGridDataDAO(
            SessionFactory sessionFactory,
            Class<E> entityClass,
            Function<E, T> resultMapper) {
        super(sessionFactory);
        this.resultMapper = resultMapper;
        this.entityClass = entityClass;
    }

    @Override
    public List<T> getRows(@NotNull GridViewInputSpec gridViewInputSpec) {
        logger.debug("Getting row data for grid input spec {}", gridViewInputSpec);
        List<T> list = getSession().createQuery(interpolate(QUERY_FIND_ALL, entityClass.getSimpleName()), getEntityType()).stream()
                .map(resultMapper)
                .collect(toList());
        logger.trace("Row data ready: {}", list);
        return list;
    }

    @Override
    public Long count(@NotNull GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        logger.debug("Counting grid rows (with filter? {}) for grid input spec {}", applyFilter, gridViewInputSpec);
        Long result = getSession().createQuery(interpolate(QUERY_COUNT, getEntityType().getSimpleName()), Long.class).uniqueResult();
        logger.trace("Count finished: {}", result);
        return result;
    }

    protected Class<E> getEntityType() {
        return entityClass;
    }

    protected String interpolate(String template, Object... params) {
        return MessageFormat.format(template, params);
    }

}
