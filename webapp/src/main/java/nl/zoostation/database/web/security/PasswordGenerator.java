package nl.zoostation.database.web.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author valentinnastasi
 */
public class PasswordGenerator {

    public static void main(String[] args) {
        String password = "su";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode(password));
    }

}
