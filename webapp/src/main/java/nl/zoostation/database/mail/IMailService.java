package nl.zoostation.database.mail;

/**
 * @author valentinnastasi
 */
public interface IMailService {

    void sendMail(EmailDescription emailDescription) throws Exception;

}
