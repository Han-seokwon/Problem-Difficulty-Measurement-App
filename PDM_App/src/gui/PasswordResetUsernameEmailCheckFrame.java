package gui;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import users.User;
import users.UserDBManager;

// ��й�ȣ �ʱ�ȭ ���� 1�ܰ� : �����̸�, �̸��� Ȯ�� -> 2�ܰ� : �ʱ�ȭ ������ ���� �亯 Ȯ�� -> 3 �ܰ� : ���Ӱ� ������ ��й�ȣ �Է�

public class PasswordResetUsernameEmailCheckFrame extends JFrame {
	// << ��й�ȣ �ʱ�ȭ ���� 1�ܰ� >>
	// �����̸�, �̸��ϸ� �Է��Ͽ� ��й�ȣ�� �ʱ�ȭ�� ����ڸ� ã�� ������
 

	private JTextField usernameField;
	private JTextField emailField;
	private User user;
	public PasswordResetUsernameEmailCheckFrame() {
		
		// ������Ʈ ���� �� �ʱ�ȭ
		JLabel usernameLabel = new JLabel("���� �̸�:");
		usernameField = new JTextField();
		JLabel emailLabel = new JLabel("�̸���:");
		emailField = new JTextField();
		JButton nextButton = new JButton("����");
		// ���� ��ư�� �̺�Ʈ �ڵ鷯 ����
		nextButton.addActionListener(new CheckNameEmailListener());
		
		
		// ���̾ƿ�
		setLayout(new GridLayout(3, 2, 70, 70));
		
		// �� ������Ʈ �����ӿ� �߰�
		add(usernameLabel);
		add(usernameField);
		add(emailLabel);
		add(emailField);
		add(new CancelButton()); 		
		add(nextButton);	

		// ������ �Ӽ� ����
		setTitle("��й�ȣ �ʱ�ȭ");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setVisible(true);

	}
	class CheckNameEmailListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String username = usernameField.getText();
			String email = emailField.getText();
			if(UserDBManager.isEmailExist(email)) {
				// ������ �����ϴ� ���� Ȯ�������Ƿ� ����ó�� �ʿ����
				user = UserDBManager.findUserByEmail(email); // ���� ���� -> ������ �����ϹǷ� �ٸ� �����ӿ��� ������
				if( username.equals(user.getUsername())) {
					dispose();
					// ��й�ȣ �ʱ�ȭ�� ����, �亯 Ȯ�� ������ ����
					new PasswordResetAnswerCheckFrame(user); 				
				} else {
					JOptionPane.showMessageDialog(null, "���� �̸��� ��ġ���� �ʽ��ϴ�.", "���� ����", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "�ش� ������ �������� �ʽ��ϴ�.", "���� ����", JOptionPane.WARNING_MESSAGE);
			}

		}

	}
}