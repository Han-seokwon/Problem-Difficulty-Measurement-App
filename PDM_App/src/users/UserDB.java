package users;

import java.util.ArrayList;

public class UserDB {
	private static ArrayList<User> userList; 
	public static User findUserById(int id) {
		for( User user : userList) {
			if( user.getUserId() == id) {
				return user;
			}
		}
		return ; // Optional
	}
	
	
	
	
}
