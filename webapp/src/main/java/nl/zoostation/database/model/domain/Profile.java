package nl.zoostation.database.model.domain;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * @author valentinnastasi
 */
@Entity
@Table(name = "profiles")
public class Profile extends Identifiable {

    @Column(name = "zs_number", length = 32)
    private String zoostationNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_pr_lng_id")
    private ProgrammingLanguage mainProgrammingLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "second_pr_lng_id")
    private ProgrammingLanguage secondProgrammingLanguage;

    @Column(name = "test_rating")
    private Integer testRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_country_id")
    private Country originCountry;

    @Column(name = "visa_needed")
    private Boolean visaNeeded;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "work_history", length = 500)
    private String workHistory;

    @Column(name = "english_level")
    private Integer englishLevel;

    @Column(name = "travel_time")
    private Integer travelTime;

    @Column(name = "preferred_city", length = 45)
    private String preferredCity;

    @Column(name = "availability")
    private Integer availability;

    @Column(name = "hours_per_week")
    private Integer hoursPerWeek;

    @Column(name = "relocation_reason", length = 500)
    private String relocationReason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_type_id")
    private ContractType contractType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "known_frameworks", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "framework_id"))
    @Cascade(CascadeType.ALL)
    private Set<Framework> knownFrameworks = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "preferred_countries", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
    @Cascade(CascadeType.ALL)
    private Set<Country> preferredCountries = new HashSet<>();


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "preferred_company_types", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "company_type_id"))
    @Cascade(CascadeType.ALL)
    private Set<CompanyType> preferredCompanyTypes = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "profile_ranks", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "rank_type_id"))
    //@Cascade(CascadeType.ALL)
    private RankType rankType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "profile_roles", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "role_type_id"))
    //@Cascade(CascadeType.ALL)
    private RoleType roleType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "profile")
    @Cascade(CascadeType.ALL)
    private Set<CustomProfileField> customFields = new HashSet<>();

    public Profile() {
    }

    public Profile(Long id) {
        super(id);
    }

    public String getZoostationNumber() {
        return zoostationNumber;
    }

    public void setZoostationNumber(String zoostationNumber) {
        this.zoostationNumber = zoostationNumber;
    }

    public Optional<ProgrammingLanguage> getMainProgrammingLanguage() {
        return Optional.ofNullable(mainProgrammingLanguage);
    }

    public void setMainProgrammingLanguage(ProgrammingLanguage mainProgrammingLanguage) {
        this.mainProgrammingLanguage = mainProgrammingLanguage;
    }

    public Optional<ProgrammingLanguage> getSecondProgrammingLanguage() {
        return Optional.ofNullable(secondProgrammingLanguage);
    }

    public void setSecondProgrammingLanguage(ProgrammingLanguage secondProgrammingLanguage) {
        this.secondProgrammingLanguage = secondProgrammingLanguage;
    }

    public Integer getTestRating() {
        return testRating;
    }

    public void setTestRating(Integer testRating) {
        this.testRating = testRating;
    }

    public Optional<Country> getOriginCountry() {
        return Optional.ofNullable(originCountry);
    }

    public void setOriginCountry(Country originCountry) {
        this.originCountry = originCountry;
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

    public Optional<ContractType> getContractType() {
        return Optional.ofNullable(contractType);
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }

    public Set<Framework> getKnownFrameworks() {
        return knownFrameworks;
    }

    public void setKnownFrameworks(Set<Framework> knownFrameworks) {
        this.knownFrameworks.clear();
        this.knownFrameworks.addAll(knownFrameworks);
    }

    public Set<Country> getPreferredCountries() {
        return preferredCountries;
    }

    public void setPreferredCountries(Set<Country> preferredCountries) {
        this.preferredCountries.clear();
        this.preferredCountries.addAll(preferredCountries);
    }

    public Set<CompanyType> getPreferredCompanyTypes() {
        return preferredCompanyTypes;
    }

    public void setPreferredCompanyTypes(Set<CompanyType> preferredCompanyTypes) {
        this.preferredCompanyTypes.clear();
        this.preferredCompanyTypes.addAll(preferredCompanyTypes);
    }

    public Optional<RankType> getRankType() {
        return Optional.ofNullable(rankType);
    }

    public void setRankType(RankType rankType) {
        this.rankType = rankType;
    }

    public Optional<RoleType> getRoleType() {
        return Optional.ofNullable(roleType);
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Set<CustomProfileField> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Set<CustomProfileField> customFields) {
        this.customFields.clear();
        this.customFields.addAll(customFields);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Profile profile = (Profile) o;
        return Objects.equals(zoostationNumber, profile.zoostationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), zoostationNumber);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Profile{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("zoostationNumber='").append(zoostationNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
