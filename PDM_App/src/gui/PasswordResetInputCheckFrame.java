package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import users.AccountManager;
import users.User;
import users.UserDBManager;

public class PasswordResetInputCheckFrame extends JFrame{
	
//	 ��й�ȣ �ʱ�ȭ ���� 3�ܰ�� �����̸�, �̸���, ��� �ʱ�ȭ �亯�� ��� ������ ����ڰ� ���������� ���Ӱ� ������ ��й�ȣ�� �Է��ϴ� ������
	
	private JPasswordField newPasswordField;
	private JPasswordField passwordConfirmField;
	private JButton resetButton;
	private User user;
	// ���� ��й�ȣ�� �ʱ�ȭ�Ϸ��� User �ν��Ͻ��� ���ڷ� ����
	public PasswordResetInputCheckFrame(User user) {
		this.user = user;
		
		// ������Ʈ ���� �� �ʱ�ȭ
		JPanel panel = new JPanel(new GridLayout(3, 2));        

		JLabel newPasswordLabel = new JLabel("���ο� ��й�ȣ:");
		newPasswordField = new JPasswordField();

		JLabel confirmPasswordLabel = new JLabel("��й�ȣ Ȯ��:");
		passwordConfirmField = new JPasswordField();

		resetButton = new JButton("��й�ȣ �ʱ�ȭ");
		// �ʱ�ȭ ��ư�� �̺�Ʈ �ڵ鷯 ����
		resetButton.addActionListener(new CheckNewPasswordListener());

		// ���̾ƿ�
		setLayout(new GridLayout(3, 2, 70, 70));
		
		panel.add(newPasswordLabel);
		panel.add(newPasswordField);
		panel.add(confirmPasswordLabel);
		panel.add(passwordConfirmField);
		panel.add(new CancelButton());
		panel.add(resetButton);
		add(panel);
		
		// ������ �Ӽ� ����
		setTitle("��й�ȣ �ʱ�ȭ");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setVisible(true);

	}

	class CheckNewPasswordListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean isValidPw = true;
			String newPassword = new String(newPasswordField.getPassword());
			String newPasswordConfirm = new String(passwordConfirmField.getPassword());

			try {// �ʱ�ȭ ��й�ȣ ��ȿ�� Ȯ��
				AccountManager.checkPasswordVaildity(newPassword, newPasswordConfirm);
			} catch (IOException err) {
				JOptionPane.showMessageDialog(null, err.getMessage(), "��й�ȣ �ʱ�ȭ ����", JOptionPane.WARNING_MESSAGE);
				isValidPw = false;
			} 
			if(isValidPw) {
//				 ��й�ȣ �ʱ�ȭ�� �Ϸ�Ǿ��ٴ� �˾�â�� ���� ��й�ȣ�� ������Ʈ��
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �ʱ�ȭ�Ǿ����ϴ�.", "��й�ȣ �ʱ�ȭ ����", JOptionPane.INFORMATION_MESSAGE);
				AccountManager.updatePassword(user, newPassword);// ��й�ȣ ������Ʈ
				dispose(); 
			}
		}
	}

}
