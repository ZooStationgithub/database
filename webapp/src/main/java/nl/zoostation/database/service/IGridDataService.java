package nl.zoostation.database.service;

import nl.zoostation.database.model.grid.IGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;

/**
 * @author val
 */
public interface IGridDataService<T extends IGridRow> {

    GridViewOutputSpec<T> getGridData(GridViewInputSpec gridViewInputSpec);

}
