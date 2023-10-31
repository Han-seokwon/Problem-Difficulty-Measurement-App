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
		String path = System.getProperty("user.dir") + "\\PDM_App\\src\\users\\registerFormats.txt";// ������
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF8"))) {
        	// try ��ȣ �ȿ� �ۼ��ϸ� close �ڵ� ����
            String line = "";            
            while ((line = reader.readLine()) != null) {
            	String[] tokens = line.split(",,"); // ����ǥ���� ������ ���ڿ��� ��ǥ �� ���� ������       	
            	RegisterFormatList.add(new RegisterFormat(tokens[0], tokens[1], tokens[2], tokens[3] )); // ȸ�� ��� ��� ���� �߰�            	
            }            
        } catch (FileNotFoundException | IndexOutOfBoundsException e ) {
            e.printStackTrace();
            System.out.println("ȸ�� ��� ����� �ε����� ���߽��ϴ�.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	public static List<RegisterFormat> getResisterFormats() {
		return List.copyOf(RegisterFormatList); // unmodifiable list�� ��ȯ�Ͽ� ����� ����
	}
}
