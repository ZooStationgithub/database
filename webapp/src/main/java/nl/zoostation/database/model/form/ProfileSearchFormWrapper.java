package nl.zoostation.database.model.form;

import nl.zoostation.database.model.domain.*;
import nl.zoostation.database.model.input.SearchToken;

import java.util.ArrayList;
import java.util.List;

/**
 * @author valentinnastasi
 */
public class ProfileSearchFormWrapper {

    private ProfileSearchFormObject formObject;
    private List<ProgrammingLanguage> programmingLanguages;
    private List<Country> countries;
    private List<Framework> frameworks;
    private List<CompanyType> companyTypes;
    private List<RoleType> roleTypes;
    private List<RankType> rankTypes;
    private List<ContractType> contractTypes;
    private List<SearchToken> selectedOriginCountry;
    private List<SearchToken> selectedPreferredCountries;

    public ProfileSearchFormWrapper() {
        programmingLanguages = new ArrayList<>();
        countries = new ArrayList<>();
        frameworks = new ArrayList<>();
        companyTypes = new ArrayList<>();
        roleTypes = new ArrayList<>();
        rankTypes = new ArrayList<>();
        contractTypes = new ArrayList<>();
    }

    public List<ProgrammingLanguage> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<ProgrammingLanguage> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<Framework> getFrameworks() {
        return frameworks;
    }

    public void setFrameworks(List<Framework> frameworks) {
        this.frameworks = frameworks;
    }

    public List<CompanyType> getCompanyTypes() {
        return companyTypes;
    }

    public void setCompanyTypes(List<CompanyType> companyTypes) {
        this.companyTypes = companyTypes;
    }

    public List<RoleType> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(List<RoleType> roleTypes) {
        this.roleTypes = roleTypes;
    }

    public List<RankType> getRankTypes() {
        return rankTypes;
    }

    public void setRankTypes(List<RankType> rankTypes) {
        this.rankTypes = rankTypes;
    }

    public List<ContractType> getContractTypes() {
        return contractTypes;
    }

    public void setContractTypes(List<ContractType> contractTypes) {
        this.contractTypes = contractTypes;
    }

    public List<SearchToken> getSelectedOriginCountry() {
        return selectedOriginCountry;
    }

    public void setSelectedOriginCountry(List<SearchToken> selectedOriginCountry) {
        this.selectedOriginCountry = selectedOriginCountry;
    }

    public List<SearchToken> getSelectedPreferredCountries() {
        return selectedPreferredCountries;
    }

    public void setSelectedPreferredCountries(List<SearchToken> selectedPreferredCountries) {
        this.selectedPreferredCountries = selectedPreferredCountries;
    }

    public ProfileSearchFormObject getForm() {
        return formObject;
    }

    public void setForm(ProfileSearchFormObject formObject) {
        this.formObject = formObject;
    }
}
