package users;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;


public class PasswordManager {
	/* ��й�ȣ�� �ؽ��ϴ� �޼���, �̸����� ��Ʈ�� �̿���
	 * return : �ؽ̵� ���ڿ�   
	 */
    public static String hashPassword(String password, String email) {
        try {
        	// @�� �������� ���� ���ڿ��� ����
        	String salt = email.substring(email.indexOf("@")); 
        	// SHA-256 �ؽ� �Լ��� ����Ͽ� MessageDigest ��ü�� �ʱ�ȭ
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // password�� salt�� ������ �� byte �迭�� ��ȯ
            md.update((password + salt).getBytes()); 
            // ��������Ʈ(�ؽ�)�� ����Ʈ �迭 ��ȯ
            byte[] hashedBytes = md.digest();

            StringBuilder sb = new StringBuilder();
            //  ����Ʈ �迭�� 16���� ���ڿ��� ��ȯ�Ͽ� �ؽõ� ��й�ȣ�� ����
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
