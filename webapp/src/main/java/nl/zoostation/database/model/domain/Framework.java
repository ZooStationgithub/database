package nl.zoostation.database.model.domain;



import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:50
 */
@Entity
@Table(name = "frameworks")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Framework extends Named {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "programming_language_id")
    private ProgrammingLanguage programmingLanguage;

    public Framework() {
    }

    public Framework(String name) {
        super(name);
    }

    public Framework(Long id, String name) {
        super(id, name);
    }

    public Framework(String name, ProgrammingLanguage programmingLanguage) {
        super(name);
        this.programmingLanguage = programmingLanguage;
    }

    public Framework(Long id, String name, ProgrammingLanguage programmingLanguage) {
        super(id, name);
        this.programmingLanguage = programmingLanguage;
    }

    public ProgrammingLanguage getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(ProgrammingLanguage programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Framework{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("name=").append(getName());
        sb.append('}');
        return sb.toString();
    }
}
