package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

/**
 * @author val
 */
@Repository
@SuppressWarnings({"JpaQlInspection", "unchecked"})
public class AccountGridDataDAO extends SessionAwareDAO implements IGridDataDAO<AccountGridRow> {

    private static final String SU_USER = "su";

    private static final String SELECT_QUERY =
            "select new nl.zoostation.database.model.grid.AccountGridRow(" +
                    "a.id, a.login, g.displayName, a.creationDate, " +
                    "(case when (a.activationDate is null) then false else true end), " +
                    "(case when (a.login = :onlineUser) then true else false end)) " +
            "from Account a inner join a.accountGroup g " +
            "where a.login not in (:forbiddenUsers)";

    private static final String COUNT_QUERY =
            "select count(distinct a.id) " +
            "from Account a " +
            "where a.login not in (:forbiddenUsers)";

    @Autowired
    public AccountGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<AccountGridRow> getRows(GridViewInputSpec gridViewInputSpec) {
        String onlineUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Query query = getSession().createQuery(SELECT_QUERY).setParameter("onlineUser", onlineUser).setParameterList("forbiddenUsers", Collections.singleton(SU_USER));
        return query.list();
    }

    @Override
    public Long count(GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        Query query = getSession().createQuery(COUNT_QUERY).setParameterList("forbiddenUsers", Collections.singleton(SU_USER));
        return (Long) query.uniqueResult();
    }
}
