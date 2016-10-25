package nl.zoostation.database.dao.impl;

import nl.zoostation.database.dao.IProfileViewDAO;
import nl.zoostation.database.model.domain.CustomProfileField;
import nl.zoostation.database.model.view.ProfileView;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;

/**
 * @author valentinnastasi
 */
@Repository
public class ProfileViewDAO extends SessionAwareDAO implements IProfileViewDAO {

    @Autowired
    public ProfileViewDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<ProfileView> findById(Long id) {
        ProfileView profileView = getSession().get(ProfileView.class, id);

        if (profileView != null) {
            profileView.setAdditionalInfo(loadCustomFields(id));
        }

        return Optional.ofNullable(profileView);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<ProfileView> findByZSNumber(String zsNumber) {
        Optional<ProfileView> profileView = getSession().createQuery("from " + ProfileView.class.getSimpleName() + " where zoostationNumber = :zsNumber")
                .setParameter("zsNumber", zsNumber).uniqueResultOptional();
        if (!profileView.isPresent()) {
            return profileView;
        }

        ProfileView profileViewUnwrapped = profileView.get();
        profileViewUnwrapped.setAdditionalInfo(loadCustomFields(profileViewUnwrapped.getId()));

        return Optional.of(profileViewUnwrapped);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> loadCustomFields(Long profileId) {
        List<CustomProfileField> customFields = getSession().createQuery("from " + CustomProfileField.class.getSimpleName() + " where profile.id = :id")
                .setParameter("id", profileId).list();
        return customFields.stream().collect(toMap(CustomProfileField::getFieldName, CustomProfileField::getFieldValue));
    }
}
