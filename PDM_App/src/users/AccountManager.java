package users;

import java.io.IOException;
import java.util.regex.Pattern;

import file.FileManager;



public class AccountManager {
	
	public static void registerInputCheck(String name, String email, String password, String passwordCheck) throws IOException{
		String errMsg = "";
		 // Ư������ ����
        if (!Pattern.matches("[a-zA-Z0-9]+", name)) {
        	errMsg += "���: ����� �̸��� Ư�� ���ڸ� ����� �� �����ϴ�.\n";
        }
        
        // �̸��� ���� �ؼ�
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
        	errMsg += "���: �ùٸ� �̸��� ������ �ƴմϴ�.\n";
        }
        
        // TODO : �ߺ��Ǵ� �̸����� �ִ��� Ȯ��
        
        // �ּ� 8�� �̻�, �����ڿ� ���� ���� 1�� �̻� ����        
        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
        	errMsg += "���: ��й�ȣ�� �ּ� 8�� �̻��̾�� �ϰ� �ּ� �ϳ��� ���ڿ� �����ڸ� �����ؾ� �մϴ�.\n";
        }        
        
        if (!(password.equals(passwordCheck))){
        	errMsg += "���: �Էµ� ��й�ȣ�� ��ġ ���� �ʽ��ϴ�.\n";
        }
        if(!errMsg.isEmpty()) {
        	throw new IOException(errMsg);
        }                
	}
	
	public static void createAccount(User user) {
		String filename = FileManager.emailToFilename(user.getEmail());
		String filepath = System.getProperty("user.dir") + String.format("\\src\\users\\UserDB\\%s.txt", filename); // ��� ����
		FileManager.createObjectFile(user, filepath);
		UserDBManager.addUser(user.getEmail(), user);
		// ���� ���� Ȯ�ο�
//		Object obj = FileManager.readObjectFile(path);//		
//		if(obj instanceof User) {
//			System.out.println((User)obj);
//		} 
	}
	
	public static void loginCheck(String email, String password) throws NullPointerException{
		// �̸��� Ȯ��
		System.out.println(email);
		System.out.println(password);
		User user = UserDBManager.findUserByEmail(email);
		if(!User.isVaild(user)) { // �ش��ϴ� �̸����� ���� ���
			throw new NullPointerException("��� : �������� �ʴ� �̸����Դϴ�.");
		} 
		// ��й�ȣ Ȯ��
		String hashedPw = PasswordManager.hashPassword(password, email);
		if(!hashedPw.equals(user.getPassword_hashed())) {
			throw new NullPointerException("��� : ��й�ȣ�� �ٸ��ϴ�.");
		}
		
	}
		

}
