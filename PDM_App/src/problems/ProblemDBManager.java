package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import users.RANK;
import file.FileManager;

public class ProblemDBManager {
	// Key = ���� ID, Value = Problem ��ü
	private static HashMap<Integer, Problem> ProblemDBMap = new HashMap<>();

	public static void PrintProblemDBMap() { // ������
		for (Map.Entry<Integer, Problem> entry : ProblemDBMap.entrySet()) {
            Integer key = entry.getKey();
            Problem value = entry.getValue();
            System.out.println("Key: " + key + ", Value: " + value.toString() + "\n");
        }
	}
	
	// ������ �ؽø� ProblemDBMap�� �߰��ϴ� �Լ�
	public static boolean AddProblem(int ID, Problem problem) {
		if (!problem.isVaild()) {
			return false;
		}
		else {
			ProblemDBMap.put(ID, problem);
			return true;
		}
	}
	
	// ������ .txt ���Ͽ� ���� �� �ؽø� ProblemDBMap�� �߰��ϴ� �Լ�
	public static boolean CreateProblem(Problem problem) {
		
		if (!problem.isVaild()) {
			return false;
		}
		else {
			String filename = Integer.toString(problem.getProblemID());
			String filepath = String.format("\\problems\\ProblemDB\\%s.txt", filename);
			FileManager.createUpdateObjectFile(problem, filepath);
			ProblemDBMap.put(problem.getProblemID(), problem);
			
			return true;
		}
		
	}
	
	// ������ �ٲٴ� �Լ�
	public static boolean ChangeProblem(int ID, Problem problem) {
		if (!problem.isVaild()) {
			return false;
		}
		else {
			if(ProblemDBMap.containsKey(ID)) {
				ProblemDBMap.put(ID, problem);
				String filename = Integer.toString(ID);
				String filepath = String.format("problems\\ProblemDB\\%s.txt", filename);
				FileManager.createUpdateObjectFile(problem, filepath);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	// ������ �ִ��� Ȯ���ϴ� �Լ�
	public static boolean ContainProblem(int ID) {
		if (ProblemDBMap.containsKey(ID)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// ���� ID�� ���� ������ ã�� �Լ�
	public static Problem FindProblem(int ID) {
		Problem pblm = new Problem();
		if (ProblemDBMap.containsKey(ID)) {
			pblm = ProblemDBMap.get(ID);
			return pblm;
		}
		else {
			return pblm;
		}
	}
	
	// ���� ID�� �������� ��������, ������������ �����ؼ� ��ȯ�ϴ� �Լ�
	public static ArrayList<Problem> FindProblemToID(boolean sort){
		ArrayList<Integer> Keys = new ArrayList<>(ProblemDBMap.keySet());
		ArrayList<Problem> ProblemSortID = new ArrayList<>();
		
		if (sort) {
			Collections.sort(Keys);
		}
		else {
			Collections.sort(Keys, Collections.reverseOrder());
		}
		
		for (Integer key : Keys) {
			ProblemSortID.add(ProblemDBMap.get(key));
		}
		
		return ProblemSortID;
	}
	
	// ���� �̸��� �������� ��������, ������������ �����ؼ� ��ȯ�ϴ� �Լ�
	public static ArrayList<Problem> FindProblemToName(boolean sort) {
	    HashMap<Integer, String> ProblemName = new HashMap<>();
	    ArrayList<Problem> SortProblem = new ArrayList<>();
	    
	    for (Map.Entry<Integer, Problem> entry : ProblemDBMap.entrySet()) {
	        Integer key = entry.getKey();
	        Problem problem = entry.getValue();
	        String name = problem.getProblemName();

	        ProblemName.put(key, name);
	    }
	    
	    List<Map.Entry<Integer,String>> list = new ArrayList<Map.Entry<Integer,String>>(ProblemName.entrySet());
	    
        Collections.sort(list, new Comparator<>() {
            @Override
            public int compare(Map.Entry<Integer, String> o1, Map.Entry<Integer, String> o2) {
                if (sort) {
                	return o1.getValue().compareTo(o2.getValue());
                }
                else {
                	return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
	    
        for(Map.Entry<Integer, String> entry : list) {
        	SortProblem.add(ProblemDBMap.get(entry.getKey()));
        }
	    
	    return SortProblem;
	}
	
	// ���� ��ũ�� �������� ��������, ���������� �ؼ� ��ȯ�ϴ� �Լ�
	public static ArrayList<Problem> FindProblemToRank(boolean sort){
		HashMap<Integer, RANK> ProblemName = new HashMap<>();
	    ArrayList<Problem> SortProblem = new ArrayList<>();
	    
	    for (Map.Entry<Integer, Problem> entry : ProblemDBMap.entrySet()) {
	        Integer key = entry.getKey();
	        Problem problem = entry.getValue();
	        RANK Rank = problem.getProblemRank();

	        ProblemName.put(key, Rank);
	    }
	    
	    List<Map.Entry<Integer,RANK>> list = new ArrayList<Map.Entry<Integer,RANK>>(ProblemName.entrySet());
	    
        Collections.sort(list, new Comparator<>() {
            @Override
            public int compare(Map.Entry<Integer, RANK> o1, Map.Entry<Integer, RANK> o2) {
                if (sort) {
                	return o1.getValue().compareTo(o2.getValue());
                }
                else {
                	return o2.getValue().compareTo(o1.getValue());
                }
            }
        });
	    
        for(Map.Entry<Integer, RANK> entry : list) {
        	SortProblem.add(ProblemDBMap.get(entry.getKey()));
        }
	    
	    return SortProblem;
	}
	
	// ���� �̸� �˻��� ���� ������ ��ȯ�ϴ� �Լ�
	public static ArrayList<Problem> FindProblemSearch(String Name) {
	    ArrayList<Problem> ProblemSearch = new ArrayList<>();
	    
	    for (Problem problem : ProblemDBMap.values()) {
	        if (problem.getProblemName().toLowerCase().contains(Name.toLowerCase())) {
	        	ProblemSearch.add(problem);
	        }
	    }
	    
	    return ProblemSearch;
	}
	
	// ProblemDB�� ����� �������� �ҷ� �ؽø� ProblemDBManger�� �߰��ϴ� �Լ�
	public static void init() {
		String dirpath = String.format("\\problems\\ProblemDB"); // ��� ����
		// �ش� ������ ����� ��� ������ Object�� ��ȯ�Ͽ� ArrayList<Object>�� ��ȯ 
		ArrayList<Object> objList = FileManager.readAllObjectFileInDirectory(dirpath);
		try {
			// �� Object ���� Problem�� ����ȯ
			for (Object obj : objList) {	
				if(obj instanceof Problem) {
					Problem plbm = (Problem)obj;
					AddProblem(plbm.getProblemID(), plbm);				 
				} else {
					throw new ClassCastException("Problem �ν��Ͻ��� ��ȯ�� �� �����ϴ�.");
				}
			}
		} catch (ClassCastException e) {
			e.printStackTrace();			
		}
	}	
}