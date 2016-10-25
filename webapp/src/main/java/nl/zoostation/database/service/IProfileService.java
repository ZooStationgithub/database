package nl.zoostation.database.service;

import nl.zoostation.database.model.view.ProfileView;

/**
 * @author valentinnastasi
 */
public interface IProfileService {

    ProfileView findById(Long id);

    ProfileView findByZsNumber(String zsNumber);

    void requestMoreInfo(Long id);

}
