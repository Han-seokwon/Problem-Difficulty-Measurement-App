package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import file.FileManager;

public class UserDBManager {

	private static HashMap<String, User> userDBMap = new HashMap<>();	
	
	public static void printUserDBMap() { // 디버깅용
		for (Map.Entry<String, User> entry : userDBMap.entrySet()) {
            String key = entry.getKey();
            User value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value.toString() + "\n");
        }
	}
	// 해당하는 이메일이 존재하는지 확인 (등록된 유저인지 확인용)
	public static boolean isEmailExist(String email) {
		return userDBMap.containsKey(email);
	}
	
	
	/*
	 * 이메일에 해당하는 User 인스턴스 반환, isEmailExist() 사용하여 등록된 유저인지 확인후 사용하는 것 권장
	 * return : 인자로 전달된 이메일에 일치하는 유저
	 */
	public static User findUserByEmail(String email) throws NullPointerException{
		User userFound = userDBMap.get(email);
		if(userFound == null) { // 해당 이메일이 없는 경우
			throw new NullPointerException("경고 : 존재하지 않는 이메일입니다.");
		}
		return userFound;
	}
	
	/*
	 *  유저 데이터의 유효성을 검사하고 email을 key로 하여 해시맵에 추가함
	 *  return : 정상적으로 추가되었는지 여부를 반환
	 */
	public static boolean addUser(String email, User user) {
		if(!User.isVaild(user)) { // 전달된 유저객체가 유효한지 확인
			return false;
		}
		// TODO : 기존에 같은 email가 있는 경우 예외 처리
		userDBMap.put(email, user);
		return true;
	}
	
	// UserDB 폴더에 저장된 객체 파일들을 해시맵에 저장하여 초기화, 프로그램 시작시 한 번만 실행
	public static void init() {
		String dirpath = String.format("\\users\\UserDB"); // 경로 지정
		// 해당 폴더에 저장된 모든 파일을 Object로 변환하여 ArrayList<Object>로 변환 
		ArrayList<Object> objList = FileManager.readAllObjectFileInDirectory(dirpath); 
		try {
			// 각 Object 들을 User로 형변환
			for (Object obj : objList) {			 
				if(obj instanceof User) {
					User user = (User)obj;
					addUser(user.getEmail(), user);				 
				} else {
					throw new ClassCastException("User 인스턴스로 변환할 수 없습니다.");
				}
			}
		} catch (ClassCastException e) {
			e.printStackTrace();			
		}
	}
	
	
	




}
