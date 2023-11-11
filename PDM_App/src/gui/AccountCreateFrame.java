package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import file.FileManager;
import users.AccountManager;
import users.User;


public class AccountCreateFrame extends JFrame{

	// ������Ʈ ����
	private JTextField nameField, emailField, resetPwAnswerField;
	private JPasswordField passwordField, passwordConfirmField;
	private JComboBox<String> resetPwQuestionComboBox; 
	private String[] resetPwQuestionList; // ��� �ʱ�ȭ�� ������ ������ ����Ʈ -> �޺��ڽ��� ��ȯ
	private MainFrame mainFrame;
	// ������
	public AccountCreateFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		// ������Ʈ ���� �� �ʱ�ȭ
		JLabel nameLabel = new JLabel("<html>*����: <br> (Ư�� ���� �Է� �Ұ�)<html>");
		JLabel emailLabel = new JLabel("*�̸���:");
		JLabel passwordLabel = new JLabel("<html>*��й�ȣ: <br> (����, ���� ���� 8�ڸ� �̻�)<html>");
		JLabel passwordConfirmLabel = new JLabel("*��й�ȣ Ȯ��:");
		JLabel resetPwQuestionLabel = new JLabel("*��й�ȣ �ʱ�ȭ ����:");
		JLabel resetPwAnswerLabel = new JLabel("*�亯 : "); 
		
		
		nameField = new JTextField(15);
		emailField = new JTextField(20);
		passwordField = new JPasswordField(20);
		passwordConfirmField = new JPasswordField(20);

		String filepath = "\\users\\resetPwQuestions.txt";
		// ������ ����� �ؽ�Ʈ ���Ͽ��� List<String>���·� �� �������� �޾ƿ��� �̸� �迭�� ��ȯ
		resetPwQuestionList = FileManager.readLinesFromFile(filepath).toArray(new String[0]);
		resetPwQuestionComboBox = new JComboBox<>(resetPwQuestionList);

		resetPwAnswerField = new JTextField(20);
		JButton submitButton = new JButton("���");
		// ��� ��ư�� �̺�Ʈ �ڵ鷯 ����
		submitButton.addActionListener( new SubmitButtonListener());

		// ���̾ƿ�
		setLayout(new GridLayout(7, 2, 50, 50));

		// �� ������Ʈ �����ӿ� �߰�
		add(nameLabel);
		add(nameField);
		add(emailLabel);
		add(emailField);
		add(passwordLabel);
		add(passwordField);
		add(passwordConfirmLabel);
		add(passwordConfirmField);

		add(resetPwQuestionLabel);
		add(resetPwQuestionComboBox);   
		add(resetPwAnswerLabel);
		add(resetPwAnswerField);   

		add(submitButton);		add(new CancelButton()); // ��ҹ�ư �߰�

		// ������ �Ӽ� ����
		setTitle("ȸ�� ����");
		setSize(1000, 700);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setVisible(true);
	}


	class SubmitButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// �Էµ� ������ ��ȿ�� Ȯ�� 
			String name = nameField.getText();
			String email = emailField.getText();
			String password = new String(passwordField.getPassword());
			String passwordConfirm = new String(passwordConfirmField.getPassword());
			int selectedQuestionNum = resetPwQuestionComboBox.getSelectedIndex();						
			String answer = resetPwAnswerField.getText();


			// ������ ���� ����
			System.out.println(name);
			System.out.println(email);
			System.out.println(password);
			System.out.println(passwordConfirm);
			System.out.println(selectedQuestionNum);
			System.out.println(answer);

			boolean isVaildInput = true;
			String dialogMsg = "���������� ȸ����ϵǾ����ϴ�.";
			try {
				// ȸ������ ���� ��ȿ�� Ȯ��
				AccountManager.registerInputCheck(name, email, password, passwordConfirm, answer);
			} catch (IOException err) {
				dialogMsg = err.getMessage();		
				isVaildInput = false;
			} 
			
			int msgType = isVaildInput ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE;
			String title =  isVaildInput ? "ȸ������ ����" : "ȸ������ ����";
			JOptionPane.showMessageDialog(null, dialogMsg, title, msgType);	
			
			if(isVaildInput) {	// ȸ�� ������ ��ȿ�� ��� 
				// TODO : UserDB ����, ���� �ؽø� ���� ���п� ���� ���� ó���ϱ� 
				User newUser = new User(name, email, password, resetPwQuestionList[selectedQuestionNum], answer); // ���ο� User ��ü ����
				AccountManager.createAccount(newUser);
				mainFrame.logInComponents(newUser); // ���� ������ �α��� ������Ʈ ������Ʈ
				dispose(); // â ����
			}	
		}
	}
	
}














