package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotEmpty;
import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static nl.zoostation.database.app.Constants.Parameters.IDS;

/**
 * @author valentinnastasi
 */
public class SimpleGenericReadOnlyEntityDAO<E, K extends Serializable> extends SessionAwareDAO implements IGenericReadOnlyEntityDAO<E, K> {

    private static final String QUERY_SELECT_ALL = "from {0}";
    private static final String QUERY_SELECT_MANY = "from {0} where id in (:{1})";

    protected final Logger logger = LogManager.getLogger(getClass());

    private final Class<E> entityClass;
    private final Class<K> identifierClass;

    public SimpleGenericReadOnlyEntityDAO(
            SessionFactory sessionFactory,
            Class<E> entityClass,
            Class<K> identifierClass) {

        super(sessionFactory);
        this.entityClass = entityClass;
        this.identifierClass = identifierClass;
    }

    @Override
    public Optional<E> findOne(@NotNull K id) {
        logger.debug("Finding entity by ID {}", id);
        Optional<E> e = Optional.ofNullable(getSession().get(getEntityType(), id));
        logger.trace("Entity found: {}", e);
        return e;
    }

    @Override
    public List<E> findAll() {
        logger.debug("Finding all entities");
        List<E> list = getSession().createQuery(interpolate(QUERY_SELECT_ALL, getEntityType().getSimpleName()), getEntityType()).list();
        logger.trace("Found {} entities", list.size());
        return list;
    }

    @Override
    public List<E> findMany(@NotEmpty Collection<K> ids) {
        logger.debug("Finding entities with IDs {}", ids);
        List<E> list = getSession().createQuery(interpolate(QUERY_SELECT_MANY, getEntityType().getSimpleName(), IDS), getEntityType())
                .setParameterList(IDS, ids)
                .list();
        logger.trace("Found {} entities", list.size());
        return list;
    }

    protected Class<E> getEntityType() {
        return entityClass;
    }

    protected Class<K> getIdentifierType() {
        return identifierClass;
    }

    protected String interpolate(String template, Object... params) {
        return MessageFormat.format(template, params);
    }

}
