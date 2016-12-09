package nl.zoostation.database.model.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author valentinnastasi
 */
public class IdNameForm {

    private Long id;

    @NotBlank
    private String name;

    public IdNameForm() {
    }

    public IdNameForm(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
