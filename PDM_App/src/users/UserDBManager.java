package users;

import java.util.HashMap;
import java.util.Map.Entry;

import file.FileManager;

public class UserDBManager {

	private static HashMap<String, User> userList = new HashMap<>();	

	public static User findUserByEmail(String email) {
		// 해당하는 유저가 있는지 확인 없으면 빈 인스턴스 반환
		return userList.getOrDefault(email, new User());				 
	}
	
	
	
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // 전달된 유저객체가 유효한지 확인
			return false;
		}
		// TODO : 기존에 같은 id가 있는 경우 처리
		userList.put(email, user);
		return true;
	}
	public static void init() {
		String dirpath = System.getProperty("user.dir") + String.format("\\PDM_App\\src\\users\\UserDB"); // 경로 지정
		HashMap<String, Object>  map = FileManager.readAllObjectFileInDirectory(dirpath);
		try {
			for (Entry<String, Object> entrySet : map.entrySet()) {
				String email = entrySet.getKey(); 
				Object obj = entrySet.getValue();			 
				if(obj instanceof User) {
					addUser(email,(User)obj);				 
				} else {
					throw new ClassCastException("User 인스턴스로 변환할 수 없습니다.");
				}

			}
		} catch (ClassCastException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	




}
