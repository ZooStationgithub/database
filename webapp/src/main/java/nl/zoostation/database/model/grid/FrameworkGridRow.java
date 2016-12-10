package nl.zoostation.database.model.grid;

/**
 * @author valentinnastasi
 */
public class FrameworkGridRow extends IdNameGridRow {

    private String programmingLanguageName;

    public FrameworkGridRow() {
    }

    public FrameworkGridRow(Long id, String name, String programmingLanguageName) {
        super(id, name);
        this.programmingLanguageName = programmingLanguageName;
    }

    public String getProgrammingLanguageName() {
        return programmingLanguageName;
    }

    public void setProgrammingLanguageName(String programmingLanguageName) {
        this.programmingLanguageName = programmingLanguageName;
    }
}
