package nl.zoostation.database.mail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author valentinnastasi
 */
public class EmailDescription {

    private Collection<String> toRecipients;
    private Collection<String> ccRecipients;
    private Collection<String> bccRecipients;
    private String from;
    private String subject;
    private String templateName;
    private Map<String, Object> model;

    public EmailDescription() {
        toRecipients = new ArrayList<>(1);
        ccRecipients = new ArrayList<>(1);
        bccRecipients = new ArrayList<>(1);
        model = new HashMap<>(5);
    }

    public Collection<String> getToRecipients() {
        return toRecipients;
    }

    public Collection<String> getCcRecipients() {
        return ccRecipients;
    }

    public Collection<String> getBccRecipients() {
        return bccRecipients;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public String getTemplateName() {
        return templateName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public static class Builder {

        private EmailDescription emailDescription;

        public Builder() {
            this.emailDescription = new EmailDescription();
        }

        public EmailDescription build() {
            return this.emailDescription;
        }

        public Builder setFrom(String from) {
            this.emailDescription.from = from;
            return this;
        }

        public Builder addTo(String to) {
            this.emailDescription.toRecipients.add(to);
            return this;
        }

        public Builder addCc(String cc) {
            this.emailDescription.ccRecipients.add(cc);
            return this;
        }

        public Builder addBcc(String bcc) {
            this.emailDescription.bccRecipients.add(bcc);
            return this;
        }

        public Builder setSubject(String subject) {
            this.emailDescription.subject = subject;
            return this;
        }

        public Builder setTemplateName(String templateName) {
            this.emailDescription.templateName = templateName;
            return this;
        }

        public Builder addModelEntry(String key, Object value) {
            this.emailDescription.model.put(key, value);
            return this;
        }

    }

}
