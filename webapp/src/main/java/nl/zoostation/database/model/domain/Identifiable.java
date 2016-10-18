package nl.zoostation.database.model.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author valentinnastasi
 * @created 18/10/2016 10:58
 */
@MappedSuperclass
public abstract class Identifiable implements PersistentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    public Identifiable() {
    }

    public Identifiable(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifiable that = (Identifiable) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
