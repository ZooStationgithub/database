package nl.zoostation.database.model.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author valentinnastasi
 */
public class ProfileForm {

    private Long id;
    private String zsNumber;
    private Long mainProgrammingLanguageId;
    private Long secondProgrammingLanguageId;
    private Integer testRating;
    private Long originCountryId;
    private Boolean visaNeeded;
    private Integer experience;
    private String workHistory;
    private Integer englishLevel;
    private Integer travelTime;
    private String preferredCity;
    private Integer availability;
    private Integer hoursPerWeek;
    private String relocationReason;
    private Long contractTypeId;
    private List<Long> knownFrameworkIds;
    private List<Long> preferredCountryIds;
    private List<Long> preferredCompanyTypeIds;
    private Long roleTypeId;
    private Long rankTypeId;
    private Map<String, String> customFields;

    public ProfileForm() {
        knownFrameworkIds = new ArrayList<>();
        preferredCompanyTypeIds = new ArrayList<>();
        preferredCountryIds = new ArrayList<>();
        customFields = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMainProgrammingLanguageId() {
        return mainProgrammingLanguageId;
    }

    public void setMainProgrammingLanguageId(Long mainProgrammingLanguageId) {
        this.mainProgrammingLanguageId = mainProgrammingLanguageId;
    }

    public Long getSecondProgrammingLanguageId() {
        return secondProgrammingLanguageId;
    }

    public void setSecondProgrammingLanguageId(Long secondProgrammingLanguageId) {
        this.secondProgrammingLanguageId = secondProgrammingLanguageId;
    }

    public Integer getTestRating() {
        return testRating;
    }

    public void setTestRating(Integer testRating) {
        this.testRating = testRating;
    }

    public Long getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(Long originCountryId) {
        this.originCountryId = originCountryId;
    }

    public Boolean getVisaNeeded() {
        return visaNeeded;
    }

    public void setVisaNeeded(Boolean visaNeeded) {
        this.visaNeeded = visaNeeded;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(String workHistory) {
        this.workHistory = workHistory;
    }

    public Integer getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(Integer englishLevel) {
        this.englishLevel = englishLevel;
    }

    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    public String getPreferredCity() {
        return preferredCity;
    }

    public void setPreferredCity(String preferredCity) {
        this.preferredCity = preferredCity;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public String getRelocationReason() {
        return relocationReason;
    }

    public void setRelocationReason(String relocationReason) {
        this.relocationReason = relocationReason;
    }

    public Long getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Long contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    public List<Long> getKnownFrameworkIds() {
        return knownFrameworkIds;
    }

    public void setKnownFrameworkIds(List<Long> knownFrameworkIds) {
        this.knownFrameworkIds = knownFrameworkIds;
    }

    public List<Long> getPreferredCountryIds() {
        return preferredCountryIds;
    }

    public void setPreferredCountryIds(List<Long> preferredCountryIds) {
        this.preferredCountryIds = preferredCountryIds;
    }

    public List<Long> getPreferredCompanyTypeIds() {
        return preferredCompanyTypeIds;
    }

    public void setPreferredCompanyTypeIds(List<Long> preferredCompanyTypeIds) {
        this.preferredCompanyTypeIds = preferredCompanyTypeIds;
    }

    public Long getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(Long roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public Long getRankTypeId() {
        return rankTypeId;
    }

    public void setRankTypeId(Long rankTypeId) {
        this.rankTypeId = rankTypeId;
    }

    public Map<String, String> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Map<String, String> customFields) {
        this.customFields = customFields;
    }

    public String getZsNumber() {
        return zsNumber;
    }

    public void setZsNumber(String zsNumber) {
        this.zsNumber = zsNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfileForm{");
        sb.append("id=").append(id);
        sb.append(", zsNumber='").append(zsNumber).append('\'');
        sb.append(", mainProgrammingLanguageId=").append(mainProgrammingLanguageId);
        sb.append(", secondProgrammingLanguageId=").append(secondProgrammingLanguageId);
        sb.append(", testRating=").append(testRating);
        sb.append(", originCountryId=").append(originCountryId);
        sb.append(", visaNeeded=").append(visaNeeded);
        sb.append(", experience=").append(experience);
        sb.append(", workHistory='").append(workHistory).append('\'');
        sb.append(", englishLevel=").append(englishLevel);
        sb.append(", travelTime=").append(travelTime);
        sb.append(", preferredCity='").append(preferredCity).append('\'');
        sb.append(", availability=").append(availability);
        sb.append(", hoursPerWeek=").append(hoursPerWeek);
        sb.append(", relocationReason='").append(relocationReason).append('\'');
        sb.append(", contractTypeId=").append(contractTypeId);
        sb.append(", knownFrameworkIds=").append(knownFrameworkIds);
        sb.append(", preferredCountryIds=").append(preferredCountryIds);
        sb.append(", preferredCompanyTypeIds=").append(preferredCompanyTypeIds);
        sb.append(", roleTypeId=").append(roleTypeId);
        sb.append(", rankTypeId=").append(rankTypeId);
        sb.append(", customFields=").append(customFields);
        sb.append('}');
        return sb.toString();
    }
}
