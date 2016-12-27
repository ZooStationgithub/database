package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;

/**
 * @author val
 */
public class AccountGridDataDAO extends SessionAwareDAO implements IGridDataDAO<AccountGridRow> {

    private static final Logger logger = LogManager.getLogger(AccountGridDataDAO.class);

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

    public AccountGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<AccountGridRow> getRows(@NotNull GridViewInputSpec gridViewInputSpec) {
        logger.debug("Getting row data for grid input spec {}", gridViewInputSpec);
        String onlineUser = SecurityContextHolder.getContext().getAuthentication().getName();
        Query<AccountGridRow> query = getSession().createQuery(SELECT_QUERY, AccountGridRow.class).setParameter("onlineUser", onlineUser).setParameterList("forbiddenUsers", Collections.singleton(SU_USER));
        List<AccountGridRow> list = query.list();
        logger.trace("Row data ready: {}", list);
        return list;
    }

    @Override
    public Long count(@NotNull GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        logger.debug("Counting grid rows (with filter? {}) for grid input spec {}", applyFilter, gridViewInputSpec);
        Query<Long> query = getSession().createQuery(COUNT_QUERY, Long.class).setParameterList("forbiddenUsers", Collections.singleton(SU_USER));
        Long result = query.uniqueResult();
        logger.trace("Count finished: {}", result);
        return result;
    }
}
