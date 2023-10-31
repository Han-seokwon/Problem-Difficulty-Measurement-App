package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import file.FileManager;

public class UserDBManager {

	private static HashMap<String, User> userDBMap = new HashMap<>();	
	
	public static void printuserDBMap() { // 디버깅용
		for (Map.Entry<String, User> entry : userDBMap.entrySet()) {
            String key = entry.getKey();
            User value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value.toString());
        }
	}
	
	public static User findUserByEmail(String email) {
		// 해당하는 유저가 있는지 확인 없으면 빈 인스턴스 반환
		// TODO : 방어적 복사를 통해 객체를 변경할 수 없게 하기
		return userDBMap.getOrDefault(email, new User());				 
	}
	
	
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // 전달된 유저객체가 유효한지 확인
			return false;
		}
		// TODO : 기존에 같은 id가 있는 경우 처리
		userDBMap.put(email, user);
		return true;
	}
	public static void init() {
		String dirpath = System.getProperty("user.dir") + String.format("\\src\\users\\UserDB"); // 경로 지정
		ArrayList<Object> objList = FileManager.readAllObjectFileInDirectory(dirpath);
		try {
			for (Object obj : objList) {			 
				if(obj instanceof User) {
					User user = (User)obj;
					addUser(user.getEmail(), user);				 
				} else {
					throw new ClassCastException("User 인스턴스로 변환할 수 없습니다.");
				}

			}
		} catch (ClassCastException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	




}
