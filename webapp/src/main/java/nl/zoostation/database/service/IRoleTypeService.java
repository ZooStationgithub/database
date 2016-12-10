package nl.zoostation.database.service;

import nl.zoostation.database.model.form.IdNameForm;
import nl.zoostation.database.model.grid.IdNameGridRow;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IRoleTypeService extends IGridDataService<IdNameGridRow> {

    IdNameForm prepareForm(Optional<Long> id);

    void save(IdNameForm form);

    void delete(Long id);

}
