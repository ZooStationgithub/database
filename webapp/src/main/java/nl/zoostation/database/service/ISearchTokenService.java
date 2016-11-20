package nl.zoostation.database.service;

import nl.zoostation.database.model.input.SearchToken;

import java.util.List;
import java.util.Map;

/**
 * @author val
 */
public interface ISearchTokenService {

    List<? extends SearchToken> findTokens(String term, Map<String, Object> extras);
}
