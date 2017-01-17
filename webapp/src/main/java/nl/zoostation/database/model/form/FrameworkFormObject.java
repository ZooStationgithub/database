package nl.zoostation.database.model.form;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @author valentinnastasi
 */
public class FrameworkFormObject implements IFormObject<Long> {

    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long programmingLanguageId;

    public FrameworkFormObject() {
    }

    public FrameworkFormObject(Long id, String name, Long programmingLanguageId) {
        this.id = id;
        this.name = name;
        this.programmingLanguageId = programmingLanguageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getProgrammingLanguageId() {
        return programmingLanguageId;
    }

    public void setProgrammingLanguageId(Long programmingLanguageId) {
        this.programmingLanguageId = programmingLanguageId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FrameworkFormObject{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", programmingLanguageId=").append(programmingLanguageId);
        sb.append('}');
        return sb.toString();
    }
}
