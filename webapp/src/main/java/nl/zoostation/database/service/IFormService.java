package nl.zoostation.database.service;

import nl.zoostation.database.model.domain.PersistentEntity;
import nl.zoostation.database.model.form.IFormObject;
import nl.zoostation.database.model.form.IFormWrapper;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IFormService<E extends PersistentEntity, K extends Serializable, F extends IFormObject, W extends IFormWrapper<F>> {

    W prepareForm(Optional<K> identifier);

    E save(F formObject);

    void delete(K identifier);

}
