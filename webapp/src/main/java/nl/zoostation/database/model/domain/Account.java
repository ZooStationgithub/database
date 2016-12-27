package nl.zoostation.database.model.domain;

import javax.persistence.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:35
 */
@Entity
@Table(name = "accounts")
public class Account extends Identifiable {

    @Column(name = "login", length = 45, unique = true)
    private String login;

    @Column(name = "password", length = 45)
    private String password;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "activation_date")
    private Instant activationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id")
    private AccountGroup accountGroup;

    public Account() {
    }

    public Account(String login, String password, Instant creationDate, Instant activationDate) {
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
        this.activationDate = activationDate;
    }

    public Account(Long id, String login, String password, Instant creationDate, Instant activationDate) {
        super(id);
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
        this.activationDate = activationDate;
    }

    public Account(String login, String password, Instant creationDate, Instant activationDate, AccountGroup accountGroup) {
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
        this.activationDate = activationDate;
        this.accountGroup = accountGroup;
    }

    public Account(Long id, String login, String password, Instant creationDate, Instant activationDate, AccountGroup accountGroup) {
        super(id);
        this.login = login;
        this.password = password;
        this.creationDate = creationDate;
        this.activationDate = activationDate;
        this.accountGroup = accountGroup;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Optional<Instant> getActivationDate() {
        return Optional.ofNullable(activationDate);
    }

    public void setActivationDate(Instant activationDate) {
        this.activationDate = activationDate;
    }

    public AccountGroup getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return Objects.equals(login, account.login) &&
                Objects.equals(password, account.password) &&
                Objects.equals(creationDate, account.creationDate) &&
                Objects.equals(activationDate, account.activationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login, password, creationDate, activationDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append("******").append('\'');
        sb.append(", creationDate=").append(DateTimeFormatter.ISO_DATE_TIME.format(creationDate));
        sb.append(", activationDate=").append(Optional.ofNullable(activationDate).map(DateTimeFormatter.ISO_DATE_TIME::format).orElse(null));
        sb.append('}');
        return sb.toString();
    }
}
