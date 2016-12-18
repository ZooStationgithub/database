package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.Named;
import nl.zoostation.database.model.form.IdNameFormObject;
import nl.zoostation.database.model.form.SimpleFormWrapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public class SimpleFormService<E extends Named> extends AbstractFormService<E, Long, IdNameFormObject, SimpleFormWrapper<IdNameFormObject>> {

    public SimpleFormService(IGenericEntityDAO<E, Long> genericEntityDAO) {
        super(genericEntityDAO);
    }

    @Transactional(readOnly = true)
    @Override
    public SimpleFormWrapper<IdNameFormObject> prepareForm(Optional<Long> identifier) {
        E entity = findOrCreateEntity(identifier);
        IdNameFormObject formObject = new IdNameFormObject();
        entityToForm(entity, formObject);
        SimpleFormWrapper<IdNameFormObject> formWrapper = new SimpleFormWrapper<>(formObject);
        return formWrapper;
    }

    @Override
    protected void entityToForm(E entity, IdNameFormObject formObject) {
        formObject.setId(entity.getId());
        formObject.setName(entity.getName());
    }

    @Override
    protected void formToEntity(IdNameFormObject formObject, E entity) {
        entity.setId(formObject.getId());
        entity.setName(formObject.getName());
    }
}
