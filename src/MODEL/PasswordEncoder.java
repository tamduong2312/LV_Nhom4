package MODEL;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {
    
    private static final int WORKLOAD = 5; 

    public static String hashPassword(String password) {

        String salt = BCrypt.gensalt(WORKLOAD); 
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {

        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}