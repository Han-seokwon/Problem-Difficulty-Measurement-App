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
	
	public static boolean isEmailExist(String email) {
		return userDBMap.containsKey(email);
	}
	
	public static User findUserByEmail(String email) throws NullPointerException{
		User userFound = userDBMap.get(email);
		if(userFound == null) { // 해당 이메일이 없는 경우
			throw new NullPointerException("경고 : 존재하지 않는 이메일입니다.");
		}
		return userFound;
		// TODO : 방어적 복사를 통해 객체를 변경할 수 없게 하기
	}
	
	
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // 전달된 유저객체가 유효한지 확인
			return false;
		}
		// TODO : 기존에 같은 id가 있는 경우 예외 처리
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
