package users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordManager {
    
    public static String hashPassword(String password, int userIndex) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update((password + userIndex).getBytes()); // userIndex를 솔트로 사용
            byte[] hashedBytes = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
