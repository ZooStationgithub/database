package nl.zoostation.database.model.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author valentinnastasi
 */
@Entity
@Table(name = "rank_types")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RankType extends Named {

    public RankType() {
    }

    public RankType(String name) {
        super(name);
    }

    public RankType(Long id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RankType{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("name=").append(getName());
        sb.append('}');
        return sb.toString();
    }
}
