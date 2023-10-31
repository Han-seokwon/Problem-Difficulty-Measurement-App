package users;

import java.io.Serializable;
import java.util.ArrayList;

import problems.Problem;

public class User implements Serializable{ // 객체를 바이트형태로 변환할 수 있도록 직렬화함
    private String username;
    private String email;
    private String password; 
    private ArrayList<Problem> solvedProblems = new ArrayList<>();
    
    // Constructor
    public User() {}
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        String salt = email.substring(email.indexOf("@"));
        this.password = PasswordManager.hashPassword(password, salt);
        
    }
    
    @Override
	public String toString() {
		return "User [username=" + username + ", email=" + email + ", password=" + password + ", solvedProblems="
				+ solvedProblems + "]";
	}
	// static
    public static boolean isVaild(User user) {
    	if( user.getEmail().isEmpty() ||
    			user.getusername().isEmpty() ||
    			user.getPassword().isEmpty()) {
    		return false;
    	} else {
    		return true;
    	}
    }

    // Getters and setters

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ArrayList<Problem> getSolvedProblems() {
		return solvedProblems;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getusername() {
        return username;
    }
    public String getEmail() {
        return email;
    }

    // Other methods as needed
}

