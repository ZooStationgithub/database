package nl.zoostation.database.model.form;

import nl.zoostation.database.model.domain.AccountGroup;

import java.util.List;

/**
 * @author valentinnastasi
 */
public class AccountFormWrapper implements IFormWrapper<AccountFormObject> {

    private AccountFormObject formObject;
    private List<AccountGroup> accountGroups;

    public AccountFormWrapper() {
    }

    @Override
    public AccountFormObject getForm() {
        return formObject;
    }

    public void setForm(AccountFormObject form) {
        this.formObject = form;
    }

    public List<AccountGroup> getAccountGroups() {
        return accountGroups;
    }

    public void setAccountGroups(List<AccountGroup> accountGroups) {
        this.accountGroups = accountGroups;
    }
}
