package nl.zoostation.database.model.form;

import nl.zoostation.database.model.domain.*;

import java.util.List;

/**
 * @author valentinnastasi
 */
public class ProfileFormWrapper implements IFormWrapper<ProfileFormObject> {

    private ProfileFormObject form;
    private List<ProgrammingLanguage> programmingLanguages;
    private List<Country> countries;
    private List<Framework> frameworks;
    private List<CompanyType> companyTypes;
    private List<RoleType> roleTypes;
    private List<RankType> rankTypes;
    private List<ContractType> contractTypes;

    @Override
    public ProfileFormObject getForm() {
        return form;
    }

    public void setForm(ProfileFormObject form) {
        this.form = form;
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
}
