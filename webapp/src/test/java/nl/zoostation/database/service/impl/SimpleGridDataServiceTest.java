package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.BaseServiceTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author val
 */
public class SimpleGridDataServiceTest extends BaseServiceTest {

    @Autowired
    private IGridDataDAO<IdNameGridRow> programmingLanguageGridDataDAO;

    @Autowired
    private SimpleGridDataService<IdNameGridRow> programmingLanguageGridDataService;

    @SuppressWarnings("unchecked")
    @After
    public void tearDown() throws Exception {
        Mockito.reset(programmingLanguageGridDataDAO);
    }

    @Test
    public void testGetGridData() throws Exception {
        GridViewInputSpec gridViewInputSpec = new GridViewInputSpec();
        GridViewOutputSpec<IdNameGridRow> gridViewOutputSpec = createGridViewOutputSpec();
        when(programmingLanguageGridDataDAO.getRows(gridViewInputSpec)).thenReturn((List<IdNameGridRow>) gridViewOutputSpec.getRecords());
        when(programmingLanguageGridDataDAO.count(gridViewInputSpec, true)).thenReturn(gridViewOutputSpec.getFilteredRecords());
        when(programmingLanguageGridDataDAO.count(gridViewInputSpec, false)).thenReturn(gridViewOutputSpec.getTotalRecords());

        GridViewOutputSpec<IdNameGridRow> gridData = programmingLanguageGridDataService.getGridData(gridViewInputSpec);
        assertThat(gridData).isNotNull().isEqualToComparingFieldByField(gridViewOutputSpec);
    }

    private GridViewOutputSpec<IdNameGridRow> createGridViewOutputSpec() {
        List<IdNameGridRow> list = Arrays.asList(new IdNameGridRow(1L, "One"), new IdNameGridRow(2L, "Two"));
        GridViewOutputSpec<IdNameGridRow> gridViewOutputSpec = new GridViewOutputSpec<>();
        gridViewOutputSpec.setFilteredRecords((long) list.size());
        gridViewOutputSpec.setTotalRecords((long) list.size());
        gridViewOutputSpec.setRecords(list);
        return gridViewOutputSpec;
    }

}