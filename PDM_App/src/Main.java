import gui.AccountCreateFrame;
import gui.LoginFrame;
import users.AccountManager;
import users.User;
import users.UserDBManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 테스트 유저 데이터
		User user = new User("qwer", "qwr@na.com", "d1dd", "1. 좋아하는 동물은?", "사자");
		AccountManager.createAccount(user);
		User user2 = new User("vbnm", "vbnm12@na.com", "saddwq12", "2. 출신 초등학교는?", "목천초");
		AccountManager.createAccount(user2);
		User user3 = new User("tttt", "ttttt@na.com", "wqers1", "3. 좋아하는 음식은?", "사과");		
		AccountManager.createAccount(user3);
		
		// DB 초기화
		UserDBManager.init();
		
		// DB 출력 
		UserDBManager.printUserDBMap();
		
		new AccountCreateFrame();
		new LoginFrame();
	}

}
