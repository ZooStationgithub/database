package nl.zoostation.database.service;

import nl.zoostation.database.model.grid.GridViewInputSpec;
import nl.zoostation.database.model.grid.GridViewOutputSpec;

/**
 * @author val
 */
public interface IGridDataService<T> {

    GridViewOutputSpec<T> getGridData(GridViewInputSpec gridViewInputSpec);

}
