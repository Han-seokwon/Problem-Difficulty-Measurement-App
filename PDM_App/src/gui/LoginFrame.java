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
	        JButton resetPasswordButton = new JButton("비밀번호 초기화");

	        loginButton.addActionListener(new LoginButtonListener());
	        resetPasswordButton.addActionListener(new ResetButtonListener());

	        JPanel panel = new JPanel(new GridLayout(3, 2));
	        panel.add(emailLabel);
	        panel.add(emailField);
	        panel.add(passwordLabel);
	        panel.add(passwordField);
	        panel.add(loginButton);
	        panel.add(resetPasswordButton);

	        add(panel);

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
                	AccountManager.checklogin(email, password);						
				} catch (NullPointerException err) {					
					dialogMsg = err.getMessage();
					loginSuccess = false;					
				}	                
                JOptionPane.showMessageDialog(LoginFrame.this, dialogMsg);
                if(loginSuccess) {
                	dispose();
                }
            }
	    }
	    
	    class ResetButtonListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e2) {   
            	// 비밀번호 초기화 전 유저이름, 이메일 확인 프레임 생성
                new PasswordResetUsernameEmailCheckFrame(); 
            }
	    }
	    
	    
	    

}
