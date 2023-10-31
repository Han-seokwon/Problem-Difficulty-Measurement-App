package users;

import java.util.ArrayList;

import problems.Problem;

public class User {
	private static int userIndexCount = 0;
    private final int userId; // 사용자 식별자
    private String userName;
    private String email;
    private String password; 
    private ArrayList<Problem> solvedProblems;
    
    // Constructor
    public User() {this(null, null, null);}
    public User(String userName, String email, String password) {
        this.userId = userIndexCount++;
        this.userName = userName;
        this.email = email;
        this.password = PasswordManager.hashPassword(password, userId);
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Problem> getSolvedProblems() {
		return solvedProblems;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }
    
    
   

    // Other methods as needed
}

