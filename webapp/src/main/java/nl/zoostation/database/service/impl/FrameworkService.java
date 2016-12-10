package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IFrameworkDAO;
import nl.zoostation.database.dao.IGridDataDAO;
import nl.zoostation.database.dao.IProgrammingLanguageDAO;
import nl.zoostation.database.model.domain.Framework;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.form.FrameworkForm;
import nl.zoostation.database.model.form.FrameworkFormContainer;
import nl.zoostation.database.model.grid.FrameworkGridRow;
import nl.zoostation.database.model.grid.datatables.GridViewInputSpec;
import nl.zoostation.database.model.grid.datatables.GridViewOutputSpec;
import nl.zoostation.database.service.IFrameworkService;
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
public class FrameworkService implements IFrameworkService {

    private final IFrameworkDAO frameworkDAO;
    private final IGridDataDAO<FrameworkGridRow> frameworkGridDataDAO;
    private final IProgrammingLanguageDAO programmingLanguageDAO;

    @Autowired
    public FrameworkService(
            IFrameworkDAO frameworkDAO,
            IGridDataDAO<FrameworkGridRow> frameworkGridDataDAO,
            IProgrammingLanguageDAO programmingLanguageDAO) {

        this.frameworkDAO = frameworkDAO;
        this.frameworkGridDataDAO = frameworkGridDataDAO;
        this.programmingLanguageDAO = programmingLanguageDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public GridViewOutputSpec<FrameworkGridRow> getGridData(GridViewInputSpec gridViewInputSpec) {
        Objects.requireNonNull(gridViewInputSpec, () -> "Parameter 'gridViewInputSpec' is required");
        List<FrameworkGridRow> rows = frameworkGridDataDAO.getRows(gridViewInputSpec);
        Long total = frameworkGridDataDAO.count(gridViewInputSpec, false);
        Long filtered = frameworkGridDataDAO.count(gridViewInputSpec, true);
        GridViewOutputSpec<FrameworkGridRow> outputSpec = new GridViewOutputSpec<>();
        outputSpec.setRecords(rows);
        outputSpec.setTotalRecords(total);
        outputSpec.setFilteredRecords(filtered);
        return outputSpec;
    }

    @Transactional(readOnly = true)
    @Override
    public FrameworkFormContainer prepareForm(Optional<Long> id) {
        Framework entity = findOrCreateEntity(id);
        FrameworkForm frameworkForm = new FrameworkForm();
        frameworkForm.setId(entity.getId());
        frameworkForm.setName(entity.getName());
        frameworkForm.setProgrammingLanguageId(Optional.ofNullable(entity.getProgrammingLanguage()).map(ProgrammingLanguage::getId).orElse(null));
        FrameworkFormContainer frameworkFormContainer = new FrameworkFormContainer();
        frameworkFormContainer.setFrameworkForm(frameworkForm);
        frameworkFormContainer.setProgrammingLanguages(programmingLanguageDAO.findAll());
        return frameworkFormContainer;
    }

    @Transactional
    @Override
    public void save(FrameworkForm form) {
        Objects.requireNonNull(form, () -> "Parameter 'form' is required");
        Framework entity = findOrCreateEntity(Optional.ofNullable(form.getId()));
        entity.setId(form.getId());
        entity.setName(form.getName());
        entity.setProgrammingLanguage(programmingLanguageDAO.findOne(form.getProgrammingLanguageId())
                .orElseThrow(() -> new IllegalStateException("Programming language with ID " + form.getProgrammingLanguageId() + "does not exist")));

        frameworkDAO.save(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id, () -> "Parameter 'id' is required");
        frameworkDAO.delete(id);
    }

    private Framework findOrCreateEntity(Optional<Long> id) {
        if (!id.isPresent()) {
            return new Framework();
        }
        return frameworkDAO.findOne(id.get()).orElse(new Framework());
    }

}
