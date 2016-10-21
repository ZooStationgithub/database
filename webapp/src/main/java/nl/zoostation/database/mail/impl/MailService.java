package nl.zoostation.database.mail.impl;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

/**
 * @author valentinnastasi
 */
public class MailService implements IMailService {

    private final JavaMailSender mailSender;
    private final Configuration freeMarkerConfiguration;

    public MailService(JavaMailSender mailSender, Configuration freeMarkerConfiguration) {
        this.mailSender = mailSender;
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    @Override
    public void sendMail(EmailDescription emailDescription) throws Exception {
        MimeMessagePreparator messagePreparator = createMessagePreparator(emailDescription);
        mailSender.send(messagePreparator);
    }

    private MimeMessagePreparator createMessagePreparator(final EmailDescription emailDescription) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(emailDescription.getFrom());
            messageHelper.setTo(emailDescription.getToRecipients().stream().toArray(String[]::new));
            messageHelper.setCc(emailDescription.getCcRecipients().stream().toArray(String[]::new));
            messageHelper.setBcc(emailDescription.getCcRecipients().stream().toArray(String[]::new));
            messageHelper.setSubject(emailDescription.getSubject());
            messageHelper.setText(createMailContent(emailDescription), true);
        };
    }

    private String createMailContent(EmailDescription emailDescription) throws IOException, TemplateException {
        return FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfiguration.getTemplate(emailDescription.getTemplateName()), emailDescription.getModel());
    }

}
