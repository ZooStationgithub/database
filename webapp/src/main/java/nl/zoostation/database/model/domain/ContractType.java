package nl.zoostation.database.model.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:09
 */
@Entity
@Table(name = "contract_types")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class ContractType extends Named {

    public ContractType() {
    }

    public ContractType(String name) {
        super(name);
    }

    public ContractType(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ContractType {");
        sb.append("id=").append(getId()).append(", ");
        sb.append("name=").append(getName());
        sb.append('}');
        return sb.toString();
    }
}
