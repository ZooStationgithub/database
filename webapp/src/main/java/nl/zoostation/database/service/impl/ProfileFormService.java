package nl.zoostation.database.service.impl;

import nl.zoostation.database.annotations.NotNull;
import nl.zoostation.database.dao.IGenericEntityDAO;
import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.form.ProfileFormObject;
import nl.zoostation.database.model.form.ProfileFormWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

import static java.util.stream.Collectors.*;

/**
 * @author valentinnastasi
 */
public class ProfileFormService extends AbstractFormService<Profile, Long, ProfileFormObject, ProfileFormWrapper> {

    private final IGenericEntityDAO<Country, Long> countryDAO;
    private final IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO;
    private final IGenericEntityDAO<CompanyType, Long> companyTypeDAO;
    private final IGenericEntityDAO<ContractType, Long> contractTypeDAO;
    private final IGenericEntityDAO<Framework, Long> frameworkDAO;
    private final IGenericEntityDAO<RankType, Long> rankTypeDAO;
    private final IGenericEntityDAO<RoleType, Long> roleTypeDAO;

    public ProfileFormService(
            IGenericEntityDAO<Profile, Long> profileDAO,
            IGenericEntityDAO<Country, Long> countryDAO,
            IGenericEntityDAO<ProgrammingLanguage, Long> programmingLanguageDAO,
            IGenericEntityDAO<CompanyType, Long> companyTypeDAO,
            IGenericEntityDAO<ContractType, Long> contractTypeDAO,
            IGenericEntityDAO<Framework, Long> frameworkDAO,
            IGenericEntityDAO<RankType, Long> rankTypeDAO,
            IGenericEntityDAO<RoleType, Long> roleTypeDAO) {

        super(profileDAO);
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
    public ProfileFormWrapper prepareForm(Optional<Long> identifier) {
        Profile entity = findOrCreateEntity(identifier);
        ProfileFormObject formObject = new ProfileFormObject();
        entityToForm(entity, formObject);
        ProfileFormWrapper formWrapper = new ProfileFormWrapper();
        formWrapper.setForm(formObject);
        formWrapper.setProgrammingLanguages(programmingLanguageDAO.findAll());
        formWrapper.setCompanyTypes(companyTypeDAO.findAll());
        formWrapper.setContractTypes(contractTypeDAO.findAll());
        formWrapper.setCountries(countryDAO.findAll());
        formWrapper.setFrameworks(frameworkDAO.findAll());
        formWrapper.setRankTypes(rankTypeDAO.findAll());
        formWrapper.setRoleTypes(roleTypeDAO.findAll());
        return formWrapper;
    }

    @Transactional
    @Override
    public Profile save(@NotNull ProfileFormObject formObject) {
        Profile profile = super.save(formObject);
        setCustomFields(profile, formObject.getCustomFields());
        return profile;
    }

    @Override
    protected void entityToForm(Profile entity, ProfileFormObject formObject) {
        formObject.setId(entity.getId());
        formObject.setZsNumber(entity.getZoostationNumber());
        formObject.setAvailability(entity.getAvailability());
        formObject.setEnglishLevel(entity.getEnglishLevel());
        formObject.setExperience(entity.getExperience());
        formObject.setHoursPerWeek(entity.getHoursPerWeek());
        formObject.setPreferredCity(entity.getPreferredCity());
        formObject.setRelocationReason(entity.getRelocationReason());
        formObject.setTestRating(entity.getTestRating());
        formObject.setTravelTime(entity.getTravelTime());
        formObject.setVisaNeeded(entity.getVisaNeeded());
        formObject.setWorkHistory(entity.getWorkHistory());
        formObject.setContractTypeId(entity.getContractType().map(Identifiable::getId).orElse(null));
        formObject.setMainProgrammingLanguageId(entity.getMainProgrammingLanguage().map(Identifiable::getId).orElse(null));
        formObject.setOriginCountryId(entity.getOriginCountry().map(Identifiable::getId).orElse(null));
        formObject.setRankTypeId(entity.getRankType().map(Identifiable::getId).orElse(null));
        formObject.setRoleTypeId(entity.getRoleType().map(Identifiable::getId).orElse(null));
        formObject.setSecondProgrammingLanguageId(entity.getSecondProgrammingLanguage().map(Identifiable::getId).orElse(null));
        formObject.setPreferredCountryIds(entity.getPreferredCountries().stream().map(Identifiable::getId).collect(toList()));
        formObject.setPreferredCompanyTypeIds(entity.getPreferredCompanyTypes().stream().map(Identifiable::getId).collect(toList()));
        formObject.setKnownFrameworkIds(entity.getKnownFrameworks().stream().map(Identifiable::getId).collect(toList()));
        formObject.setCustomFields(entity.getCustomFields().stream().collect(toMap(CustomProfileField::getFieldName, CustomProfileField::getFieldValue)));
    }

    @Override
    protected void formToEntity(ProfileFormObject formObject, Profile entity) {
        if (StringUtils.isEmpty(entity.getZoostationNumber())) {
            entity.setZoostationNumber(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
        }
        entity.setId(formObject.getId());
        entity.setVisaNeeded(formObject.getVisaNeeded());
        entity.setWorkHistory(formObject.getWorkHistory());
        entity.setTestRating(formObject.getTestRating());
        entity.setAvailability(formObject.getAvailability());
        entity.setEnglishLevel(formObject.getEnglishLevel());
        entity.setExperience(formObject.getExperience());
        entity.setHoursPerWeek(formObject.getHoursPerWeek());
        entity.setTravelTime(formObject.getTravelTime());
        entity.setPreferredCity(formObject.getPreferredCity());
        entity.setRelocationReason(formObject.getRelocationReason());
        setScalarField(formObject.getContractTypeId(), contractTypeDAO, entity::setContractType);
        setScalarField(formObject.getMainProgrammingLanguageId(), programmingLanguageDAO, entity::setMainProgrammingLanguage);
        setScalarField(formObject.getSecondProgrammingLanguageId(), programmingLanguageDAO, entity::setSecondProgrammingLanguage);
        setScalarField(formObject.getRoleTypeId(), roleTypeDAO, entity::setRoleType);
        setScalarField(formObject.getOriginCountryId(), countryDAO, entity::setOriginCountry);
        setScalarField(formObject.getRankTypeId(), rankTypeDAO, entity::setRankType);
        setCollectionField(formObject.getKnownFrameworkIds(), frameworkDAO, entity::setKnownFrameworks);
        setCollectionField(formObject.getPreferredCountryIds(), countryDAO, entity::setPreferredCountries);
        setCollectionField(formObject.getPreferredCompanyTypeIds(), companyTypeDAO, entity::setPreferredCompanyTypes);
    }

    private void setCustomFields(Profile profile, Map<String, String> customFields) {
        profile.getCustomFields().clear();
        customFields.forEach((key, value) -> {
            CustomProfileField customProfileField = new CustomProfileField(key, value);
            customProfileField.setProfile(profile);
            profile.getCustomFields().add(customProfileField);
        });
    }

    private <T extends PersistentEntity> void setScalarField(Long id, IGenericEntityDAO<T, Long> dao, Consumer<T> consumer) {
        if (id == null) {
            consumer.accept(null);
            return;
        }
        dao.findOne(id).ifPresent(consumer);
    }

    private <T extends PersistentEntity> void setCollectionField(Collection<Long> ids, IGenericEntityDAO<T, Long> dao, Consumer<Set<T>> consumer) {
        if (CollectionUtils.isEmpty(ids)) {
            consumer.accept(Collections.emptySet());
            return;
        }
        consumer.accept(dao.findMany(ids).stream().collect(toSet()));
    }

}
