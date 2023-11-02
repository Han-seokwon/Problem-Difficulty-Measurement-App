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
	
	// email을 파일명에 맞게 변환
	public static String emailToFilename(String email) {
		return email.replace('@','0').replace('.', '1');
	}
	// 현재 프로젝트 소스 폴더의 절대 경로를 가져오는 메서드
	public static String getPackageRootDir() {
		return System.getProperty("user.dir") + String.format("\\PDM_App\\src");
	}

	// Object 객체를 파일경로에 저장 or 업데이트
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
	
	// 파일경로에 해당하는 객체파일을 Object로 변환하여 반환하는 메서드
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

	// 객체파일(File)을 Object로 변환하여 반환하는 메서드
	public static Object readObjectFile(File file) {
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			obj = ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return obj;
	}

	// 폴더에 있는 파일을 Object로 변환하여 ArrayList에 담아 반환하는 메서드
	public static ArrayList<Object> readAllObjectFileInDirectory(String dirpath) {
		dirpath = getPackageRootDir() + dirpath;
		ArrayList<Object> objList = new ArrayList<>();		
		File dir = new File(dirpath);
		File[] objFiles = dir.listFiles(); // 폴더의 모든 파일을 배열로 반환
		try {
			for( File file : objFiles) {
				objList.add(readObjectFile(file)); // 각 파일을 Object로 변환
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("해당 파일을 Obeject로 변환할 수 없습니다.");
		}
		
		return objList;
	}	

	// 특정 경로에 있는 파일 내용을 한 줄씩 읽어 List<String>로 반환 ( 텍스트 파일 읽는데 사용 )
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
