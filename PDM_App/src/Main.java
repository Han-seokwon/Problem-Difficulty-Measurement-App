import gui.AccountCreateFrame;
import gui.LoginFrame;
import users.AccountManager;
import users.User;
import users.UserDBManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// �׽�Ʈ ���� ������
		User user = new User("qwer", "qwr@na.com", "d1dd", "1. �����ϴ� ������?", "����");
		AccountManager.createAccount(user);
		User user2 = new User("vbnm", "vbnm12@na.com", "saddwq12", "2. ��� �ʵ��б���?", "��õ��");
		AccountManager.createAccount(user2);
		User user3 = new User("tttt", "ttttt@na.com", "wqers1", "3. �����ϴ� ������?", "���");		
		AccountManager.createAccount(user3);
		
		// DB �ʱ�ȭ
		UserDBManager.init();
		
		// DB ��� 
		UserDBManager.printUserDBMap();
		
		new AccountCreateFrame();
		new LoginFrame();
	}

}
