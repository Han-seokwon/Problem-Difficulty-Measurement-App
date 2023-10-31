import gui.LoginFrame;
import users.AccountManager;
import users.User;
import users.UserDBManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		new AccountCreateGUI();
		User user = new User("qwer", "qwr@na.com", "1234");
		AccountManager.createAccount(user);
		UserDBManager.init();
		new LoginFrame();
	}

}
