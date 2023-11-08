package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import file.FileManager;

public class UserDBManager {

	private static HashMap<String, User> userDBMap = new HashMap<>();	
	
	public static void printUserDBMap() { // ������
		for (Map.Entry<String, User> entry : userDBMap.entrySet()) {
            String key = entry.getKey();
            User value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value.toString() + "\n");
        }
	}
	// �ش��ϴ� �̸����� �����ϴ��� Ȯ�� (��ϵ� �������� Ȯ�ο�)
	public static boolean isEmailExist(String email) {
		return userDBMap.containsKey(email);
	}
	
	
	/*
	 * �̸��Ͽ� �ش��ϴ� User �ν��Ͻ� ��ȯ, isEmailExist() ����Ͽ� ��ϵ� �������� Ȯ���� ����ϴ� �� ����
	 * return : ���ڷ� ���޵� �̸��Ͽ� ��ġ�ϴ� ����
	 */
	public static User findUserByEmail(String email) throws NullPointerException{
		User userFound = userDBMap.get(email);
		if(userFound == null) { // �ش� �̸����� ���� ���
			throw new NullPointerException("��� : �������� �ʴ� �̸����Դϴ�.");
		}
		return userFound;
	}
	
	/*
	 *  ���� �������� ��ȿ���� �˻��ϰ� email�� key�� �Ͽ� �ؽøʿ� �߰���
	 *  return : ���������� �߰��Ǿ����� ���θ� ��ȯ
	 */
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // ���޵� ������ü�� ��ȿ���� Ȯ��
			return false;
		}
		// TODO : ������ ���� email�� �ִ� ��� ���� ó��
		userDBMap.put(email, user);
		return true;
	}
	
	// UserDB ������ ����� ��ü ���ϵ��� �ؽøʿ� �����Ͽ� �ʱ�ȭ, ���α׷� ���۽� �� ���� ����
	public static void init() {
		String dirpath = String.format("\\users\\UserDB"); // ��� ����
		// �ش� ������ ����� ��� ������ Object�� ��ȯ�Ͽ� ArrayList<Object>�� ��ȯ 
		ArrayList<Object> objList = FileManager.readAllObjectFileInDirectory(dirpath); 
		try {
			// �� Object ���� User�� ����ȯ
			for (Object obj : objList) {			 
				if(obj instanceof User) {
					User user = (User)obj;
					addUser(user.getEmail(), user);				 
				} else {
					throw new ClassCastException("User �ν��Ͻ��� ��ȯ�� �� �����ϴ�.");
				}
			}
		} catch (ClassCastException e) {
			e.printStackTrace();			
		}
	}
	
	
	




}
