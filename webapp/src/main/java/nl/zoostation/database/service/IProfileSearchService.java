package nl.zoostation.database.service;

import nl.zoostation.database.model.form.ProfileSearchFormWrapper;
import nl.zoostation.database.model.grid.ProfileGridRow;

/**
 * @author valentinnastasi
 */
public interface IProfileSearchService extends IGridDataService<ProfileGridRow> {

    ProfileSearchFormWrapper prepareForm();

}
