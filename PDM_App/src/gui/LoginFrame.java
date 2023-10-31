package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame{

	 private JTextField emailField;
	    private JPasswordField passwordField;

	    public LoginFrame() {
	        setTitle("�α���");

	        JLabel emailLabel = new JLabel("�̸���:");
	        emailField = new JTextField(20);

	        JLabel passwordLabel = new JLabel("��й�ȣ:");
	        passwordField = new JPasswordField(20);

	        JButton loginButton = new JButton("�α���");
	        JButton findPasswordButton = new JButton("��й�ȣ ã��");

	        loginButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String email = emailField.getText();
	                String password = new String(passwordField.getPassword());

	                
	                // Add your login logic here

	                JOptionPane.showMessageDialog(LoginFrame.this, "�α��� ����!");
	            }
	        });

	        findPasswordButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // Add your password recovery logic here

	                JOptionPane.showMessageDialog(LoginFrame.this, "��й�ȣ ã�� Ŭ����.");
	            }
	        });

	        JPanel panel = new JPanel(new GridLayout(3, 2));
	        panel.add(emailLabel);
	        panel.add(emailField);
	        panel.add(passwordLabel);
	        panel.add(passwordField);
	        panel.add(loginButton);
	        panel.add(recoveryButton);

	        add(panel);

	        setSize(300, 150);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setVisible(true);
	    }

}
