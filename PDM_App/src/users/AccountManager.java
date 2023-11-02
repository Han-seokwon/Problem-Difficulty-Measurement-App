package users;

import java.io.IOException;
import java.util.regex.Pattern;

import file.FileManager;

public class AccountManager {
	
	// 회원가입 정보 유효성 확인 메서드
	public static void registerInputCheck(String name, String email, String password, String passwordConfirm, String answer) throws IOException{
		String errMsg = "";
		
		if(name.isEmpty()) {
			errMsg += "경고: 사용자 이름이 입력되지 않았습니다.\n";
		}
		
		 // 특수문자 제외
        if (!Pattern.matches("[a-zA-Z0-9]+", name)) {
        	errMsg += "경고: 사용자 이름에 특수 문자를 사용할 수 없습니다.\n";
        }
        
        // 이메일 형식 준수
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
        	errMsg += "경고: 올바른 이메일 형식이 아닙니다.\n";
        }
        
        // 중복되는 이메일이 있는지 확인
        if (UserDBManager.isEmailExist(email)) {
        	errMsg += "경고: 이미 등록된 이메일입니다.\n";
        }
        
        try { // 비밀번호 유효성 확인
        	checkPasswordVaildity(password, passwordConfirm);
		} catch (IOException e) {
			errMsg += e.getMessage();
		}
        
        if (answer.isEmpty()){
        	errMsg += "경고: 비밀번호 초기화용 질문에 대한 답변이 입력되지 않았습니다.\n";
        }
        
        if(!errMsg.isEmpty()) {
        	throw new IOException(errMsg);
        }                
	}

	// 비밀번호 유효성 확인용 메서드 (비밀번호 초기화에서도 사용하므로 별개의 메서드로 분리) 
	public static void checkPasswordVaildity(String password, String passwordConfirm) throws IOException{

		String errMsg = "";
        // 최소 8자 이상, 영문자와 숫자 각각 1개 이상 포함        
        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
        	errMsg += "경고: 비밀번호는 최소 8자 이상이어야 하고 최소 하나의 숫자와 영문자를 포함해야 합니다.\n";
        }        
        
        if (!(password.equals(passwordConfirm))){
        	errMsg += "경고: 입력된 비밀번호가 일치 하지 않습니다.\n";
        }
        if(!errMsg.isEmpty()) {
        	throw new IOException(errMsg);
        } 
	}

	// 계정 생성, UserDB 폴더에 파일로 저장하고 UserDBManager 해시맵에도 저장하는 메서드
	public static void createAccount(User user) {
		String filename = FileManager.emailToFilename(user.getEmail());
		String filepath = String.format("\\users\\UserDB\\%s.txt", filename); // 경로 지정
		FileManager.createUpdateObjectFile(user, filepath); // UserDB 폴더에 객체 텍스트 파일 생성
		UserDBManager.addUser(user.getEmail(), user); // UserDBManager 해시맵에 객체 추가
	}
	
	// 로그인 정보 유효성 확인 메서드
	public static void checklogin(String email, String password) throws NullPointerException{
		System.out.println(email);
		System.out.println(password);
		try {
			// 이메일 확인
			User user = UserDBManager.findUserByEmail(email); // 이메일 없으면 throws NullPointerException
			// 비밀번호 확인
			String hashedPw = PasswordManager.hashPassword(password, email);
			if(!hashedPw.equals(user.getPassword_hashed())) {
				throw new NullPointerException("경고 : 비밀번호가 다릅니다.");
			}
		} catch (NullPointerException e) { 
			throw e;// 에러 메시지를 전달하기 위해 예외 되던지기
		}
	}
	
	// 사용자의 비밀번호를 변경하는 메서드, UserDB 폴더에 저장된 객체파일도 수정
	public static void updatePassword(User user, String newPassWord) {
		String newPassword_hashed = PasswordManager.hashPassword(newPassWord, user.getEmail());
		user.setPassword_hashed(newPassword_hashed);		
		// 해당 객체 파일 업데이트
		String filename = FileManager.emailToFilename(user.getEmail());
		String filepath = String.format("\\users\\UserDB\\%s.txt", filename); // 경로 지정
		FileManager.createUpdateObjectFile(user, filepath);
		
	}
	
}
