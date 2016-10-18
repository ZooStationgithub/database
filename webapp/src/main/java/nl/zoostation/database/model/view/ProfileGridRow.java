package nl.zoostation.database.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author valentinnastasi
 */
@Entity
@Table(name = "profile_search_view")
public class ProfileGridRow implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "zs_number")
    private String zoostationNumber;

    @Column(name = "ranks")
    private String rank;

    @Column(name = "main_programming_language")
    private String mainProgrammingLanguage;

    @Column(name = "second_programming_language")
    private String secondProgrammingLanguage;

    @Column(name = "frameworks")
    private String frameworks;

    @Column(name = "rating")
    private String testRating;

    @Column(name = "origin_country")
    private String originCountry;

    @Column(name = "preferred_countries")
    private String preferredCountries;

    @Column(name = "contract_type")
    private String contractType;

    @Column(name = "visa_needed")
    private Boolean visaNeeded;

    @Column(name = "preferred_company_types")
    private String preferredCompanyTypes;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "roles")
    private String roles;

    @Column(name = "work_history")
    private String workHistory;

    @Column(name = "english_level")
    private Integer englishLevel;

    @Column(name = "travel_time")
    private Integer travelTime;

    @Column(name = "preferred_city")
    private String preferredCity;

    @Column(name = "availability")
    private Integer availability;

    @Column(name = "hours_per_week")
    private Integer hoursPerWeek;

    @Column(name = "relocation_reason")
    private String relocationReason;

    public ProfileGridRow() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getZoostationNumber() {
        return zoostationNumber;
    }

    public void setZoostationNumber(String zoostationNumber) {
        this.zoostationNumber = zoostationNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMainProgrammingLanguage() {
        return mainProgrammingLanguage;
    }

    public void setMainProgrammingLanguage(String mainProgrammingLanguage) {
        this.mainProgrammingLanguage = mainProgrammingLanguage;
    }

    public String getSecondProgrammingLanguage() {
        return secondProgrammingLanguage;
    }

    public void setSecondProgrammingLanguage(String secondProgrammingLanguage) {
        this.secondProgrammingLanguage = secondProgrammingLanguage;
    }

    public String getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(String frameworks) {
        this.frameworks = frameworks;
    }

    public String getTestRating() {
        return testRating;
    }

    public void setTestRating(String testRating) {
        this.testRating = testRating;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getPreferredCountries() {
        return preferredCountries;
    }

    public void setPreferredCountries(String preferredCountries) {
        this.preferredCountries = preferredCountries;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Boolean getVisaNeeded() {
        return visaNeeded;
    }

    public void setVisaNeeded(Boolean visaNeeded) {
        this.visaNeeded = visaNeeded;
    }

    public String getPreferredCompanyTypes() {
        return preferredCompanyTypes;
    }

    public void setPreferredCompanyTypes(String preferredCompanyTypes) {
        this.preferredCompanyTypes = preferredCompanyTypes;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfileGridRow that = (ProfileGridRow) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(zoostationNumber, that.zoostationNumber) &&
                Objects.equals(rank, that.rank) &&
                Objects.equals(mainProgrammingLanguage, that.mainProgrammingLanguage) &&
                Objects.equals(secondProgrammingLanguage, that.secondProgrammingLanguage) &&
                Objects.equals(frameworks, that.frameworks) &&
                Objects.equals(testRating, that.testRating) &&
                Objects.equals(originCountry, that.originCountry) &&
                Objects.equals(preferredCountries, that.preferredCountries) &&
                Objects.equals(contractType, that.contractType) &&
                Objects.equals(visaNeeded, that.visaNeeded) &&
                Objects.equals(preferredCompanyTypes, that.preferredCompanyTypes) &&
                Objects.equals(experience, that.experience) &&
                Objects.equals(roles, that.roles) &&
                Objects.equals(workHistory, that.workHistory) &&
                Objects.equals(englishLevel, that.englishLevel) &&
                Objects.equals(travelTime, that.travelTime) &&
                Objects.equals(preferredCity, that.preferredCity) &&
                Objects.equals(availability, that.availability) &&
                Objects.equals(hoursPerWeek, that.hoursPerWeek) &&
                Objects.equals(relocationReason, that.relocationReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, zoostationNumber, rank, mainProgrammingLanguage, secondProgrammingLanguage, frameworks,
                testRating, originCountry, preferredCountries, contractType, visaNeeded, preferredCompanyTypes, experience,
                roles, workHistory, englishLevel, travelTime, preferredCity, availability, hoursPerWeek, relocationReason);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfileGridRow{");
        sb.append("id=").append(id);
        sb.append(", zoostationNumber='").append(zoostationNumber).append('\'');
        sb.append(", rank='").append(rank).append('\'');
        sb.append(", mainProgrammingLanguage='").append(mainProgrammingLanguage).append('\'');
        sb.append(", secondProgrammingLanguage='").append(secondProgrammingLanguage).append('\'');
        sb.append(", frameworks='").append(frameworks).append('\'');
        sb.append(", testRating='").append(testRating).append('\'');
        sb.append(", originCountry='").append(originCountry).append('\'');
        sb.append(", preferredCountries='").append(preferredCountries).append('\'');
        sb.append(", contractType='").append(contractType).append('\'');
        sb.append(", visaNeeded=").append(visaNeeded);
        sb.append(", preferredCompanyTypes='").append(preferredCompanyTypes).append('\'');
        sb.append(", experience=").append(experience);
        sb.append(", roles='").append(roles).append('\'');
        sb.append(", workHistory='").append(workHistory).append('\'');
        sb.append(", englishLevel=").append(englishLevel);
        sb.append(", travelTime=").append(travelTime);
        sb.append(", preferredCity='").append(preferredCity).append('\'');
        sb.append(", availability=").append(availability);
        sb.append(", hoursPerWeek=").append(hoursPerWeek);
        sb.append(", relocationReason='").append(relocationReason).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
