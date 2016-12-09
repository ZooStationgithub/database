package nl.zoostation.database.model.grid;

/**
 * @author valentinnastasi
 */
public class IdNameGridRow {

    private Long id;
    private String name;

    public IdNameGridRow() {
    }

    public IdNameGridRow(Long id, String name) {
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