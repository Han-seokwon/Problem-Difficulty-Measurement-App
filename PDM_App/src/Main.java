import gui.AccountCreateFrame;
import gui.LoginFrame;
import users.AccountManager;
import users.User;
import users.UserDBManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User("qwer", "qwr@na.com", "d1dd");
		AccountManager.createAccount(user);
		User user2 = new User("vbnm", "vbnm12@na.com", "saddwq12");
		AccountManager.createAccount(user2);
		User user3 = new User("tttt", "ttttt@na.com", "wqers1");
		AccountManager.createAccount(user3);
		UserDBManager.init();
		UserDBManager.printuserDBMap();
		new AccountCreateFrame();
//		new LoginFrame();
	}

}
