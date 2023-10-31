package users;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserDB {
	
	private static HashMap<Integer, User> userList = new HashMap<>();	
	private static int userIndex = 0;
	
	public static User findUserById(int id) {
		// 해당하는 유저가 있는지 확인 없으면 빈 인스턴스 반환
		return userList.getOrDefault(id, new User());				 
	}
	
	
	
	
}
