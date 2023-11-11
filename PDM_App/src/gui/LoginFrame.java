package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.AccountManager;
import users.User;

public class LoginFrame extends JFrame{
	// �α����� �����ϴ� ������, ��й�ȣ �ʱ�ȭ ��ư Ŭ���� �ʱ�ȭ�� �������� ������
	private JTextField emailField;
	private JPasswordField passwordField;
	// �α��� ������ ��� ������������ �α��� ���� ������Ʈ�� ������Ʈ �ϱ����� ����� ���������� ��ü�� ����
	private MainFrame mainFrame;  
	public LoginFrame(MainFrame mainFrame) {
		// ���� ������ ��ü�� ������Ʈ
		this.mainFrame = mainFrame; 
		
		// ������Ʈ ���� �� �ʱ�ȭ
		JLabel emailLabel = new JLabel("�̸���:");
		emailField = new JTextField(20);

		JLabel passwordLabel = new JLabel("��й�ȣ:");
		passwordField = new JPasswordField(20);

		JButton loginButton = new JButton("�α���");
		JButton resetPasswordButton = new JButton("��й�ȣ �ʱ�ȭ");

		// �α���, ��� �ʱ�ȭ ��ư�� �̺�Ʈ �ڵ鷯 ����
		loginButton.addActionListener(new LoginButtonListener());
		resetPasswordButton.addActionListener(new ResetButtonListener());


		// ���̾ƿ�
		setLayout(new GridLayout(3, 2, 70, 70));

		// �� ������Ʈ �����ӿ� �߰�
		add(emailLabel);
		add(emailField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		add(resetPasswordButton);

		// ������ �Ӽ� ����
		setTitle("�α���");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}


	class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String email = emailField.getText();
			String password = new String(passwordField.getPassword());
			boolean loginSuccess = true;
			String dialogMsg = "�α��� ����";	
			User user = new User();
			try {
				user = AccountManager.checklogin(email, password);	// �α��� ���� ��ȿ�� Ȯ��					
			} catch (NullPointerException err) {					
				dialogMsg = err.getMessage();
				loginSuccess = false;					
			}	                
			JOptionPane.showMessageDialog(null, dialogMsg); // �α��� ����, ���� ���θ� �˷��ִ� �˾�â ����
			System.out.println(user);
			if(loginSuccess) { // �α��� ������ ���				
				mainFrame.logInComponents(user); // ���� ������ �α��ΰ��� ������Ʈ ������Ʈ �� ���� �ν��Ͻ� ����
				// �α׾ƿ� ��ư���� �ٲٱ�
				dispose(); // �α��� â �ݱ�
			}
		}
	}

	class ResetButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e2) {   
			// ��й�ȣ �ʱ�ȭ �� �����̸�, �̸��� Ȯ�� �����ӿ� ����
			new PasswordResetUsernameEmailCheckFrame(); 
		}
	}




}
