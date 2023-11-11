package users;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import problems.Problem;
import problems.SolvedProblem;


public class User implements Serializable{ // ��ü�� ����Ʈ���·� ��ȯ�� �� �ֵ��� ����ȭ��
    private String username;
    private String email;
    private String password_hashed; 
    private RANK rank = RANK.RANK5; // ���� ���� ��ũ���� ����
    private int rankPoint = 0; // Ƽ� �ø��µ� �ʿ��� ����Ʈ(����ġ)
    private String pwResetQuestion;
    private String pwResetAnswer;
    
    private ArrayList<Problem> solvedProblemList = new ArrayList<>();
    private ArrayList<Date> activityDateList= new ArrayList<>();    
    
    private static final long serialVersionUID = 1L; // ����ȭ ���� ����
    
    // Constructor
    public User() {}
    public User(String username, String email, String password, String pwResetQuestion, String pwResetAnswer) {
        this.username = username;
        this.email = email;
        this.password_hashed = PasswordManager.hashPassword(password, email);
        this.pwResetQuestion = pwResetQuestion;
        this.pwResetAnswer = pwResetAnswer;
    }
    
    // ���� ������ ( deepcopy )
    public User(User user) {
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password_hashed = user.getPassword_hashed();
        this.rank = user.getRank();
        this.rankPoint = user.getRankPoint();
        this.pwResetQuestion = user.getPwResetQuestion();
        this.pwResetAnswer = user.getPwResetAnswer();
        this.solvedProblemList = new ArrayList<>(user.getSolvedProblemList());
        this.activityDateList = new ArrayList<>(user.getActivityDateList()); 
    }
    
    // Getters and setters

	public String getUsername() {
        return username;
    }
	public void setUsername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword_hashed() {
		return password_hashed;
	}
	public void setPassword_hashed(String password_hashed) {
		this.password_hashed = password_hashed;
	}

	public RANK getRank() {
		return RANK.valueOf(rank.name()); // ���� ���縦 ���� ���ο� ������ ����
	}
	public int getRankPoint() {
		return rankPoint; 
	}
	
	public String getPwResetQuestion() {
		return pwResetQuestion;
	}
	public String getPwResetAnswer() {
		return pwResetAnswer;
	}
	
	public List<Problem> getSolvedProblemList() {
		return List.of(solvedProblemList.toArray(new SolvedProblem[0]));// �Һ� ����Ʈ ��ȯ
	}
	public List<Date> getActivityDateList() {
		return List.of(activityDateList.toArray(new Date[0])); // �Һ� ����Ʈ ��ȯ
	}
	

	
    @Override
	public String toString() {
		return "User [ username=" + username + "\n email=" + email + "\n password_hashed=" + password_hashed + "\n rank="
				+ rank + "\n rankPoint=" + rankPoint + "\n pwResetQuestion=" + pwResetQuestion + "\n pwResetAnswer="
				+ pwResetAnswer + "\n solvedProblemList=" + solvedProblemList + "\n activityDateList=" + activityDateList
				+ "]";
	}
    
    // ���� �ν��Ͻ��� ��ȿ���� Ȯ��
	public static boolean isVaild(User user) {    	
    	if( user.getEmail() == null ||
    			user.getUsername() == null ||
    			user.getPassword_hashed() == null ||
    			user.getPwResetAnswer() == null) {
    		return false;
    	} else {
    		return true;
    	}
    }
	
	// ������ ��ũ ����Ʈ�� �����ϰ� ���� Ƽ�� ������ ���� ����Ʈ�� �ѱ�� Ƽ�� ���	
    public void addRankPoint( int rankPoint) {
    	this.rankPoint += rankPoint;
    	RANK nextRank = this.rank.getNextRank();
    	if (this.rankPoint >= nextRank.getRequireRankPoint()) {
			this.rank = nextRank;
		}
    }
    // �ذ�� ������ ���� ����Ʈ�� �߰���
    public void addSolvedProblemData(Problem problem) {
    	this.solvedProblemList.add(problem);
    }
    
	// Ȱ����¥����Ʈ�� �ش� ��¥ �߰�
    public void addActivityDate(Date date) { 
    	if(!this.activityDateList.contains(date)) {
    		this.activityDateList.add(date);    	
    	}
    }
}

