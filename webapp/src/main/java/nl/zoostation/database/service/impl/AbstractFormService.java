package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.PersistentEntity;
import nl.zoostation.database.model.form.IFormObject;
import nl.zoostation.database.model.form.IFormWrapper;
import nl.zoostation.database.service.IFormService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
abstract class AbstractFormService<E extends PersistentEntity, K extends Serializable, F extends IFormObject<K>, W extends IFormWrapper<F>>
        extends TransactionAwareService implements IFormService<E, K, F, W> {

    private final IGenericEntityDAO<E, K> genericEntityDAO;

    protected AbstractFormService(IGenericEntityDAO<E, K> genericEntityDAO) {
        this.genericEntityDAO = genericEntityDAO;
    }

    @Transactional
    @Override
    public E save(F formObject) {
        Objects.requireNonNull(formObject, () -> "Parameter 'formObject' must not be null");
        E entity = findOrCreateEntity(Optional.ofNullable(formObject.getId()));
        formToEntity(formObject, entity);
        return genericEntityDAO.save(entity);
    }

    @Transactional
    @Override
    public void delete(K identifier) {
        Objects.requireNonNull(identifier, () -> "Parameter 'identifier' must not be null");
        genericEntityDAO.delete(identifier);
    }

    protected E findOrCreateEntity(Optional<K> identifier) {
        if (!identifier.isPresent()) {
            return genericEntityDAO.create();
        }
        return genericEntityDAO.findOne(identifier.get()).orElse(genericEntityDAO.create());
    }

    protected abstract void entityToForm(E entity, F formObject);

    protected abstract void formToEntity(F formObject, E entity);

}
