package users;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;



public class Account {
	
	
	
	
	public static void inputCheck(String name, String email, String password) throws IOException{
		String errMsg = "";
		 // Ư������ ����
        if (!Pattern.matches("[a-zA-Z0-9]+", name)) {
        	errMsg += "���: ����� �̸��� Ư�� ���ڸ� ����� �� �����ϴ�.\n";
        }
        
        // �̸��� ���� �ؼ�
        if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
        	errMsg += "���: �ùٸ� �̸��� ������ �ƴմϴ�.\n";
        }
        
        // �ּ� 8�� �̻�, �����ڿ� ���� ���� 1�� �̻� ����        
        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)) {
        	errMsg += "���: ��й�ȣ�� �ּ� 8�� �̻��̾�� �ϰ� �ּ� �ϳ��� ���ڿ� �����ڸ� �����ؾ� �մϴ�.\n";
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
