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
	public static String emailToFilename(String email) {
		// email�� ���ϸ� �°� ��ȯ
		return email.replace('@','0').replace('.', '1');
	}
	public static String getPackageRootDir() {
		return System.getProperty("user.dir") + String.format("\\PDM_App\\src");
	}

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

	public static Object readObjectFile(File file) {
		// Ư�� ������ Object�� ��ȯ 
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			obj = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static ArrayList<Object> readAllObjectFileInDirectory(String dirpath) {
		// ������ �ִ� ��� ������ Object�� ��ȯ�Ͽ� ArrayList�� ��� ��ȯ
		ArrayList<Object> objList = new ArrayList<>();		
		File dir = new File(dirpath);
		File[] objFiles = dir.listFiles();
		try {
			for( File file : objFiles) {
				objList.add(readObjectFile(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("�ش� ������ Obeject�� ��ȯ�� �� �����ϴ�.");
		}
		
		return objList;
	}	

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
