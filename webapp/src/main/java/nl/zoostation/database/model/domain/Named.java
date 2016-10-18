package nl.zoostation.database.model.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Objects;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:05
 */
@MappedSuperclass
public abstract class Named extends Identifiable {

    @Column(name = "name", length = 45)
    private String name;

    public Named() {
    }

    public Named(Long id, String name) {
        super(id);
        this.name = name;
    }

    public Named(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Named that = (Named) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }

}
