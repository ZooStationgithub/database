package nl.zoostation.database.service;

import nl.zoostation.database.model.grid.ProfileGridRow;
import nl.zoostation.database.model.grid.SearchQueryContainer;

/**
 * @author valentinnastasi
 */
public interface IProfileSearchService extends IGridDataService<ProfileGridRow> {

    SearchQueryContainer prepareForm();

}
