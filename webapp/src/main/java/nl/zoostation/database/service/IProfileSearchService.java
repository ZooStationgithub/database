package nl.zoostation.database.service;

import nl.zoostation.database.model.grid.SearchQuery;
import nl.zoostation.database.model.grid.SearchQueryContainer;
import nl.zoostation.database.model.view.ProfileView;

import java.util.List;

/**
 * @author valentinnastasi
 */
public interface IProfileSearchService {

    SearchQueryContainer prepareForm();

    List<ProfileView> getListItems(SearchQuery searchQuery);

}
