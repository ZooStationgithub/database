package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.dao.IRankTypeDAO;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.domain.RankType;
import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IRankTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
@Service
public class RankTypeService implements IRankTypeService {

    private final IRankTypeDAO rankTypeDAO;
    private final IGridDataDAO<IdNameGridRow> rankTypeGridDataDAO;

    @Autowired
    public RankTypeService(
            IRankTypeDAO rankTypeDAO,
            IGridDataDAO<IdNameGridRow> rankTypeGridDataDAO) {
        this.rankTypeDAO = rankTypeDAO;
        this.rankTypeGridDataDAO = rankTypeGridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<IdNameGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' is required");
        List<IdNameGridRow> rows = rankTypeGridDataDAO.getRows(gridViewInputSpec);
        Long total = rankTypeGridDataDAO.count(gridViewInputSpec, false);
        Long filtered = rankTypeGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<IdNameGridRow> outputSpec = new GridViewOutputSpec<>();
        outputSpec.setRecords(rows);
        outputSpec.setTotalRecords(total);
        outputSpec.setFilteredRecords(filtered);
        return outputSpec;
    }

    @Transactional(readOnly = true)
    @Override
    public IdNameForm prepareForm(Optional<Long> id) {
        RankType entity = findOrCreateEntity(id);
        return new IdNameForm(entity.getId(), entity.getName());
    }

    @Transactional
    @Override
    public void save(IdNameForm form) {
        Objects.requireNonNull(form, () -> "Parameter 'form' is required");

        RankType entity = findOrCreateEntity(Optional.ofNullable(form.getId()));
        entity.setId(form.getId());
        entity.setName(form.getName());

        rankTypeDAO.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        rankTypeDAO.delete(id);
    }

    private RankType findOrCreateEntity(Optional<Long> id) {
        if (!id.isPresent()) {
            return new RankType();
        }
        return rankTypeDAO.findOne(id.get()).orElse(new RankType());
    }
}
