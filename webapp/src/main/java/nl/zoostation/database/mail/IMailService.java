package nl.zoostation.database.mail;

/**
 * @author valentinnastasi
 */
public interface IMailService {

    void sendMail(EmailDescription... emailDescriptions) throws Exception;

    void sendMailAsync(EmailDescription... emailDescriptions) throws Exception;

}
