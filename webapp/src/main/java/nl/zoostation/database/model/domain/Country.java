package nl.zoostation.database.model.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:27
 */
@Entity
@Table(name = "countries")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Country extends Named {

    @Column(name = "code")
    private String code;

    public Country() {
    }

    public Country(String name, String code) {
        super(name);
        this.code = code;
    }

    public Country(Long id, String name, String code) {
        super(id, name);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), code);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("name=").append(getName());
        sb.append("code=").append(code);
        sb.append('}');
        return sb.toString();
    }
}
