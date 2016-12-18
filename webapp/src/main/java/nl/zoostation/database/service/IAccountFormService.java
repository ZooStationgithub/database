package nl.zoostation.database.service;

import nl.zoostation.database.model.domain.Account;
import nl.zoostation.database.model.form.AccountFormObject;
import nl.zoostation.database.model.form.AccountFormWrapper;

/**
 * @author valentinnastasi
 */
public interface IAccountFormService extends IFormService<Account, Long, AccountFormObject, AccountFormWrapper> {

}
