package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IContractTypeDAO;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.domain.ContractType;
import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IContractTypeService;
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
public class ContractTypeService implements IContractTypeService {

    private final IContractTypeDAO contractTypeDAO;
    private final IGridDataDAO<IdNameGridRow> contractTypeGridDataDAO;

    @Autowired
    public ContractTypeService(
            IContractTypeDAO contractTypeDAO,
            IGridDataDAO<IdNameGridRow> contractTypeGridDataDAO) {

        this.contractTypeDAO = contractTypeDAO;
        this.contractTypeGridDataDAO = contractTypeGridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<IdNameGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' is required");
        List<IdNameGridRow> rows = contractTypeGridDataDAO.getRows(gridViewInputSpec);
        Long total = contractTypeGridDataDAO.count(gridViewInputSpec, false);
        Long filtered = contractTypeGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<IdNameGridRow> outputSpec = new GridViewOutputSpec<>();
        outputSpec.setRecords(rows);
        outputSpec.setTotalRecords(total);
        outputSpec.setFilteredRecords(filtered);
        return outputSpec;
    }

    @Transactional(readOnly = true)
    @Override
    public IdNameForm prepareForm(Optional<Long> id) {
        ContractType entity = findOrCreateEntity(id);
        return new IdNameForm(entity.getId(), entity.getName());
    }

    @Transactional
    @Override
    public void save(IdNameForm form) {
        Objects.requireNonNull(form, () -> "Parameter 'form' is required");

        ContractType entity = findOrCreateEntity(Optional.ofNullable(form.getId()));
        entity.setId(form.getId());
        entity.setName(form.getName());

        contractTypeDAO.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        contractTypeDAO.delete(id);
    }

    private ContractType findOrCreateEntity(Optional<Long> id) {
        if (!id.isPresent()) {
            return new ContractType();
        }

        return contractTypeDAO.findOne(id.get()).orElse(new ContractType());
    }

}
