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

import users.Account;
import users.ResisterManager;


public class AccountGUI extends JFrame implements ActionListener{

	// Components
	private JLabel nameLabel, emailLabel, passwordLabel;
	private JTextField nameField, emailField;
	private JPasswordField passwordField;
	private JButton submitButton, cancelButton;

	// Constructor
	public AccountGUI() {
		// Initialize components
        nameLabel = new JLabel("<html>이름: <br> (특수 문자 입력 불가)<html>");
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("<html>Password: <br> (문자, 숫자 포함 8자리 이상)<html>");

        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        submitButton = new JButton("등록");
        cancelButton = new JButton("취소");
        // Set layout
        setLayout(new GridLayout(4, 2, 50, 50));

        // Add components to the frame
        add(nameLabel);
        add(nameField);
        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(submitButton);		add(cancelButton);   
		
		// Add action listener to the submit button
		submitButton.addActionListener(this);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
			
			System.out.println(name);
			System.out.println(email);
			System.out.println(password);
			boolean isVaildInput = true;
			String dialogMsg = "정상적으로 회원등록되었습니다.";
			try {
				Account.inputCheck(name, email, password);
			} catch (IOException err) {
				dialogMsg = err.getMessage();		
				isVaildInput = false;
			} finally {
				int msgType = isVaildInput ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
				String title =  isVaildInput ? "회원가입 성공" : "회원가입 실패";
				JOptionPane.showMessageDialog(null, dialogMsg, title, msgType);
			}				
			if(isVaildInput) {	// 회원 등록 완료			
				dispose(); // 창 닫음
			}	
		}
	}

	// Main method to run the application
	public static void main(String[] args) {
		new AccountGUI();
	}

}
