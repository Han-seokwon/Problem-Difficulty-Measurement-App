package gui;

import java.awt.CardLayout;

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
//��й�ȣ �ʱ�ȭ ���� 1�ܰ� : �����̸�, �̸��� Ȯ�� -> 2�ܰ� : �ʱ�ȭ ������ ���� �亯 Ȯ�� -> 3 �ܰ� : ���Ӱ� ������ ��й�ȣ �Է�

public class PasswordResetAnswerCheckFrame extends JFrame {
	// << ��й�ȣ �ʱ�ȭ ���� 2�ܰ� >>
	// �����̸�, �̸����� ���� Ȯ�ε� ������� ��� �ʱ�ȭ ������ ������ �亯�� �Է¹޴� ������
	
	private String resetQuestion, resetAnswer;
	private JTextField answerField;
	private JButton nextButton;
	private User user;
	
	// ���� ��й�ȣ�� �ʱ�ȭ�Ϸ��� User �ν��Ͻ��� ���ڷ� ����
	public PasswordResetAnswerCheckFrame(User user) {   
		this.user = user;
		
		resetQuestion = user.getPwResetQuestion(); // ��ϵ� ������ ������
		resetAnswer = user.getPwResetAnswer(); // ��ϵ� �亯 ������
		
		// ������Ʈ ���� �� �ʱ�ȭ
		JLabel resetQuestionJLabel = new JLabel("����: " + resetQuestion);
		System.out.println(user.toString());
		
		answerField = new JTextField();
		nextButton = new JButton("����");
		// ��� ��ư�� �̺�Ʈ �ڵ鷯 ����
		nextButton.addActionListener(new CheckAnswerListener());
		
		// ���̾ƿ�
		setLayout(new GridLayout(3, 2, 70, 70));
		
		// �� ������Ʈ �����ӿ� �߰�
		add(resetQuestionJLabel);
		add(answerField);
		add(new CancelButton()); 
		add(nextButton);
		
		// ������ �Ӽ� ����
		setTitle("��й�ȣ �ʱ�ȭ");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setVisible(true);
	}
	
	class CheckAnswerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// �亯�� �´��� Ȯ��
			if(resetAnswer.equals(answerField.getText())) {
				dispose();
				// ��й�ȣ �ʱ�ȭ ������ ����
				new PasswordResetInputCheckFrame(user);
			} else {
				System.out.println(resetAnswer);
				System.out.println(answerField.getText());
				
				JOptionPane.showMessageDialog(null, "�亯�� Ʋ���ϴ�.", "���� ����", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

}
