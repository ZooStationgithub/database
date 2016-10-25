package nl.zoostation.database.dao;

import nl.zoostation.database.model.view.ProfileView;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IProfileViewDAO {

    Optional<ProfileView> findById(Long id);

    Optional<ProfileView> findByZSNumber(String zsNumber);

}
