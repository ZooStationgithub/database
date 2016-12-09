package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.dao.IProgrammingLanguageDAO;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IProgrammingLanguageService;
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
public class ProgrammingLanguageService implements IProgrammingLanguageService {

    private final IProgrammingLanguageDAO programmingLanguageDAO;
    private final IGridDataDAO<IdNameGridRow> programmingLanguageGridDataDAO;

    @Autowired
    public ProgrammingLanguageService(IProgrammingLanguageDAO programmingLanguageDAO, IGridDataDAO<IdNameGridRow> programmingLanguageGridDataDAO) {
        this.programmingLanguageDAO = programmingLanguageDAO;
        this.programmingLanguageGridDataDAO = programmingLanguageGridDataDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public IdNameForm prepareForm(Optional<Long> id) {
        ProgrammingLanguage entity = findOrCreateEntity(id);
        return new IdNameForm(entity.getId(), entity.getName());
    }

    @Transactional
    @Override
    public void save(IdNameForm form) {
        Objects.requireNonNull(form, () -> "Parameter 'form' is required");

        ProgrammingLanguage entity = findOrCreateEntity(Optional.ofNullable(form.getId()));
        entity.setId(form.getId());
        entity.setName(form.getName());

        programmingLanguageDAO.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        programmingLanguageDAO.delete(id);
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<IdNameGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' is required");
        List<IdNameGridRow> rows = programmingLanguageGridDataDAO.getRows(gridViewInputSpec);
        Long total = programmingLanguageGridDataDAO.count(gridViewInputSpec, false);
        Long filtered = programmingLanguageGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<IdNameGridRow> outputSpec = new GridViewOutputSpec<>();
        outputSpec.setRecords(rows);
        outputSpec.setTotalRecords(total);
        outputSpec.setFilteredRecords(filtered);
        return outputSpec;
    }

    private ProgrammingLanguage findOrCreateEntity(Optional<Long> id) {
        if (!id.isPresent()) {
            return new ProgrammingLanguage();
        }
        return programmingLanguageDAO.findOne(id.get()).orElse(new ProgrammingLanguage());
    }


}
