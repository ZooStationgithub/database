package nl.zoostation.database.model.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author valentinnastasi
 */
public class AccountFormObject implements IFormObject<Long> {

    @Email
    @NotBlank
    private String email;

    @Size(min = 8, max = 16)
    private String password;

    private String confirmPassword;

    @NotNull
    private Long groupId;

    public AccountFormObject() {
    }

    public AccountFormObject(String email, String password, Long groupId) {
        this.email = email;
        this.password = password;
        this.groupId = groupId;
    }

    @Override
    public Long getId() {
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountFormObject{");
        sb.append("email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", groupId=").append(groupId);
        sb.append('}');
        return sb.toString();
    }

}
