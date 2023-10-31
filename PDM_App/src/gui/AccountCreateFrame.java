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
        nameLabel = new JLabel("<html>�̸�: <br> (Ư�� ���� �Է� �Ұ�)<html>");
        emailLabel = new JLabel("Email:");
        passwordLabel = new JLabel("<html>��й�ȣ: <br> (����, ���� ���� 8�ڸ� �̻�)<html>");
        passwordCheckLabel = new JLabel("��й�ȣ Ȯ��:");
        nameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        passwordCheckField = new JPasswordField();

        submitButton = new JButton("���");
        cancelButton = new JButton("���");
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
				dispose(); // â �ݱ�
			}
		});

		// Set frame properties
		setTitle("ȸ�� ����");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ���� DISPOSE_ON_CLOSE�� ����
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
			
			// ������ ���� ����
			System.out.println(name);
			System.out.println(email);
			System.out.println(password);
			System.out.println(passwordCheck);
			
			boolean isVaildInput = true;
			String dialogMsg = "���������� ȸ����ϵǾ����ϴ�.";
			try {
				AccountManager.registerInputCheck(name, email, password, passwordCheck);
			} catch (IOException err) {
				dialogMsg = err.getMessage();		
				isVaildInput = false;
			} finally {
				int msgType = isVaildInput ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
				String title =  isVaildInput ? "ȸ������ ����" : "ȸ������ ����";
				JOptionPane.showMessageDialog(null, dialogMsg, title, msgType);
			}				
			if(isVaildInput) {	// ȸ�� ����
				AccountManager.createAccount(new User(name, email, password));
				dispose(); // â ����
			}	
		}
	}

}
