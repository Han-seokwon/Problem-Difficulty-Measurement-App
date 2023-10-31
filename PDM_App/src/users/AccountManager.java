package users;

import java.io.IOException;
import java.util.regex.Pattern;

import file.FileManager;



public class AccountManager {
	
	public static void registerInputCheck(String name, String email, String password, String passwordCheck) throws IOException{
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
        
        if (!(password.equals(passwordCheck))){
        	errMsg += "경고: 입력된 비밀번호가 일치 하지 않습니다.\n";
        }
        if(!errMsg.isEmpty()) {
        	throw new IOException(errMsg);
        }                
	}
	
	public static void createAccount(User user) {
		String filepath = System.getProperty("user.dir") + String.format("\\PDM_App\\src\\users\\UserDB\\%s.txt", user.getEmail()); // 경로 지정
		FileManager.createObjectFile(user, filepath);
		UserDBManager.addUser(user.getEmail(), user);
		// 파일 생성 확인용
//		Object obj = FileManager.readObjectFile(path);//		
//		if(obj instanceof User) {
//			System.out.println((User)obj);
//		} 
		
	}
		

}
