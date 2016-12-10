package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.ICompanyTypeDAO;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.model.domain.CompanyType;
import nl.zoostation.database.model.domain.ContractType;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.ICompanyTypeService;
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
public class CompanyTypeService implements ICompanyTypeService {

    private final ICompanyTypeDAO companyTypeDAO;
    private final IGridDataDAO<IdNameGridRow> companyTypeGridDataDAO;

    @Autowired
    public CompanyTypeService(
            ICompanyTypeDAO companyTypeDAO,
            IGridDataDAO<IdNameGridRow> companyTypeGridDataDAO) {
        this.companyTypeDAO = companyTypeDAO;
        this.companyTypeGridDataDAO = companyTypeGridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<IdNameGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' is required");
        List<IdNameGridRow> rows = companyTypeGridDataDAO.getRows(gridViewInputSpec);
        Long total = companyTypeGridDataDAO.count(gridViewInputSpec, false);
        Long filtered = companyTypeGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<IdNameGridRow> outputSpec = new GridViewOutputSpec<>();
        outputSpec.setRecords(rows);
        outputSpec.setTotalRecords(total);
        outputSpec.setFilteredRecords(filtered);
        return outputSpec;
    }

    @Transactional(readOnly = true)
    @Override
    public IdNameForm prepareForm(Optional<Long> id) {
        CompanyType entity = findOrCreateEntity(id);
        return new IdNameForm(entity.getId(), entity.getName());
    }

    @Transactional
    @Override
    public void save(IdNameForm form) {
        Objects.requireNonNull(form, () -> "Parameter 'form' is required");

        CompanyType entity = findOrCreateEntity(Optional.ofNullable(form.getId()));
        entity.setId(form.getId());
        entity.setName(form.getName());

        companyTypeDAO.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        companyTypeDAO.delete(id);
    }

    private CompanyType findOrCreateEntity(Optional<Long> id) {
        if (!id.isPresent()) {
            return new CompanyType();
        }

        return companyTypeDAO.findOne(id.get()).orElse(new CompanyType());
    }
}
