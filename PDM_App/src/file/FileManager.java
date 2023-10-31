package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class FileManager {
	
	public static boolean createObjectFile(Object obj, String filepath) {
		// TODO : 기존에 파일이 있는 경우 처리 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filepath))) {
            oos.writeObject(obj);
        } catch (IOException e) {
        	e.printStackTrace();           
            return false;
        }
        return true;
	}
	
	public static Object readObjectFile(String filepath) {
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filepath))) {
			obj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return obj;
	}
	// 오버로딩
	public static Object readObjectFile(File file) {
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			obj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return obj;
	}
	
	public static HashMap<String, Object> readAllObjectFileInDirectory(String dirpath) {
		HashMap<String, Object> objMap = new HashMap<>();		
		File dir = new File(dirpath);
		File[] objFiles = dir.listFiles();
		for( File file : objFiles) {
			String filename = file.getName().substring(0, file.getName().indexOf('.'));// 확장자 제거
			objMap.put(filename, readObjectFile(file));
		}		
		return objMap;
	}	
	
}
