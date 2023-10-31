package users;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ResisterManager {
	private static List<RegisterFormat> RegisterFormatList = new ArrayList<>();
	public ResisterManager() {
		String path = System.getProperty("user.dir") + "\\PDM_App\\src\\users\\registerFormats.txt";// 절대경로
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF8"))) {
        	// try 괄호 안에 작성하면 close 자동 실행
            String line = "";            
            while ((line = reader.readLine()) != null) {
            	String[] tokens = line.split(",,"); // 정규표현식 때문에 문자열을 쉼표 두 개로 구분함       	
            	RegisterFormatList.add(new RegisterFormat(tokens[0], tokens[1], tokens[2], tokens[3] )); // 회원 등록 양식 정보 추가            	
            }            
        } catch (FileNotFoundException | IndexOutOfBoundsException e ) {
            e.printStackTrace();
            System.out.println("회원 등록 양식을 로드하지 못했습니다.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static List<RegisterFormat> getResisterFormats() {
		return List.copyOf(RegisterFormatList); // unmodifiable list를 반환하여 방어적 복사
	}
}
