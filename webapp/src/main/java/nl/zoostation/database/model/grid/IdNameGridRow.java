package nl.zoostation.database.model.grid;

/**
 * @author valentinnastasi
 */
public class IdNameGridRow implements IGridRow<Long> {

    private Long id;
    private String name;

    public IdNameGridRow() {
    }

    public IdNameGridRow(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdNameGridRow{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
