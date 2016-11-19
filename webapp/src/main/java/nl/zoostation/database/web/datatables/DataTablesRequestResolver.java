package nl.zoostation.database.web.datatables;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static nl.zoostation.database.web.datatables.DataTablesConstants.Request.*;

/**
 * <p>
 * Spring resolver for {@link DataTablesRequest} class. This allows to use this type directly as argument type in mapping
 * methods.
 * </p>
 *
 * @author val
 */
public class DataTablesRequestResolver implements HandlerMethodArgumentResolver {

    @Override
    public Object resolveArgument(
            MethodParameter methodParameter,
            ModelAndViewContainer container,
            NativeWebRequest nativeWebRequest,
            WebDataBinderFactory binderFactory) throws Exception {

        final Map<String, String[]> parameterMap = nativeWebRequest.getParameterMap();

        DataTablesRequest dtRequest = new DataTablesRequest();

        Optional<Integer> drawCounter = extractIntegerParameter(parameterMap.entrySet().stream(), DRAW_COUNTER_PATTERN);
        dtRequest.setDrawCounter(drawCounter.orElse(null));

        Optional<Integer> pageStart = extractIntegerParameter(parameterMap.entrySet().stream(), PAGE_START_PATTERN);
        dtRequest.setPageStart(pageStart.orElse(null));

        Optional<Integer> pageLength = extractIntegerParameter(parameterMap.entrySet().stream(), PAGE_LENGTH_PATTERN);
        dtRequest.setPageLength(pageLength.orElse(null));

        Optional<Integer> orderColumn = extractIntegerParameter(parameterMap.entrySet().stream(), ORDER_COLUMN_PATTERN);
        dtRequest.getOrderDefinition().setColumnIndex(orderColumn.orElse(null));

        Optional<String> orderDirection = extractStringParameter(parameterMap.entrySet().stream(), ORDER_DIRECTION_PATTERN);
        dtRequest.getOrderDefinition().setDirection(orderDirection.orElse(null));

        Optional<String> globalFilter = extractStringParameter(parameterMap.entrySet().stream(), GLOBAL_FILTER_PATTERN);
        dtRequest.setGlobalFilter(globalFilter.orElse(null));

        Map<Integer, DataTablesRequest.ColumnDefinition> columnDefinitionMap = new HashMap<>();

        parameterMap.entrySet().stream()
                .filter((entry) -> entry.getKey().matches(COLUMN_DEFINITION_PATTERN))
                .collect(groupingBy((entry) -> Integer.valueOf(entry.getKey().replaceAll("\\D", ""))))
                .forEach((key, value) -> {
                    DataTablesRequest.ColumnDefinition columnDefinition = new DataTablesRequest.ColumnDefinition();

                    Optional<String> name = extractStringParameter(value.stream(), COLUMN_NAME_PATTERN);
                    columnDefinition.setName(name.orElse(null));

                    Optional<String> header = extractStringParameter(value.stream(), COLUMN_HEADER_PATTERN);
                    columnDefinition.setHeader(header.orElse(null));

                    Optional<String> searchable = extractStringParameter(value.stream(), COLUMN_SEARCHABLE_PATTERN);
                    columnDefinition.setSearchable(Boolean.valueOf(searchable.orElse("false")));

                    Optional<String> orderable = extractStringParameter(value.stream(), COLUMN_ORDERABLE_PATTERN);
                    columnDefinition.setOrderable(Boolean.valueOf(orderable.orElse("false")));

                    Optional<String> filterValue = extractStringParameter(value.stream(), COLUMN_FILTER_PATTERN);
                    columnDefinition.setFilterValue(filterValue.orElse(null));

                    columnDefinitionMap.put(key, columnDefinition);
                });
        dtRequest.setColumnDefinitions(columnDefinitionMap);

        Map<String, String> extras = new HashMap<>();

        parameterMap
                .entrySet()
                .stream()
                .filter((entry) -> {
                    String pattern = DRAW_COUNTER_PATTERN + "|" + PAGE_START_PATTERN + "|" + PAGE_LENGTH_PATTERN + "|"
                            + ORDER_COLUMN_PATTERN + "|"
                            + ORDER_DIRECTION_PATTERN
                            + "|" + GLOBAL_FILTER_PATTERN + "|" + COLUMN_DEFINITION_PATTERN;
                    return !entry.getKey().matches(pattern);
                })
                .collect(groupingBy(Map.Entry::getKey))
                .forEach((key, value) -> {
                    extras.put(key, value.get(0).getValue()[0]);
                });
        dtRequest.setExtras(extras);

        return dtRequest;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(DataTablesRequest.class);
    }

    private Optional<String> extractStringParameter(Stream<Map.Entry<String, String[]>> stream, final String parameterName) {
        Optional<String> parameterValue = stream
                .filter((entry) -> entry.getKey().matches(parameterName))
                .map((entry) -> entry.getValue()[0])
                .reduce((one, two) -> one);

        return parameterValue;
    }

    private Optional<Integer> extractIntegerParameter(Stream<Map.Entry<String, String[]>> stream, final String parameterName) {
        Optional<Integer> parameterValue = stream
                .filter((entry) -> entry.getKey().matches(parameterName))
                .map((entry) -> entry.getValue()[0])
                .map(Integer::valueOf)
                .reduce((one, two) -> one);

        return parameterValue;
    }

}