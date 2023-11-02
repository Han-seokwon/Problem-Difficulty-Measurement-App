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
	// 로그인을 진행하는 프레임, 비밀번호 초기화 버튼 클릭시 초기화용 프레임을 오픈함
	private JTextField emailField;
	private JPasswordField passwordField;
	public LoginFrame() {

		// 컴포넌트 생성 및 초기화
		JLabel emailLabel = new JLabel("이메일:");
		emailField = new JTextField(20);

		JLabel passwordLabel = new JLabel("비밀번호:");
		passwordField = new JPasswordField(20);

		JButton loginButton = new JButton("로그인");
		JButton resetPasswordButton = new JButton("비밀번호 초기화");

		// 로그인, 비번 초기화 버튼에 이벤트 핸들러 적용
		loginButton.addActionListener(new LoginButtonListener());
		resetPasswordButton.addActionListener(new ResetButtonListener());


		// 레이아웃
		setLayout(new GridLayout(3, 2, 70, 70));

		// 각 컴포넌트 프레임에 추가
		add(emailLabel);
		add(emailField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		add(resetPasswordButton);

		// 프레임 속성 설정
		setTitle("로그인");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 추후 DISPOSE_ON_CLOSE로 변경
		setVisible(true);
	}


	class LoginButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String email = emailField.getText();
			String password = new String(passwordField.getPassword());
			boolean loginSuccess = true;
			String dialogMsg = "로그인 성공";
			try {
				AccountManager.checklogin(email, password);	// 로그인 정보 유효성 확인					
			} catch (NullPointerException err) {					
				dialogMsg = err.getMessage();
				loginSuccess = false;					
			}	                
			JOptionPane.showMessageDialog(null, dialogMsg); // 로그인 실패, 성공 여부를 알려주는 팝업창 오픈
			if(loginSuccess) {
				dispose();
			}
		}
	}

	class ResetButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e2) {   
			// 비밀번호 초기화 전 유저이름, 이메일 확인 프레임용 생성
			new PasswordResetUsernameEmailCheckFrame(); 
		}
	}




}
