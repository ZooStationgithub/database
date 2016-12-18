package nl.zoostation.database.dao.impl;

import nl.zoostation.database.model.domain.Named;
import nl.zoostation.database.model.grid.IdNameGridRow;
import org.hibernate.SessionFactory;

/**
 * @author valentinnastasi
 */
public class NamedEntityGridDataDAO<E extends Named> extends SimpleGridDataDAO<E, IdNameGridRow> {

    public NamedEntityGridDataDAO(SessionFactory sessionFactory, Class<E> entityClass) {
        super(sessionFactory, entityClass, (e) -> new IdNameGridRow(e.getId(), e.getName()));
    }
}
