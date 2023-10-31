package users;

import java.util.HashMap;
import java.util.Map.Entry;

import file.FileManager;

public class UserDBManager {

	private static HashMap<String, User> userList = new HashMap<>();	

	public static User findUserByEmail(String email) {
		// �ش��ϴ� ������ �ִ��� Ȯ�� ������ �� �ν��Ͻ� ��ȯ
		return userList.getOrDefault(email, new User());				 
	}
	
	
	
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // ���޵� ������ü�� ��ȿ���� Ȯ��
			return false;
		}
		// TODO : ������ ���� id�� �ִ� ��� ó��
		userList.put(email, user);
		return true;
	}
	public static void init() {
		String dirpath = System.getProperty("user.dir") + String.format("\\PDM_App\\src\\users\\UserDB"); // ��� ����
		HashMap<String, Object>  map = FileManager.readAllObjectFileInDirectory(dirpath);
		try {
			for (Entry<String, Object> entrySet : map.entrySet()) {
				String email = entrySet.getKey(); 
				Object obj = entrySet.getValue();			 
				if(obj instanceof User) {
					addUser(email,(User)obj);				 
				} else {
					throw new ClassCastException("User �ν��Ͻ��� ��ȯ�� �� �����ϴ�.");
				}

			}
		} catch (ClassCastException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	




}
