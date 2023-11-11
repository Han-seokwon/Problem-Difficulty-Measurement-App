import fetcher.JsonFetcher;
import gui.MainFrame;
import problems.ProblemDBManager;
import users.AccountManager;
import users.User;
import users.UserDBManager;

public class Main {

	public static void main(String[] args) {	
		// UserDB �ʱ�ȭ
		UserDBManager.init();
		
		// UserDB ��� 
		UserDBManager.printUserDBMap();
		
		
		new MainFrame();
		

		// �׽�Ʈ ���� ������
//		User user = new User("qwer", "qwr@na.com", "d1dd", "1. �����ϴ� ������?", "����");
//		AccountManager.createAccount(user);
//		User user2 = new User("vbnm", "vbnm12@na.com", "saddwq12", "2. ��� �ʵ��б���?", "��õ��");
//		AccountManager.createAccount(user2);
//		User user3 = new User("tttt", "ttttt@na.com", "wqers1", "3. �����ϴ� ������?", "���");		
//		AccountManager.createAccount(user3);
//		User user4 = new User("�Ѽ���", "han@naver.com", "qwer1234", "3. �����ϴ� ������?", "���");		
//		AccountManager.createAccount(user4);
		
		
		
		// SolvedAC���� ���� ������ �����ͼ� ProblemDB�� �߰��ϱ� (900���� �������� 429���� �߻�)
//		JsonFetcher.updateProblemDB_FromSolvedAC();
		

//        long startTime = System.currentTimeMillis();        
//		// ProblemDB �ʱ�ȭ
//		ProblemDBManager.init();		
//		System.out.println("ProblemDB �ʱ�ȭ �ҿ� �ð� : " + (System.currentTimeMillis() - startTime) + "ms");   
//		
//		// ProblemDB ���
////		ProblemDBManager.PrintProblemDBMap();
//		
//		User user1 = UserDBManager.findUserByEmail("han@naver.com");
//		JsonFetcher.updateUserSolvedProblemList_FromSolvedAC("hoh9170", user1);
//		System.out.println("\n<<< User Data >>>\n");
//		System.out.println(user1);
		

		
		
//		// DB �ʱ�ȭ
//		UserDBManager.init();
//		
//		// DB ��� 
//		UserDBManager.printUserDBMap();
//		

	}

}
