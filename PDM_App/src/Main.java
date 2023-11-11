import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import problems.Problem;
import problems.ProblemDBManager;
import users.AccountManager;
import users.RANK;
import users.User;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> list = Arrays.asList("����", "��Ģ����", "����");
		ArrayList<String> algorithm = new ArrayList<>(list);
		Problem problem = new Problem("A+B",1000, "https://www.acmicpc.net/problem/1000", RANK.RANK5,algorithm);
		System.out.println(problem);
		ProblemDBManager.CreateProblem(problem);
		
//		JsonFetcher.updateProblemDB_FromSolvedAC();
		
		
		// �׽�Ʈ ���� ������
		User user = new User("qwer", "qwr@na.com", "d1dd", "1. �����ϴ� ������?", "����");
		AccountManager.createAccount(user);
		
//		User user2 = new User("vbnm", "vbnm12@na.com", "saddwq12", "2. ��� �ʵ��б���?", "��õ��");
//		AccountManager.createAccount(user2);
//		User user3 = new User("tttt", "ttttt@na.com", "wqers1", "3. �����ϴ� ������?", "���");		
//		AccountManager.createAccount(user3);
//		
//		// DB �ʱ�ȭ
//		UserDBManager.init();
//		
//		// DB ��� 
//		UserDBManager.printUserDBMap();
//		
//		new AccountCreateFrame();
//		new LoginFrame();
	}

}
