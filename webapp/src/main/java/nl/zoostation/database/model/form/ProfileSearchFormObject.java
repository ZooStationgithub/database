package nl.zoostation.database.model.form;

import nl.zoostation.database.model.grid.annotations.From;
import nl.zoostation.database.model.grid.annotations.Select;
import nl.zoostation.database.model.grid.annotations.Where;

import java.util.ArrayList;
import java.util.List;

/**
 * @author valentinnastasi
 */
@Select(
        columns = "DISTINCT profile.id AS id, rank_type.name AS rank, mpl.name AS mainProgrammingLanguage, " +
                "spl.name AS secondProgrammingLanguage, profile.test_rating AS testRating, " +
                "c.name AS originCountry",
        count = "COUNT(DISTINCT profile.id)"
)
@From(
        "profiles profile " +
                "LEFT JOIN preferred_company_types company_type ON profile.id = company_type.profile_id " +
                "LEFT JOIN known_frameworks framework ON profile.id = framework.profile_id " +
                "LEFT JOIN preferred_countries country ON profile.id = country.profile_id " +
                "LEFT JOIN profile_ranks rank ON profile.id = rank.profile_id " +
                "LEFT JOIN profile_roles role ON profile.id = role.profile_id " +
                "LEFT JOIN rank_types rank_type ON rank.rank_type_id = rank_type.id " +
                "LEFT JOIN countries c ON profile.origin_country_id = c.id " +
                "LEFT JOIN programming_languages mpl ON profile.main_pr_lng_id = mpl.id " +
                "LEFT JOIN programming_languages spl ON profile.second_pr_lng_id = spl.id "
)
public class ProfileSearchFormObject {
    @Where("LOWER(profile.zs_number) LIKE '%'||LOWER(:${field})||'%'")
    private String zsNumber;

    @Where("profile.main_pr_lng_id = :${field}")
    private Long mainProgrammingLanguageId;

    @Where("profile.second_pr_lng_id = :${field}")
    private Long secondProgrammingLanguageId;

    @Where("profile.test_rating = :${field}")
    private Integer testRating;

    @Where("profile.origin_country_id = :${field}")
    private Long originCountryId;

    @Where("profile.visa_needed = :${field}")
    private Boolean visaNeeded;

    @Where("profile.experience = :${field}")
    private Integer experience;

    // TODO Is it really needed?
    private String workHistory;

    @Where("profile.english_level = :${field}")
    private Integer englishLevel;

    @Where("profile.travel_time = :${field}")
    private Integer travelTime;

    @Where("LOWER(profile.preferred_city) LIKE '%'||LOWER(:${field})||'%'")
    private String preferredCity;

    @Where("profile.availability = :${field}")
    private Integer availability;

    @Where("profile.hours_per_week = :${field}")
    private Integer hoursPerWeek;

    // TODO Is it really needed?
    private String relocationReason;

    @Where("profile.contract_type_id = :${field}")
    private Long contractTypeId;

    @Where("framework.framework_id IN (:${field})")
    private List<Long> knownFrameworkIds;

    @Where("country.country_id IN (:${field})")
    private List<Long> preferredCountryIds;

    @Where("company_type.company_type_id IN (:${field})")
    private List<Long> preferredCompanyTypeIds;

    @Where("role.role_type_id IN (:${field})")
    private Long roleTypeId;

    @Where("rank.rank_type_id IN (:${field})")
    private Long rankTypeId;

    public ProfileSearchFormObject() {
        knownFrameworkIds = new ArrayList<>();
        preferredCompanyTypeIds = new ArrayList<>();
        preferredCountryIds = new ArrayList<>();
    }

    public String getZsNumber() {
        return zsNumber;
    }

    public void setZsNumber(String zsNumber) {
        this.zsNumber = zsNumber;
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

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("ProfileSearchFormObject{");
        sb.append("zsNumber='").append(zsNumber).append('\'');
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
        sb.append('}');
        return sb.toString();
    }
}
