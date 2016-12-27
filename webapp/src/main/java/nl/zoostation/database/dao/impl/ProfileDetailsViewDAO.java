package nl.zoostation.database.dao.impl;

import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.dao.IGenericReadOnlyEntityDAO;
import nl.zoostation.database.model.domain.CustomProfileField;
import nl.zoostation.database.model.view.ProfileView;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toMap;
import static nl.zoostation.database.app.Constants.Parameters.ID;

/**
 * @author valentinnastasi
 */
public class ProfileDetailsViewDAO extends SimpleGenericReadOnlyEntityDAO<ProfileView, Long> implements IGenericReadOnlyEntityDAO<ProfileView, Long> {

    private static final String QUERY_FOR_CUSTOM_FIELDS = "from {0} where profile.id = :{1}";

    public ProfileDetailsViewDAO(SessionFactory sessionFactory) {
        super(sessionFactory, ProfileView.class, Long.class);
    }

    @Override
    public Optional<ProfileView> findOne(@NotNull Long id) {
        logger.debug("Find profile view by ID {}", id);
        ProfileView profileView = getSession().get(ProfileView.class, id);

        if (profileView != null) {
            profileView.setAdditionalInfo(loadCustomFields(id));
        }

        Optional<ProfileView> profileViewOptional = Optional.ofNullable(profileView);
        logger.trace("View found: {}", profileViewOptional);
        return profileViewOptional;
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> loadCustomFields(Long profileId) {
        List<CustomProfileField> customFields = getSession().createQuery(interpolate(QUERY_FOR_CUSTOM_FIELDS, CustomProfileField.class.getSimpleName(), ID))
                .setParameter(ID, profileId).list();
        return customFields.stream().collect(toMap(CustomProfileField::getFieldName, CustomProfileField::getFieldValue));
    }
}
