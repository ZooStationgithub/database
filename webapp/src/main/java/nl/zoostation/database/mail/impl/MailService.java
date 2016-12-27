package nl.zoostation.database.mail.impl;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import nl.zoostation.database.annotations.validation.NotNull;
import nl.zoostation.database.exception.MailServiceException;
import nl.zoostation.database.mail.EmailDescription;
import nl.zoostation.database.mail.IMailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author valentinnastasi
 */
public class MailService implements IMailService {

    private static final Logger logger  = LogManager.getLogger(MailService.class);

    private final JavaMailSender mailSender;
    private final Configuration freeMarkerConfiguration;

    public MailService(JavaMailSender mailSender, Configuration freeMarkerConfiguration) {
        this.mailSender = mailSender;
        this.freeMarkerConfiguration = freeMarkerConfiguration;
    }

    @Override
    public void sendMail(@NotNull EmailDescription... emailDescriptions) throws Exception {
        Arrays.stream(emailDescriptions).forEach(emailDescription -> {
            try {
                logger.debug("Preparing mail for {}", emailDescription.getToRecipients());
                MimeMessagePreparator messagePreparator = createMessagePreparator(emailDescription);
                mailSender.send(messagePreparator);
            } catch (Exception e) {
                throw new MailServiceException(e);
            }
        });
    }

    @Async
    @Override
    public void sendMailAsync(EmailDescription... emailDescriptions) throws Exception {
        sendMail(emailDescriptions);
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
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfiguration.getTemplate(emailDescription.getTemplateName()), emailDescription.getModel());
        logger.debug("Mail content for {} is ready: {}", emailDescription.getToRecipients(), s);
        return s;
    }

}
