package nl.zoostation.database.dao;

import nl.zoostation.database.model.grid.GridViewInputSpec;

import java.util.List;

/**
 * @author val
 */
public interface IGridDataDAO<T> {

    List<T> getRows(GridViewInputSpec gridViewInputSpec);

    Long count(GridViewInputSpec gridViewInputSpec, boolean applyFilter);

}
