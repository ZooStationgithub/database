package nl.zoostation.database.service.impl;

import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.IGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IGridDataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author valentinnastasi
 */
public class SimpleGridDataService<T extends IGridRow<?>> implements IGridDataService<T> {

    protected final Logger logger = LogManager.getLogger(getClass());

    private final IGridDataDAO<T> gridDataDAO;

    public SimpleGridDataService(IGridDataDAO<T> gridDataDAO) {
        this.gridDataDAO = gridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<T> getGridData(@NotNull GridViewInputSpec gridViewInputSpec) {
        logger.debug("Getting grid data for input spec {}", gridViewInputSpec);
        List<T> rows = gridDataDAO.getRows(gridViewInputSpec);
        Long totalFiltered = gridDataDAO.count(gridViewInputSpec, true);
        Long total = gridDataDAO.count(gridViewInputSpec, false);
        GridViewOutputSpec<T> gridViewOutputSpec = new GridViewOutputSpec<>(total, totalFiltered, rows);
        logger.trace("Grid data ready: {}", gridViewOutputSpec);
        return gridViewOutputSpec;
    }
}
