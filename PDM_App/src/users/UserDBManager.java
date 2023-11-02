package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import file.FileManager;

public class UserDBManager {

	private static HashMap<String, User> userDBMap = new HashMap<>();	
	
	public static void printuserDBMap() { // ������
		for (Map.Entry<String, User> entry : userDBMap.entrySet()) {
            String key = entry.getKey();
            User value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value.toString() + "\n");
        }
	}
	
	public static boolean isEmailExist(String email) {
		return userDBMap.containsKey(email);
	}
	
	public static User findUserByEmail(String email) throws NullPointerException{
		User userFound = userDBMap.get(email);
		if(userFound == null) { // �ش� �̸����� ���� ���
			throw new NullPointerException("��� : �������� �ʴ� �̸����Դϴ�.");
		}
		return userFound;
	}
	
	
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // ���޵� ������ü�� ��ȿ���� Ȯ��
			return false;
		}
		// TODO : ������ ���� email�� �ִ� ��� ���� ó��
		userDBMap.put(email, user);
		return true;
	}
	public static void init() {
		String dirpath = FileManager.getPackageRootDir() + String.format("\\users\\UserDB"); // ��� ����
		ArrayList<Object> objList = FileManager.readAllObjectFileInDirectory(dirpath);
		try {
			for (Object obj : objList) {			 
				if(obj instanceof User) {
					User user = (User)obj;
					addUser(user.getEmail(), user);				 
				} else {
					throw new ClassCastException("User �ν��Ͻ��� ��ȯ�� �� �����ϴ�.");
				}
			}
		} catch (ClassCastException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();			
		}
	}
	
	
	




}
