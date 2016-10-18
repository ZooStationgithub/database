package nl.zoostation.database.model.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:42
 */
@Entity
@Table(name = "account_groups")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class AccountGroup extends Identifiable {

    @Column(name = "display_name", length = 45)
    private String displayName;

    @Column(name = "security_role")
    @Enumerated(EnumType.STRING)
    private SecurityRole securityRole;

    public AccountGroup() {
    }

    public AccountGroup(String displayName, SecurityRole securityRole) {
        this.displayName = displayName;
        this.securityRole = securityRole;
    }

    public AccountGroup(Long id, String displayName, SecurityRole securityRole) {
        super(id);
        this.displayName = displayName;
        this.securityRole = securityRole;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public SecurityRole getSecurityRole() {
        return securityRole;
    }

    public void setSecurityRole(SecurityRole securityRole) {
        this.securityRole = securityRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AccountGroup that = (AccountGroup) o;
        return Objects.equals(displayName, that.displayName) &&
                securityRole == that.securityRole;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), displayName, securityRole);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountGroup{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("displayName='").append(displayName).append('\'');
        sb.append(", securityRole=").append(securityRole);
        sb.append('}');
        return sb.toString();
    }
}
