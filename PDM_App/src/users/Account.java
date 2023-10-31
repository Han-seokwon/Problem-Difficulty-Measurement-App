package users;

import java.util.Scanner;

public class Account {
	public static void createAccount() {
		while(true) {
			String username = getInput("user name : ");
			String email = getInput("email: ");
			String password = getInput("pw : ");
			User new_user = new User(username, email, password);
		}
	}
	
	public static String getInput( String inputMessasge) {
		while(true) {
			String inputStr;
			try {
				System.out.printf('\n' + inputMessasge);
				Scanner sc = new Scanner(System.in);
				inputStr = sc.nextLine();				
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			} 
			return inputStr;			
		}
	}

}
