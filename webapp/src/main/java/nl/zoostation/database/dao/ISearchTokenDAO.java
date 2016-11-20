package nl.zoostation.database.dao;

import nl.zoostation.database.model.input.SearchToken;

import java.util.List;
import java.util.Map;

/**
 * @author val
 */
public interface ISearchTokenDAO {

    String PARAM_TERM = "term";

    List<? extends SearchToken> findTokens(String term, Map<String, Object> extras);

}
