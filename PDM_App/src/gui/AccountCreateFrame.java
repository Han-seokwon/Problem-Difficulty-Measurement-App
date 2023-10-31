package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import users.AccountManager;
import users.User;
import users.UserDBManager;


public class AccountCreateFrame extends JFrame implements ActionListener{

	// Components
	private JLabel nameLabel, emailLabel, passwordLabel, passwordCheckLabel;
	private JTextField nameField, emailField;
	private JPasswordField passwordField, passwordCheckField;
	private JButton submitButton, cancelButton;

	// Constructor
	public AccountCreateFrame() {
		// Initialize components
        nameLabel = new JLabel("<html>이름: <br> (특수 문자 입력 불가)<html>");
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("<html>비밀번호: <br> (문자, 숫자 포함 8자리 이상)<html>");
        passwordCheckLabel = new JLabel("비밀번호 확인:");
        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        passwordCheckField = new JPasswordField();

        submitButton = new JButton("등록");
        cancelButton = new JButton("취소");
        // Set layout
        setLayout(new GridLayout(5, 2, 50, 50));

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(passwordCheckLabel);
        add(passwordCheckField);
        add(submitButton);		add(cancelButton);   
		
		// Add action listener to the submit button
		submitButton.addActionListener(this);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose(); // 창 닫기
			}
		});

		// Set frame properties
		setTitle("회원 가입");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 추후 DISPOSE_ON_CLOSE로 변경
		setVisible(true);
	}

	// Action event handler
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			String name = nameField.getText();
			String email = emailField.getText();
			String password = new String(passwordField.getPassword());
			String passwordCheck = new String(passwordCheckField.getPassword());
			
			// 디버깅용 추후 삭제
			System.out.println(name);
			System.out.println(email);
			System.out.println(password);
			System.out.println(passwordCheck);
			
			boolean isVaildInput = true;
			String dialogMsg = "정상적으로 회원등록되었습니다.";
			try {
				AccountManager.registerInputCheck(name, email, password, passwordCheck);
			} catch (IOException err) {
				dialogMsg = err.getMessage();		
				isVaildInput = false;
			} finally {
				int msgType = isVaildInput ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
				String title =  isVaildInput ? "회원가입 성공" : "회원가입 실패";
				JOptionPane.showMessageDialog(null, dialogMsg, title, msgType);
			}				
			if(isVaildInput) {	// 회원 정보
				AccountManager.createAccount(new User(name, email, password));
				dispose(); // 창 닫음
			}	
		}
	}

}
