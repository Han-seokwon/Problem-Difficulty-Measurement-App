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

	 private JTextField emailField;
	    private JPasswordField passwordField;

	    public LoginFrame() {
	        setTitle("로그인");

	        JLabel emailLabel = new JLabel("이메일:");
	        emailField = new JTextField(20);

	        JLabel passwordLabel = new JLabel("비밀번호:");
	        passwordField = new JPasswordField(20);

	        JButton loginButton = new JButton("로그인");
	        JButton findPasswordButton = new JButton("비밀번호 찾기");

	        loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String email = emailField.getText();
	                String password = new String(passwordField.getPassword());
	                boolean loginSuccess = true;
	                String dialogMsg = "로그인 성공";
	                try {
	                	AccountManager.loginCheck(email, password);						
					} catch (NullPointerException err) {
						err.printStackTrace();
						
						dialogMsg = err.getMessage();
						loginSuccess = false;					
					}	                
	                JOptionPane.showMessageDialog(LoginFrame.this, dialogMsg);
	                if(loginSuccess) {
	                	dispose();
	                }
	            }
	        });

	        findPasswordButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e2) {
	                // Add your password recovery logic here

	                JOptionPane.showMessageDialog(LoginFrame.this, "비밀번호 찾기 클릭됨.");
	            }
	        });

	        JPanel panel = new JPanel(new GridLayout(3, 2));
	        panel.add(emailLabel);
	        panel.add(emailField);
	        panel.add(passwordLabel);
	        panel.add(passwordField);
	        panel.add(loginButton);
	        panel.add(findPasswordButton);

	        add(panel);

	        setSize(500, 500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 추후 DISPOSE_ON_CLOSE로 변경
	        setVisible(true);
	    }

}
