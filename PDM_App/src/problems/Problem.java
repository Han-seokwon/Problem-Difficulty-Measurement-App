package problems;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import users.RANK;
import users.User;

public class Problem implements Serializable {
	
	// ���� �̸�, ���� ID, ���� URL(����), ���� ��ũ
	private String ProblemName;
	private int ProblemID;
	private String ProblemURL;
	private RANK ProblemRank;
	/*
	 * Step 1, Step 2, Step 3, Algorithm Hint�� Ű ������ Ž��
	 * ������ ������ Ű ������ ������ ��Ʈ�� ������ ������ �ؽø��� ������ ����
	 */
	private HashMap<String, HashMap<User, String>> ProblemHint = new HashMap<>();
	// �н��ڷ� URL, ���� �˰��� �з�
	private ArrayList<String> ProblemReferences = new ArrayList<>();
	private ArrayList<String> ProblemAlgorithm = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
	
	// ������
	public Problem() {}
	public Problem(String ProblemName, int ID, String URL, RANK Rank, ArrayList<String> Algorithm) {
		this.ProblemName = ProblemName;
		this.ProblemID = ID;
		this.ProblemURL = URL;
		this.ProblemRank = Rank;
		this.ProblemAlgorithm = Algorithm;
	}
	public Problem(Problem problem) {
		this.ProblemName = problem.ProblemName;
		this.ProblemID = problem.ProblemID;
		this.ProblemURL = problem.ProblemURL;
		this.ProblemRank = problem.ProblemRank;
		this.ProblemAlgorithm = problem.ProblemAlgorithm;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Problem ID: ").append(ProblemID).append("\n");
	    sb.append("Problem Name: ").append(ProblemName).append("\n");
	    sb.append("Problem URL: ").append(ProblemURL).append("\n");
	    sb.append("Problem Rank: ").append(ProblemRank).append("\n");

	    sb.append("Problem Hints: \n");
	    for (String hintKey : ProblemHint.keySet()) {
	        sb.append("  ").append(hintKey).append(":\n");
	        HashMap<User, String> hintMap = ProblemHint.get(hintKey);
	        for (User user : hintMap.keySet()) {
	            String hint = hintMap.get(user);
	            sb.append("    ").append(user.getUsername()).append(": ").append(hint).append("\n");
	        }
	    }

	    sb.append("Problem References: \n");
	    for (String reference : ProblemReferences) {
	        sb.append("  ").append(reference).append("\n");
	    }

	    sb.append("Problem Algorithms: \n");
	    for (String algorithm : ProblemAlgorithm) {
	        sb.append("  ").append(algorithm).append("\n");
	    }

	    return sb.toString();
	}
	
	// ������
	public void setProblemRank(RANK Rank) {
		this.ProblemRank = Rank;
	}
	// ������
	public String getProblemName() {
		return this.ProblemName;
	}
	public int getProblemID() {
		return this.ProblemID;
	}
	public String getProblemURL() {
		return this.ProblemURL;
	}
	public RANK getProblemRank() {
		return this.ProblemRank;
	}
	public HashMap<User, String> getProblemHint(String key) {
	    HashMap<User, String> userHintMap = this.ProblemHint.get(key);
	    
	    if (userHintMap == null) {
	        return null;
	    }
	    
	    return userHintMap;
	}
	public ArrayList<String> getProblemReferences(){
		return this.ProblemReferences;
	}
	public ArrayList<String> getProblemAlgorithm(){
		return this.ProblemAlgorithm;
	}
	
	// ���� ��Ʈ, �н��ڷ� �߰�
	public void AddProblemHint(String key, User user, String hint) {
	    HashMap<User, String> userHintMap = this.ProblemHint.get(key);
	    
	    if (userHintMap == null) {
	        userHintMap = new HashMap<>();
	        this.ProblemHint.put(key, userHintMap);
	    }

	    userHintMap.put(user, hint);
	}
	
	public void AddProblemReferences(String References) {
		this.ProblemReferences.add(References);
	}
	
	public boolean isVaild() {
		if (this.ProblemID == 0 || 
				this.ProblemName == null || 
					this.ProblemRank == null ||
						this.ProblemURL == null ||
							this.ProblemAlgorithm == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
}