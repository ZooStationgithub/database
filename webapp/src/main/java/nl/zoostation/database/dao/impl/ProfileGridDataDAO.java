package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.*;
import nl.zoostation.database.model.grid.annotations.From;
import nl.zoostation.database.model.grid.annotations.Select;
import nl.zoostation.database.model.grid.annotations.Where;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;
import static nl.zoostation.database.app.Constants.Parameters.SEARCH_FILTER;

/**
 * @author valentinnastasi
 */
@Repository
public class ProfileGridDataDAO extends SessionAwareDAO implements IGridDataDAO<ProfileGridRow> {

    private ThreadLocal<Map<String, Object>> parameterMap;
    private ThreadLocal<StringBuilder> queryBuilder;

    @Autowired
    public ProfileGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        parameterMap = new ThreadLocal<>();
        queryBuilder = new ThreadLocal<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProfileGridRow> getRows(GridViewInputSpec gridViewInputSpec) {
        return wrappedCall(() -> {
            SearchFilter searchFilter = (SearchFilter) gridViewInputSpec.getExtras().get(SEARCH_FILTER);
            buildDataQuery(searchFilter);
            NativeQuery<ProfileGridRow> nativeQuery = getSession().createNativeQuery(queryBuilder.get().toString(), "ProfileGridRow");
            setQueryParameters(nativeQuery);
            return nativeQuery.list();
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long count(GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        return wrappedCall(() -> {
            SearchFilter searchFilter = (SearchFilter) gridViewInputSpec.getExtras().get(SEARCH_FILTER);
            buildCountQuery(searchFilter, applyFilter);
            NativeQuery<Number> nativeQuery = getSession().createNativeQuery(queryBuilder.get().toString());
            setQueryParameters(nativeQuery);
            return nativeQuery.getSingleResult().longValue();
        });
    }

    private <T> T wrappedCall(Supplier<T> supplier) {
        parameterMap.set(new HashMap<>());
        queryBuilder.set(new StringBuilder());
        try {
            return supplier.get();
        } finally {
            parameterMap.remove();
            queryBuilder.remove();
        }
    }

    private void buildDataQuery(SearchFilter searchFilter) {
        Select selectAnn = searchFilter.getClass().getAnnotation(Select.class);
        if (selectAnn == null) {
            throw new IllegalStateException("Class should be annotated with @Select");
        }
        From fromAnn = searchFilter.getClass().getAnnotation(From.class);
        if (fromAnn == null) {
            throw new IllegalStateException("Class should be annotated with @From");
        }

        queryBuilder.get()
                .append("SELECT").append(" ").append(selectAnn.columns()).append(" ")
                .append("FROM").append(" ").append(fromAnn.value()).append(" ");
        buildWhereClause(searchFilter);
    }

    private void buildCountQuery(SearchFilter searchFilter, boolean applyFilter) {
        Select selectAnn = searchFilter.getClass().getAnnotation(Select.class);
        if (selectAnn == null) {
            throw new IllegalStateException("Class should be annotated with @Select");
        }
        From fromAnn = searchFilter.getClass().getAnnotation(From.class);
        if (fromAnn == null) {
            throw new IllegalStateException("Class should be annotated with @From");
        }

        queryBuilder.get()
                .append("SELECT").append(" ").append(selectAnn.count()).append(" ")
                .append("FROM").append(" ").append(fromAnn.value()).append(" ");

        if (applyFilter) {
            buildWhereClause(searchFilter);
        }
    }

    private void buildWhereClause(SearchFilter searchFilter) {
        Collection<String> filterQueries = new ArrayList<>();

        Arrays.stream(searchFilter.getClass().getDeclaredFields()).forEach(field -> {
            Where whereAnn = field.getAnnotation(Where.class);
            if (whereAnn == null) {
                return;
            }

            String fieldName = field.getName();
            Object fieldValue = getFieldValue(fieldName, searchFilter);
            if (isEmpty(fieldValue)) {
                return;
            }

            String filterQuery = whereAnn.value().replace("${field}", fieldName);
            filterQueries.add(filterQuery);
            parameterMap.get().put(fieldName, fieldValue);

        });

        if (!filterQueries.isEmpty()) {
            queryBuilder.get().append(" ").append("WHERE").append(" ")
                    .append(filterQueries.stream().map(q -> "(" + q + ")").collect(joining(" AND ")));
        }
    }

    private Object getFieldValue(String fieldName, SearchFilter target) throws RuntimeException {
        try {
            return PropertyUtils.getProperty(target, fieldName);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isEmpty(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof CharSequence) {
            return StringUtils.isEmpty((CharSequence) value);
        } else if (value instanceof Collection) {
            return CollectionUtils.isEmpty((Collection) value);
        } else {
            return false;
        }
    }

    private void setQueryParameters(NativeQuery<?> query) {
        parameterMap.get().forEach((key, value) -> {
            if (value instanceof Boolean) {
                Boolean typedValue = (Boolean) value;
                query.setParameter(key, typedValue ? 1 : 0);
            } else if (value instanceof Collection) {
                Collection typedValue = (Collection) value;
                query.setParameterList(key, typedValue);
            } else {
                query.setParameter(key, value);
            }
        });
    }

}
