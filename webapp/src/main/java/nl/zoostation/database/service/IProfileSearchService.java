package nl.zoostation.database.service;

import nl.zoostation.database.dao.ISearchTokenDAO;
import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.SearchQueryContainer;

/**
 * @author valentinnastasi
 */
public interface IProfileSearchService extends IGridDataService<ProfileGridRow>, ISearchTokenService {

    SearchQueryContainer prepareForm();

}
