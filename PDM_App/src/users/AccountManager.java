package users;

import java.io.IOException;
import java.util.regex.Pattern;

import file.FileManager;

public class AccountManager {
	
	// ȸ������ ���� ��ȿ�� Ȯ�� �޼���
	// param : �̸�, �̸���, ���, ���Ȯ��, ��� �ʱ�ȭ�� ������ ���� �亯
	public static void registerInputCheck(String name, String email, String password, String passwordConfirm, String answer) throws IOException{
		String errMsg = "";
		
		if(name.isEmpty()) {
			errMsg += "���: ����� �̸��� �Էµ��� �ʾҽ��ϴ�.\n";
		}
		
		 // Ư������ ����
        if (!Pattern.matches("[a-zA-Z��-�R0-9]+", name)) {
        	errMsg += "���: ����� �̸��� Ư�� ���ڸ� ����� �� �����ϴ�.\n";
        }
        
        // �̸��� ���� �ؼ�
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
        	errMsg += "���: �ùٸ� �̸��� ������ �ƴմϴ�.\n";
        }
        
        // �ߺ��Ǵ� �̸����� �ִ��� Ȯ��
        if (UserDBManager.isEmailExist(email)) {
        	errMsg += "���: �̹� ��ϵ� �̸����Դϴ�.\n";
        }
        
        try { // ��й�ȣ ��ȿ�� Ȯ��
        	checkPasswordVaildity(password, passwordConfirm);
		} catch (IOException e) {
			errMsg += e.getMessage();
		}
        
        if (answer.isEmpty()){
        	errMsg += "���: ��й�ȣ �ʱ�ȭ�� ������ ���� �亯�� �Էµ��� �ʾҽ��ϴ�.\n";
        }
        
        if(!errMsg.isEmpty()) {
        	throw new IOException(errMsg);
        }                
	}

	// ��й�ȣ ��ȿ�� Ȯ�ο� �޼��� (��й�ȣ �ʱ�ȭ������ ����ϹǷ� ������ �޼���� �и�)
	public static void checkPasswordVaildity(String password, String passwordConfirm) throws IOException{

		String errMsg = "";
        // �ּ� 8�� �̻�, �����ڿ� ���� ���� 1�� �̻� ����        
        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
        	errMsg += "���: ��й�ȣ�� �ּ� 8�� �̻��̾�� �ϰ� �ּ� �ϳ��� ���ڿ� �����ڸ� �����ؾ� �մϴ�.\n";
        }        
        
        if (!(password.equals(passwordConfirm))){
        	errMsg += "���: �Էµ� ��й�ȣ�� ��ġ ���� �ʽ��ϴ�.\n";
        }
        if(!errMsg.isEmpty()) {
        	throw new IOException(errMsg);
        } 
	}

	/* ȸ�� ������ ��ȿ���� Ȯ�ε� ������ UserDB ������ ���Ϸ� �����ϰ� UserDBManager �ؽøʿ��� �����ϴ� �޼���
	 * param :  ȸ�� ������ ��ȿ���� Ȯ�ε� ���� ��ü
	 */
	public static void createAccount(User user) {
		String filename = FileManager.emailToFilename(user.getEmail());
		String filepath = String.format("\\users\\UserDB\\%s.txt", filename); // ��� ����
		FileManager.createUpdateObjectFile(user, filepath); // UserDB ������ ��ü �ؽ�Ʈ ���� ����
		UserDBManager.addUser(user.getEmail(), user); // UserDBManager �ؽøʿ� ��ü �߰�
	}
	
	// �̸���, ����� �Է¹޾� �ش� ȸ���� �����ϴ��� Ȯ���ϰ�, ����� ��ġ�ϴ��� Ȯ���ϴ� �޼���
	public static User checklogin(String email, String password) throws NullPointerException{
		System.out.println(email);
		System.out.println(password);
		try {
			// �̸��� Ȯ��
			User user = UserDBManager.findUserByEmail(email); // �̸��� ������ throws NullPointerException
			// ��й�ȣ Ȯ��
			String hashedPw = PasswordManager.hashPassword(password, email);
			// �Է¹��� ��й�ȣ�� �ؽ��Ͽ� user �ν��Ͻ��� ����� �ؽ� ��й�ȣ�� ��
			if(!hashedPw.equals(user.getPassword_hashed())) {
				throw new NullPointerException("��� : ��й�ȣ�� �ٸ��ϴ�.");
			} else { // ��й�ȣ�� ��ġ�ϴ� ���
				return user;
			}
		} catch (NullPointerException e) { 
			throw e;// ���� �޽����� �����ϱ� ���� ���� �Ǵ�����
		}
	}
	
	/* ������� ��й�ȣ�� �����ϴ� �޼���, UserDB ������ ����� ��ü���ϵ� ����
	 * param : ����� ������ ���� ��ü, ������ ��� ���ڿ�
	 */
	public static void updatePassword(User user, String newPassWord) {
		String newPassword_hashed = PasswordManager.hashPassword(newPassWord, user.getEmail());
		user.setPassword_hashed(newPassword_hashed);		
		// �ش� ��ü ���� ������Ʈ
		String filename = FileManager.emailToFilename(user.getEmail());
		String filepath = String.format("/users/UserDB/%s.txt", filename); // ��� ����
		FileManager.createUpdateObjectFile(user, filepath);
		
	}
	
}
