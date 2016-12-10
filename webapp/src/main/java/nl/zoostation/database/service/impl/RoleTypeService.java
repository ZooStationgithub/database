package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.dao.IRoleTypeDAO;
import nl.zoostation.database.model.domain.RoleType;
import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IRoleTypeService;
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
public class RoleTypeService implements IRoleTypeService {

    private final IRoleTypeDAO roleTypeDAO;
    private final IGridDataDAO<IdNameGridRow> roleTypeGridDataDAO;

    @Autowired
    public RoleTypeService(
            IRoleTypeDAO roleTypeDAO,
            IGridDataDAO<IdNameGridRow> roleTypeGridDataDAO) {
        this.roleTypeDAO = roleTypeDAO;
        this.roleTypeGridDataDAO = roleTypeGridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<IdNameGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' is required");
        List<IdNameGridRow> rows = roleTypeGridDataDAO.getRows(gridViewInputSpec);
        Long total = roleTypeGridDataDAO.count(gridViewInputSpec, false);
        Long filtered = roleTypeGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<IdNameGridRow> outputSpec = new GridViewOutputSpec<>();
        outputSpec.setRecords(rows);
        outputSpec.setTotalRecords(total);
        outputSpec.setFilteredRecords(filtered);
        return outputSpec;
    }

    @Transactional(readOnly = true)
    @Override
    public IdNameForm prepareForm(Optional<Long> id) {
        RoleType entity = findOrCreateEntity(id);
        return new IdNameForm(entity.getId(), entity.getName());
    }

    @Transactional
    @Override
    public void save(IdNameForm form) {
        Objects.requireNonNull(form, () -> "Parameter 'form' is required");

        RoleType entity = findOrCreateEntity(Optional.ofNullable(form.getId()));
        entity.setId(form.getId());
        entity.setName(form.getName());

        roleTypeDAO.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        roleTypeDAO.delete(id);
    }

    private RoleType findOrCreateEntity(Optional<Long> id) {
        if (!id.isPresent()) {
            return new RoleType();
        }
        return roleTypeDAO.findOne(id.get()).orElse(new RoleType());
    }
}
