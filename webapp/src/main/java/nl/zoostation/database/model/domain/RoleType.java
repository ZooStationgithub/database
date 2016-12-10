package nl.zoostation.database.model.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:22
 */
@Entity
@Table(name = "role_types")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleType extends Named {

    public RoleType() {
    }

    public RoleType(String name) {
        super(name);
    }

    public RoleType(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoleType{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("name=").append(getName());
        sb.append('}');
        return sb.toString();
    }
}
