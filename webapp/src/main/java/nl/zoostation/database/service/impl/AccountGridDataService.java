package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.AccountGridRow;
import nl.zoostation.database.model.grid.GridViewInputSpec;
import nl.zoostation.database.model.grid.GridViewOutputSpec;
import nl.zoostation.database.service.IGridDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author val
 */
@Service
public class AccountGridDataService implements IGridDataService<AccountGridRow> {

    private final IGridDataDAO<AccountGridRow> accountGridDataDAO;

    @Autowired
    public AccountGridDataService(IGridDataDAO<AccountGridRow> accountGridDataDAO) {
        this.accountGridDataDAO = accountGridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<AccountGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' should not be null");
        List<AccountGridRow> rows = accountGridDataDAO.getRows(gridViewInputSpec);
        Long total = accountGridDataDAO.count(gridViewInputSpec, false);
        Long totalFiltered = accountGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<AccountGridRow> gridViewOutputSpec = new GridViewOutputSpec<>();
        gridViewOutputSpec.setRecords(rows);
        gridViewOutputSpec.setTotalRecords(total);
        gridViewOutputSpec.setFilteredRecords(totalFiltered);
        return gridViewOutputSpec;
    }

}
