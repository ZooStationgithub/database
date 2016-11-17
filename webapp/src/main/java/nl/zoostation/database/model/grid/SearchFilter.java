package nl.zoostation.database.model.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author valentinnastasi
 */
public class SearchFilter {

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

    public SearchFilter() {
        knownFrameworkIds = new ArrayList<>();
        preferredCompanyTypeIds = new ArrayList<>();
        preferredCountryIds = new ArrayList<>();
    }

    /*
     * Assume:
     * profile table - {profile}
     * frameworks table - {frameworks}
     * countries table - {countries}
     * companies table - {companies}
     * roles table - {roles}
     */

    @Path("profile.zs_number")
    public String getZsNumber() {
        return zsNumber;
    }

    public void setZsNumber(String zsNumber) {
        this.zsNumber = zsNumber;
    }

    @Path("profile.main_pr_lng_id")
    public Long getMainProgrammingLanguageId() {
        return mainProgrammingLanguageId;
    }

    public void setMainProgrammingLanguageId(Long mainProgrammingLanguageId) {
        this.mainProgrammingLanguageId = mainProgrammingLanguageId;
    }

    @Path("profile.second_pr_lng_id")
    public Long getSecondProgrammingLanguageId() {
        return secondProgrammingLanguageId;
    }

    public void setSecondProgrammingLanguageId(Long secondProgrammingLanguageId) {
        this.secondProgrammingLanguageId = secondProgrammingLanguageId;
    }

    @Path("profile.test_rating")
    public Integer getTestRating() {
        return testRating;
    }

    public void setTestRating(Integer testRating) {
        this.testRating = testRating;
    }

    @Path("profile.origin_country_id")
    public Long getOriginCountryId() {
        return originCountryId;
    }

    public void setOriginCountryId(Long originCountryId) {
        this.originCountryId = originCountryId;
    }

    @Path("profile.visa_needed")
    public Boolean getVisaNeeded() {
        return visaNeeded;
    }

    public void setVisaNeeded(Boolean visaNeeded) {
        this.visaNeeded = visaNeeded;
    }

    @Path("profile.experience")
    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    @Path(value = "profile.work_history", exactMatch = false)
    public String getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(String workHistory) {
        this.workHistory = workHistory;
    }

    @Path("profile.english_level")
    public Integer getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(Integer englishLevel) {
        this.englishLevel = englishLevel;
    }

    @Path("profile.travel_time")
    public Integer getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Integer travelTime) {
        this.travelTime = travelTime;
    }

    @Path(value = "profile.preferred_city", exactMatch = false)
    public String getPreferredCity() {
        return preferredCity;
    }

    public void setPreferredCity(String preferredCity) {
        this.preferredCity = preferredCity;
    }

    @Path("profile.availability")
    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    @Path("profile.hours_per_week")
    public Integer getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(Integer hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    @Path(value = "profile.relocation_reason", exactMatch = false)
    public String getRelocationReason() {
        return relocationReason;
    }

    public void setRelocationReason(String relocationReason) {
        this.relocationReason = relocationReason;
    }

    @Path("profile.contract_type_id")
    public Long getContractTypeId() {
        return contractTypeId;
    }

    public void setContractTypeId(Long contractTypeId) {
        this.contractTypeId = contractTypeId;
    }

    @Path(value = "frameworks.framework_id", collection = true)
    public List<Long> getKnownFrameworkIds() {
        return knownFrameworkIds;
    }

    public void setKnownFrameworkIds(List<Long> knownFrameworkIds) {
        this.knownFrameworkIds = knownFrameworkIds;
    }

    @Path(value = "countries.country_id", collection = true)
    public List<Long> getPreferredCountryIds() {
        return preferredCountryIds;
    }

    public void setPreferredCountryIds(List<Long> preferredCountryIds) {
        this.preferredCountryIds = preferredCountryIds;
    }

    @Path(value = "companies.company_id", collection = true)
    public List<Long> getPreferredCompanyTypeIds() {
        return preferredCompanyTypeIds;
    }

    public void setPreferredCompanyTypeIds(List<Long> preferredCompanyTypeIds) {
        this.preferredCompanyTypeIds = preferredCompanyTypeIds;
    }

    @Path("roles.role_id")
    public Long getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(Long roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    @Path("ranks.rank_id")
    public Long getRankTypeId() {
        return rankTypeId;
    }

    public void setRankTypeId(Long rankTypeId) {
        this.rankTypeId = rankTypeId;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder("SearchFilter{");
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
