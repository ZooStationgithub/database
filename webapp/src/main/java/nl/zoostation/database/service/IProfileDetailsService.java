package nl.zoostation.database.service;

import nl.zoostation.database.model.view.ProfileView;

/**
 * @author valentinnastasi
 */
public interface IProfileDetailsService {

    ProfileView getDetails(Long id);

    void requestMoreInfo(Long id);

}
