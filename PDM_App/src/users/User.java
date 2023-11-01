package users;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import problems.SolvedProblem;


public class User implements Serializable{ // 객체를 바이트형태로 변환할 수 있도록 직렬화함
    private String username;
    private String email;
    private String password_hashed; 
    private ArrayList<SolvedProblem> solvedProblemList = new ArrayList<>();
    private ArrayList<Date> activityDateList= new ArrayList<>();
    
    private RANK rank = RANK.RANK5; // 가장 낮은 랭크부터 시작
    private int rankPoint = 0; // 티어를 올리는데 필요한 포인트(경험치)
    
    // Constructor
    public User() {}
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password_hashed = PasswordManager.hashPassword(password, email);
    }
    
    // Getters and setters

	public String getPassword_hashed() {
		return password_hashed;
	}
	public void setPassword_hashed(String password_hashed) {
		this.password_hashed = password_hashed;
	}
	public List<SolvedProblem> getSolvedProblemList() {
		return List.of(solvedProblemList.toArray(new SolvedProblem[0]));// 불변 리스트 반환
	}
	public List<Date> getActivityDateList() {
		return List.of(activityDateList.toArray(new Date[0])); 
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public String getusername() {
        return username;
    }
	public void setusername(String username) {
		this.username = username;
	}

	public RANK getRank() {
		return this.rank;
	}
	
    @Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password_hashed=" + password_hashed + ", solvedProblemList="
				+ solvedProblemList + "]";
	}
	
    public static boolean isVaild(User user) {
    	if( user.getEmail() == null ||
    			user.getusername() == null ||
    			user.getPassword_hashed() == null) {
    		return false;
    	} else {
    		return true;
    	}
    }
    public void addRankPoint( int rankPoint) {
    	this.rankPoint += rankPoint;
    	RANK nextRank = this.rank.getNextRank();
    	if (this.rankPoint >= nextRank.getRequireRankPoint()) {
			this.rank = nextRank;
		}
    }
    
    public void addSolvedProblemData(SolvedProblem problem) {
    	this.solvedProblemList.add(problem);
    }
    
    public void addActivityDate(Date date) { 
    	// 활동날짜리스트에 해당 날짜 추가
    	if(!this.activityDateList.contains(date)) {
    		this.activityDateList.add(date);    	
    	}
    }
}

