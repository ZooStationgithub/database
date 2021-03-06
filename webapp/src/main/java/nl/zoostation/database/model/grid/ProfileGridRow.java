package nl.zoostation.database.model.grid;

/**
 * @author valentinnastasi
 */
public class ProfileGridRow implements IGridRow<Long> {

    private Long id;
    private String rank;
    private String mainProgrammingLanguage;
    private String secondProgrammingLanguage;
    private Integer testRating;
    private String originCountry;

    public ProfileGridRow(Long id, String rank, String mainProgrammingLanguage, String secondProgrammingLanguage, Integer testRating, String originCountry) {
        this.id = id;
        this.rank = rank;
        this.mainProgrammingLanguage = mainProgrammingLanguage;
        this.secondProgrammingLanguage = secondProgrammingLanguage;
        this.testRating = testRating;
        this.originCountry = originCountry;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMainProgrammingLanguage() {
        return mainProgrammingLanguage;
    }

    public void setMainProgrammingLanguage(String mainProgrammingLanguage) {
        this.mainProgrammingLanguage = mainProgrammingLanguage;
    }

    public String getSecondProgrammingLanguage() {
        return secondProgrammingLanguage;
    }

    public void setSecondProgrammingLanguage(String secondProgrammingLanguage) {
        this.secondProgrammingLanguage = secondProgrammingLanguage;
    }

    public Integer getTestRating() {
        return testRating;
    }

    public void setTestRating(Integer testRating) {
        this.testRating = testRating;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfileGridRow{");
        sb.append("id=").append(id);
        sb.append(", rank='").append(rank).append('\'');
        sb.append(", mainProgrammingLanguage='").append(mainProgrammingLanguage).append('\'');
        sb.append(", secondProgrammingLanguage='").append(secondProgrammingLanguage).append('\'');
        sb.append(", testRating=").append(testRating);
        sb.append(", originCountry='").append(originCountry).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
