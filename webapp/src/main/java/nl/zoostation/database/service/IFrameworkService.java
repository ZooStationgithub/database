package nl.zoostation.database.service;

import nl.zoostation.database.model.form.FrameworkForm;
import nl.zoostation.database.model.form.FrameworkFormContainer;
import nl.zoostation.database.model.grid.FrameworkGridRow;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IFrameworkService extends IGridDataService<FrameworkGridRow> {

    FrameworkFormContainer prepareForm(Optional<Long> id);

    void save(FrameworkForm form);

    void delete(Long id);

}
