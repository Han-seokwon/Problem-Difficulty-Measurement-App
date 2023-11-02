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
		// email을 파일명에 맞게 변환
		return email.replace('@','0').replace('.', '1');
	}
	public static String getPackageRootDir() {
		return System.getProperty("user.dir") + String.format("\\PDM_App\\src");
	}

	public static boolean createUpdateObjectFile(Object obj, String filepath) {
		// 기존에 동일한 파일이 없으면 파일 생성
		// 기존에 동일한 파일이 있으면 덮어쓰기(업데이트)
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
		// 특정 파일을 Object로 변환 
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			obj = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public static ArrayList<Object> readAllObjectFileInDirectory(String dirpath) {
		// 폴더에 있는 모든 파일을 Object로 변환하여 ArrayList에 담아 반환
		ArrayList<Object> objList = new ArrayList<>();		
		File dir = new File(dirpath);
		File[] objFiles = dir.listFiles();
		try {
			for( File file : objFiles) {
				objList.add(readObjectFile(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("해당 파일을 Obeject로 변환할 수 없습니다.");
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
