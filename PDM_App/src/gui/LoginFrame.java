package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.AccountManager;

public class LoginFrame extends JFrame{
	// �α����� �����ϴ� ������, ��й�ȣ �ʱ�ȭ ��ư Ŭ���� �ʱ�ȭ�� �������� ������
	private JTextField emailField;
	private JPasswordField passwordField;
	public LoginFrame() {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� DISPOSE_ON_CLOSE�� ����
		setVisible(true);
	}


	class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String email = emailField.getText();
			String password = new String(passwordField.getPassword());
			boolean loginSuccess = true;
			String dialogMsg = "�α��� ����";
			try {
				AccountManager.checklogin(email, password);	// �α��� ���� ��ȿ�� Ȯ��					
			} catch (NullPointerException err) {					
				dialogMsg = err.getMessage();
				loginSuccess = false;					
			}	                
			JOptionPane.showMessageDialog(null, dialogMsg); // �α��� ����, ���� ���θ� �˷��ִ� �˾�â ����
			if(loginSuccess) {
				dispose();
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
