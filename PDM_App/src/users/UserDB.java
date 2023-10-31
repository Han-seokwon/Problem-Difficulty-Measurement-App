package users;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

public class UserDB {
	
	private static HashMap<Integer, User> userList;	
	private static userIndex = 0;
	
	
	public static boolean getInput_userCreate() {
		Scanner sc = new Scanner(System.in);
		System.out.println("계정을 생성합니다.");
		System.out.printf("유저이름 입력(특수문자 제외) : ");
		String username = sc.nextLine();
		System.out.printf("이메일 입력 : ");
		String email = sc.nextLine();
		System.out.printf("이메일 입력 : ");
		String password = sc.nextLine();
	}
	
	public static boolean inputCheck_userCreate(String username, String email, String password) {
        // Check if special characters are used in username
        if (!Pattern.matches("[a-zA-Z0-9]+", username)) {
            System.out.println("Warning: Special characters are not allowed in the username.");
            return false;
        }
        
        // Check if email is formatted properly
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
            System.out.println("Warning: Invalid email format.");
            return false;
        }
        
        // Check if password is secure (e.g., at least 8 characters with at least one digit and one special character)
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,}$", password)) {
            System.out.println("Warning: Password must be at least 8 characters long and contain at least one digit and one special character.");
            return false;
        }
        
        return true
	}
	
	public static boolean createUser() {

		
		
	}
	
	public static User findUserById(int id) {
		// 해당하는 유저가 있는지 확인 없으면 빈 인스턴스 반환
		return userList.getOrDefalut(id, new User());				 
	}
	
	
	
	
}
