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
}
