package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.form.ProfileSearchFormObject;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.annotations.From;
import nl.zoostation.database.model.grid.annotations.Select;
import nl.zoostation.database.model.grid.annotations.Where;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;
import static nl.zoostation.database.app.Constants.Parameters.SEARCH_FILTER;

/**
 * @author valentinnastasi
 */
public class ProfileGridDataDAO extends SessionAwareDAO implements IGridDataDAO<ProfileGridRow> {

    private ThreadLocal<Map<String, Object>> parameterMap;
    private ThreadLocal<StringBuilder> queryBuilder;

    public ProfileGridDataDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        parameterMap = new ThreadLocal<>();
        queryBuilder = new ThreadLocal<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ProfileGridRow> getRows(@NotNull GridViewInputSpec gridViewInputSpec) {
        return wrappedCall(() -> {
            ProfileSearchFormObject formObject = (ProfileSearchFormObject) gridViewInputSpec.getExtras().get(SEARCH_FILTER);
            buildDataQuery(formObject);
            NativeQuery<ProfileGridRow> nativeQuery = getSession().createNativeQuery(queryBuilder.get().toString(), "ProfileGridRow");
            setQueryParameters(nativeQuery);
            return nativeQuery.list();
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long count(@NotNull GridViewInputSpec gridViewInputSpec, boolean applyFilter) {
        return wrappedCall(() -> {
            ProfileSearchFormObject formObject = (ProfileSearchFormObject) gridViewInputSpec.getExtras().get(SEARCH_FILTER);
            buildCountQuery(formObject, applyFilter);
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

    private void buildDataQuery(ProfileSearchFormObject profileSearchFormObject) {
        Select selectAnn = profileSearchFormObject.getClass().getAnnotation(Select.class);
        if (selectAnn == null) {
            throw new IllegalStateException("Class should be annotated with @Select");
        }
        From fromAnn = profileSearchFormObject.getClass().getAnnotation(From.class);
        if (fromAnn == null) {
            throw new IllegalStateException("Class should be annotated with @From");
        }

        queryBuilder.get()
                .append("SELECT").append(" ").append(selectAnn.columns()).append(" ")
                .append("FROM").append(" ").append(fromAnn.value()).append(" ");
        buildWhereClause(profileSearchFormObject);
    }

    private void buildCountQuery(ProfileSearchFormObject profileSearchFormObject, boolean applyFilter) {
        Select selectAnn = profileSearchFormObject.getClass().getAnnotation(Select.class);
        if (selectAnn == null) {
            throw new IllegalStateException("Class should be annotated with @Select");
        }
        From fromAnn = profileSearchFormObject.getClass().getAnnotation(From.class);
        if (fromAnn == null) {
            throw new IllegalStateException("Class should be annotated with @From");
        }

        queryBuilder.get()
                .append("SELECT").append(" ").append(selectAnn.count()).append(" ")
                .append("FROM").append(" ").append(fromAnn.value()).append(" ");

        if (applyFilter) {
            buildWhereClause(profileSearchFormObject);
        }
    }

    private void buildWhereClause(ProfileSearchFormObject profileSearchFormObject) {
        Collection<String> filterQueries = new ArrayList<>();

        Arrays.stream(profileSearchFormObject.getClass().getDeclaredFields()).forEach(field -> {
            Where whereAnn = field.getAnnotation(Where.class);
            if (whereAnn == null) {
                return;
            }

            String fieldName = field.getName();
            Object fieldValue = getFieldValue(fieldName, profileSearchFormObject);
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

    private Object getFieldValue(String fieldName, ProfileSearchFormObject target) throws RuntimeException {
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
