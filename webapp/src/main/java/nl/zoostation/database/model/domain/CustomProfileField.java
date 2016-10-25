package nl.zoostation.database.model.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author valentinnastasi
 * @created 18/10/2016 11:30
 */
@Entity
@Table(name = "custom_fields")
public class CustomProfileField extends Identifiable {

    @Column(name = "field_name", length = 100)
    private String fieldName;

    @Column(name = "field_value", length = 255)
    private String fieldValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public CustomProfileField() {
    }

    public CustomProfileField(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public CustomProfileField(Long id, String fieldName, String fieldValue) {
        super(id);
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }


    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomProfileField that = (CustomProfileField) o;
        return Objects.equals(fieldName, that.fieldName) &&
                Objects.equals(fieldValue, that.fieldValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fieldName, fieldValue);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomProfileField{");
        sb.append("id=").append(getId()).append(", ");
        sb.append("fieldName='").append(fieldName).append('\'');
        sb.append(", fieldValue='").append(fieldValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
