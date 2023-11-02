package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	
	// email�� ���ϸ� �°� ��ȯ
	public static String emailToFilename(String email) {
		return email.replace('@','0').replace('.', '1');
	}
	// ���� ������Ʈ �ҽ� ������ ���� ��θ� �������� �޼���
	public static String getPackageRootDir() {
		return System.getProperty("user.dir") + String.format("\\PDM_App\\src");
	}

	// Object ��ü�� ���ϰ�ο� ���� or ������Ʈ
	public static boolean createUpdateObjectFile(Object obj, String filepath) {
		// ������ ������ ������ ������ ���� ����
		// ������ ������ ������ ������ �����(������Ʈ)
		filepath = getPackageRootDir() + filepath;		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
			oos.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();           
			return false;
		}
		return true;
	}
	
	// ���ϰ�ο� �ش��ϴ� ��ü������ Object�� ��ȯ�Ͽ� ��ȯ�ϴ� �޼���
	public static Object readObjectFile(String filepath) {
		filepath = getPackageRootDir() + filepath;
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
			obj = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	// ��ü����(File)�� Object�� ��ȯ�Ͽ� ��ȯ�ϴ� �޼���
	public static Object readObjectFile(File file) {
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			obj = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	// ������ �ִ� ������ Object�� ��ȯ�Ͽ� ArrayList�� ��� ��ȯ�ϴ� �޼���
	public static ArrayList<Object> readAllObjectFileInDirectory(String dirpath) {
		dirpath = getPackageRootDir() + dirpath;
		ArrayList<Object> objList = new ArrayList<>();		
		File dir = new File(dirpath);
		File[] objFiles = dir.listFiles(); // ������ ��� ������ �迭�� ��ȯ
		try {
			for( File file : objFiles) {
				objList.add(readObjectFile(file)); // �� ������ Object�� ��ȯ
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ش� ������ Obeject�� ��ȯ�� �� �����ϴ�.");
		}
		
		return objList;
	}	

	// Ư�� ��ο� �ִ� ���� ������ �� �پ� �о� List<String>�� ��ȯ ( �ؽ�Ʈ ���� �дµ� ��� )
	public static List<String> readLinesFromFile(String filepath){
		filepath = getPackageRootDir() + filepath;
		List<String> lineList = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
			String line;
			while ((line = br.readLine()) != null) {
				lineList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lineList;
	}

}
