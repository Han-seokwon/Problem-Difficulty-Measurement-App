package users;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Account {
	
	
	
	
	public static void inputCheck(String name, String email, String password) throws IOException{
		String errMsg = "";
		 // 특수문자 제외
        if (!Pattern.matches("[a-zA-Z0-9]+", name)) {
        	errMsg += "경고: 사용자 이름에 특수 문자를 사용할 수 없습니다.\n";
        }
        
        // 이메일 형식 준수
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
        	errMsg += "경고: 올바른 이메일 형식이 아닙니다.\n";
        }
        
        // 최소 8자 이상, 영문자와 숫자 각각 1개 이상 포함        
        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
        	errMsg += "경고: 비밀번호는 최소 8자 이상이어야 하고 최소 하나의 숫자와 영문자를 포함해야 합니다.\n";
        }        
        if(!errMsg.isEmpty()) {
        	throw new IOException(errMsg);
        }                
	}
	
	public static void createAccount() {
		
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
