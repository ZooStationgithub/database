package nl.zoostation.database.dao.impl;

import nl.zoostation.database.model.domain.Framework;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import org.hibernate.SessionFactory;

/**
 * @author valentinnastasi
 */
public class FrameworkGridDataDAO extends SimpleGridDataDAO<Framework, FrameworkGridRow> {

    public FrameworkGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory, Framework.class, (e) -> new FrameworkGridRow(e.getId(), e.getName(), e.getProgrammingLanguage().getName()));
    }
}
