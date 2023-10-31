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
	public static String emailToFilename(String email) {
		return email.replace('@','0').replace('.', '1');
	}
	
	public static boolean createObjectFile(Object obj, String filepath) {
		// TODO : ������ ������ �ִ� ��� ó�� 
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
	// �����ε�
	public static Object readObjectFile(File file) {
		Object obj = new Object();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
			obj = ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
		return obj;
	}
	
	public static ArrayList<Object> readAllObjectFileInDirectory(String dirpath) {
		ArrayList<Object> objList = new ArrayList<>();		
		File dir = new File(dirpath);
		File[] objFiles = dir.listFiles();
		for( File file : objFiles) {
			objList.add(readObjectFile(file));
		}		
		return objList;
	}	
	
}
