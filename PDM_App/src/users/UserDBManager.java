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
            System.out.println("Key: " + key + ", Value: " + value.toString());
        }
	}
	
	public static User findUserByEmail(String email) {
		// �ش��ϴ� ������ �ִ��� Ȯ�� ������ �� �ν��Ͻ� ��ȯ
		// TODO : ����� ���縦 ���� ��ü�� ������ �� ���� �ϱ�
		return userDBMap.getOrDefault(email, new User());				 
	}
	
	
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // ���޵� ������ü�� ��ȿ���� Ȯ��
			return false;
		}
		// TODO : ������ ���� id�� �ִ� ��� ó��
		userDBMap.put(email, user);
		return true;
	}
	public static void init() {
		String dirpath = System.getProperty("user.dir") + String.format("\\src\\users\\UserDB"); // ��� ����
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
		}
	}
	
	
	




}
