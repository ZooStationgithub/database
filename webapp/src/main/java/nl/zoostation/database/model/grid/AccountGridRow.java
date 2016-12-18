package nl.zoostation.database.model.grid;

import java.time.Instant;

/**
 * @author val
 */
public class AccountGridRow implements IGridRow<Long> {

    private Long id;
    private String login;
    private String group;
    private Instant creationDate;
    private Boolean activated;
    private Boolean online;

    public AccountGridRow() {
    }

    public AccountGridRow(Long id, String login, String group, Instant creationDate, Boolean activated, Boolean online) {
        this.id = id;
        this.login = login;
        this.group = group;
        this.creationDate = creationDate;
        this.activated = activated;
        this.online = online;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}
