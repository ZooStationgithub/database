package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.Framework;
import nl.zoostation.database.model.domain.Identifiable;
import nl.zoostation.database.model.domain.ProgrammingLanguage;
import nl.zoostation.database.model.form.FrameworkFormObject;
import nl.zoostation.database.model.form.FrameworkFormWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public class FrameworkFormService extends AbstractFormService<Framework, Long, FrameworkFormObject, FrameworkFormWrapper> {

    private final IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;

    public FrameworkFormService(
            IGenericEntityDAO<Framework, Long> frameworkDAO,
            IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO) {
        super(frameworkDAO);
        this.programmingLanguageDAO = programmingLanguageDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public FrameworkFormWrapper prepareForm(Optional<Long> identifier) {
        Framework entity = findOrCreateEntity(identifier);
        FrameworkFormObject formObject = new FrameworkFormObject();
        entityToForm(entity, formObject);
        FrameworkFormWrapper formWrapper = new FrameworkFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setProgrammingLanguages(programmingLanguageDAO.findAll());
        return formWrapper;
    }

    @Override
    protected void entityToForm(Framework entity, FrameworkFormObject formObject) {
        formObject.setId(entity.getId());
        formObject.setName(entity.getName());
        formObject.setProgrammingLanguageId(Optional.ofNullable(entity.getProgrammingLanguage()).map(Identifiable::getId).orElse(null));
    }

    @Override
    protected void formToEntity(FrameworkFormObject formObject, Framework entity) {
        entity.setId(formObject.getId());
        entity.setName(formObject.getName());

        ProgrammingLanguage programmingLanguage = programmingLanguageDAO.findOne(formObject.getProgrammingLanguageId())
                .orElseThrow(() -> new IllegalStateException("Programming language with ID " + formObject.getProgrammingLanguageId() + " not found"));
        entity.setProgrammingLanguage(programmingLanguage);
    }
}
