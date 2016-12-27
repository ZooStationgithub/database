package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotEmpty;
import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
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
        return Optional.ofNullable(getSession().get(getEntityType(), id));
    }

    @Override
    public List<E> findAll() {
        return getSession().createQuery(interpolate(QUERY_SELECT_ALL, getEntityType().getSimpleName()), getEntityType()).list();
    }

    @Override
    public List<E> findMany(@NotEmpty Collection<K> ids) {
        return getSession().createQuery(interpolate(QUERY_SELECT_MANY, getEntityType().getSimpleName(), IDS), getEntityType())
                .setParameterList(IDS, ids)
                .list();
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
