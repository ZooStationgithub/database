package nl.zoostation.database.dao;

import nl.zoostation.database.model.view.ProfileGridRow;

import java.util.List;

/**
 * @author valentinnastasi
 */
public interface IProfileGridDAO {

    List<ProfileGridRow> getData();

    Long count();

}
