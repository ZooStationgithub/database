package nl.zoostation.database.service.impl;

import nl.zoostation.database.dao.*;
import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.form.ProfileForm;
import nl.zoostation.database.model.form.ProfileFormContainer;
import nl.zoostation.database.model.input.SearchToken;
import nl.zoostation.database.service.IProfileFormService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.taglibs.standard.extra.spath.Token;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * @author valentinnastasi
 */
@Service
public class ProfileFormService implements IProfileFormService {

    private static final Logger logger = LogManager.getLogger(ProfileFormService.class);

    private final IProfileDAO profileDAO;
    private final ICountryDAO countryDAO;
    private final IProgrammingLanguageDAO programmingLanguageDAO;
    private final ICompanyTypeDAO companyTypeDAO;
    private final IContractTypeDAO contractTypeDAO;
    private final IFrameworkDAO frameworkDAO;
    private final IRankTypeDAO rankTypeDAO;
    private final IRoleTypeDAO roleTypeDAO;

    public ProfileFormService(
            IProfileDAO profileDAO,
            ICountryDAO countryDAO,
            IProgrammingLanguageDAO programmingLanguageDAO,
            ICompanyTypeDAO companyTypeDAO,
            IContractTypeDAO contractTypeDAO,
            IFrameworkDAO frameworkDAO,
            IRankTypeDAO rankTypeDAO,
            IRoleTypeDAO roleTypeDAO) {

        this.profileDAO = profileDAO;
        this.countryDAO = countryDAO;
        this.programmingLanguageDAO = programmingLanguageDAO;
        this.companyTypeDAO = companyTypeDAO;
        this.contractTypeDAO = contractTypeDAO;
        this.frameworkDAO = frameworkDAO;
        this.rankTypeDAO = rankTypeDAO;
        this.roleTypeDAO = roleTypeDAO;
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileFormContainer prepareForm(Optional<Long> profileId) {
        logger.debug("Initializing developer form for id {}", profileId);
        ProfileFormContainer profileFormContainer = new ProfileFormContainer();
        profileFormContainer.setCompanyTypes(companyTypeDAO.findAll());
        profileFormContainer.setRoleTypes(roleTypeDAO.findAll());
        profileFormContainer.setRankTypes(rankTypeDAO.findAll());
        profileFormContainer.setProgrammingLanguages(programmingLanguageDAO.findAll());
        profileFormContainer.setFrameworks(frameworkDAO.findAll());
        profileFormContainer.setContractTypes(contractTypeDAO.findAll());

        if (profileId.isPresent()) {
            ProfileForm profileForm = prePopulateForm(profileId.get());
            profileFormContainer.setProfileForm(profileForm);

            if (profileForm.getOriginCountryId() != null) {
                countryDAO.findOne(profileForm.getOriginCountryId()).ifPresent(c -> {
                    SearchToken searchToken = new SearchToken(c.getId(), c.getName());
                    profileFormContainer.setSelectedOriginCountry(Collections.singletonList(searchToken));
                });
            } else {
                profileFormContainer.setSelectedOriginCountry(Collections.emptyList());
            }

            if (CollectionUtils.isNotEmpty(profileForm.getPreferredCountryIds())) {
                List<SearchToken> tokens = countryDAO.findMany(profileForm.getPreferredCountryIds()).stream()
                        .map(c -> new SearchToken(c.getId(), c.getName()))
                        .collect(toList());
                profileFormContainer.setSelectedPreferredCountries(tokens);
            } else {
                profileFormContainer.setSelectedPreferredCountries(Collections.emptyList());
            }

        } else {
            profileFormContainer.setProfileForm(new ProfileForm());
        }

        return profileFormContainer;
    }

    @Transactional
    @Override
    public void saveProfile(ProfileForm profileForm) {
        Objects.requireNonNull(profileForm);
        logger.debug("Saving the profile");

        Profile profile;
        if (profileForm.getId() != null) {
            Optional<Profile> profileWrapped = profileDAO.findOne(profileForm.getId());
            if (!profileWrapped.isPresent()) {
                throw new IllegalStateException("Profile with id " + profileForm.getId() + " does not exist");
            }
            profile = profileWrapped.get();
        } else {
            profile = new Profile();
            profile.setZoostationNumber(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        }

        profile.setVisaNeeded(profileForm.getVisaNeeded());
        profile.setTravelTime(profileForm.getTravelTime());
        profile.setAvailability(profileForm.getAvailability());
        setComplexField(profileForm.getContractTypeId(), contractTypeDAO, profile::setContractType);
        profile.setEnglishLevel(profileForm.getEnglishLevel());
        profile.setExperience(profileForm.getExperience());
        profile.setHoursPerWeek(profileForm.getHoursPerWeek());
        setCollectionField(profileForm.getKnownFrameworkIds(), frameworkDAO, profile::setKnownFrameworks);
        setComplexField(profileForm.getOriginCountryId(), countryDAO, profile::setOriginCountry);
        profile.setPreferredCity(profileForm.getPreferredCity());
        setCollectionField(profileForm.getPreferredCompanyTypeIds(), companyTypeDAO, profile::setPreferredCompanyTypes);
        setCollectionField(profileForm.getPreferredCountryIds(), countryDAO, profile::setPreferredCountries);
        setComplexField(profileForm.getRankTypeId(), rankTypeDAO, profile::setRankType);
        profile.setRelocationReason(profileForm.getRelocationReason());
        setComplexField(profileForm.getRoleTypeId(), roleTypeDAO, profile::setRoleType);
        setComplexField(profileForm.getSecondProgrammingLanguageId(), programmingLanguageDAO, profile::setSecondProgrammingLanguage);
        setComplexField(profileForm.getMainProgrammingLanguageId(), programmingLanguageDAO, profile::setMainProgrammingLanguage);
        profile.setTestRating(profileForm.getTestRating());
        profile.setWorkHistory(profileForm.getWorkHistory());

        Profile savedProfile = profileDAO.save(profile);
        savedProfile.getCustomFields().clear();

        profileForm.getCustomFields().forEach((key, value) -> {
            CustomProfileField customProfileField = new CustomProfileField(key, value);
            customProfileField.setProfile(savedProfile);
            savedProfile.getCustomFields().add(customProfileField);
        });

    }

    @Transactional
    @Override
    public void delete(Long id) {
        Objects.requireNonNull(id);
        logger.debug("Deleting profile with id {}", id);
        profileDAO.delete(id);
    }

    private ProfileForm prePopulateForm(Long id) {
        Optional<Profile> profileWrapped = profileDAO.findOne(id);
        if (!profileWrapped.isPresent()) {
            throw new IllegalStateException("Profile with id " + id + " does not exist");
        }
        Profile profile = profileWrapped.get();
        ProfileForm profileForm = new ProfileForm();
        profileForm.setId(profile.getId());
        profileForm.setZsNumber(profile.getZoostationNumber());
        profileForm.setAvailability(profile.getAvailability());
        profileForm.setContractTypeId(profile.getContractType().map(ContractType::getId).orElse(null));
        profileForm.setEnglishLevel(profile.getEnglishLevel());
        profileForm.setExperience(profile.getExperience());
        profileForm.setHoursPerWeek(profile.getHoursPerWeek());
        profileForm.setKnownFrameworkIds(profile.getKnownFrameworks().stream().map(Framework::getId).collect(toList()));
        profileForm.setMainProgrammingLanguageId(profile.getMainProgrammingLanguage().map(ProgrammingLanguage::getId).orElse(null));
        profileForm.setOriginCountryId(profile.getOriginCountry().map(Country::getId).orElse(null));
        profileForm.setPreferredCity(profile.getPreferredCity());
        profileForm.setPreferredCompanyTypeIds(profile.getPreferredCompanyTypes().stream().map(CompanyType::getId).collect(toList()));
        profileForm.setPreferredCountryIds(profile.getPreferredCountries().stream().map(Country::getId).collect(toList()));
        profileForm.setRankTypeId(profile.getRankType().map(RankType::getId).orElse(null));
        profileForm.setRelocationReason(profile.getRelocationReason());
        profileForm.setRoleTypeId(profile.getRoleType().map(RoleType::getId).orElse(null));
        profileForm.setSecondProgrammingLanguageId(profile.getSecondProgrammingLanguage().map(ProgrammingLanguage::getId).orElse(null));
        profileForm.setTestRating(profile.getTestRating());
        profileForm.setTravelTime(profile.getTravelTime());
        profileForm.setVisaNeeded(profile.getVisaNeeded());
        profileForm.setWorkHistory(profile.getWorkHistory());
        profileForm.setCustomFields(profile.getCustomFields().stream().collect(toMap(CustomProfileField::getFieldName, CustomProfileField::getFieldValue)));
        return profileForm;
    }

    private <F extends PersistentEntity> void setComplexField(Long id, IGenericDAO<F, Long> dao, Consumer<F> consumer) {
        if (id == null) {
            consumer.accept(null);
            return;
        }
        dao.findOne(id).ifPresent(consumer);
    }

    private <F extends PersistentEntity> void setCollectionField(Collection<Long> ids, IGenericDAO<F, Long> dao, Consumer<Set<F>> consumer) {
        if (CollectionUtils.isEmpty(ids)) {
            consumer.accept(Collections.emptySet());
            return;
        }
        consumer.accept(dao.findMany(ids).stream().collect(toSet()));
    }

}
