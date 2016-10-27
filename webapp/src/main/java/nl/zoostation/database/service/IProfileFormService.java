package nl.zoostation.database.service;

import nl.zoostation.database.model.form.ProfileForm;
import nl.zoostation.database.model.form.ProfileFormContainer;

import java.util.Optional;

/**
 * @author valentinnastasi
 */
public interface IProfileFormService {

    ProfileFormContainer prepareForm(Optional<Long> profileId);

    void saveProfile(ProfileForm profileForm);

    void delete(Long id);

}
