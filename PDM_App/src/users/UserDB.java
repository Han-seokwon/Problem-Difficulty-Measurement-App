package users;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserDB {
	
	private static HashMap<Integer, User> userList = new HashMap<>();	
	private static int userIndex = 0;
	
	public static User findUserById(int id) {
		// �ش��ϴ� ������ �ִ��� Ȯ�� ������ �� �ν��Ͻ� ��ȯ
		return userList.getOrDefault(id, new User());				 
	}
	
	
	
	
}
