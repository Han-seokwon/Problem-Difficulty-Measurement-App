package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import file.FileManager;
import gui.AccountCreateFrame.SubmitButtonListener;
import users.AccountManager;
import users.User;

public class MainFrame extends JFrame{
	// ������Ʈ ����
	private JLabel usernameLabel;
	private JButton accountCreateButton, loginButton, problemListButton, problemRecommendButton, myPageButton;
	private User user = new User();
	private boolean isLoggedin = false;

	public void logInComponents(User user) {
		isLoggedin = true;
		this.user = user;
		usernameLabel.setText(user.getUsername());		
		loginButton.setText("�α׾ƿ�");
		accountCreateButton.setVisible(false); // ȸ������ �Ұ���
	}
	public void logOutComponents() {
		isLoggedin = false;
		this.user = new User();
		usernameLabel.setText("");		
		loginButton.setText("�α���");
		accountCreateButton.setVisible(true); // ȸ������ ����
	}

	// ������
	public MainFrame() {
		String siteName = "����Ʈ �̸�";
		// ������Ʈ ���� �� �ʱ�ȭ
		usernameLabel = new JLabel();
		JLabel siteNameLabel = new JLabel(siteName);
		JLabel introLabel = new JLabel("����Ʈ �Ұ� ��");

		accountCreateButton = new JButton("���� ����");
		accountCreateButton.addActionListener(new ButtonActionListener(this));

		loginButton = new JButton("�α���");
		loginButton.addActionListener(new ButtonActionListener(this));

		problemListButton = new JButton("���� ����Ʈ Ȯ��");
		problemListButton.addActionListener(new ButtonActionListener());

		problemRecommendButton = new JButton("���� ��õ �ޱ�");
		problemRecommendButton.addActionListener(new ButtonActionListener());

		myPageButton = new JButton("���� ������");
		myPageButton.addActionListener(new ButtonActionListener());

		// ���� ���� ��ư ��� �г�
		JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		accountPanel.add(usernameLabel);
		accountPanel.add(accountCreateButton);
		accountPanel.add(loginButton);

		// ����Ʈ �ΰ� �� �Ұ��� ��� �г� (�߾� ��ġ)
		JPanel introPanel = new JPanel(new FlowLayout()); // GridLayout�� ���߾ӿ� ��ġ��Ű�� ���� �г� 
		JPanel innerPanel = new JPanel(new GridLayout(2,1));
		innerPanel.add(siteNameLabel);
		innerPanel.add(introLabel);
		introPanel.add(innerPanel);

		// ����Ʈ ��� ��ư ��� �г�		
		JPanel functionPanel = new JPanel(new FlowLayout());
		functionPanel.add(problemListButton);
		functionPanel.add(problemRecommendButton);
		functionPanel.add(myPageButton);

		// ���̾ƿ�
		setLayout(new BorderLayout(50, 50));

		// �� ������Ʈ �����ӿ� �߰�
		add(introPanel, BorderLayout.CENTER);
		add(accountPanel, BorderLayout.NORTH);
		add(functionPanel, BorderLayout.SOUTH);

		// ������ �Ӽ� ����
		setTitle(siteName);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setVisible(true);
	}


	class ButtonActionListener implements ActionListener{
		public MainFrame mainFrame;
		public ButtonActionListener() {}
		public ButtonActionListener(MainFrame mainFrame) {
			this.mainFrame = mainFrame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(!isLoggedin) { // �α��� �� �� ���
				if(e.getSource() == accountCreateButton) { // ���� ���� 
					new AccountCreateFrame(mainFrame);				
				} else if (e.getSource() == loginButton) { // �α���
					new LoginFrame(mainFrame);	// �α��� ������ ����				
				} else {
					JOptionPane.showMessageDialog(null, "�α����� �ʿ��մϴ�.", "�ش� ��� ��� �Ұ�", JOptionPane.WARNING_MESSAGE);	
				}
			} else { // �α��� �� ���
				if (e.getSource() == loginButton) {
					logOutComponents(); // �α׾ƿ�
				} else if (e.getSource() == problemListButton) {
					System.out.println("problemListButton clicked!");
				} else if (e.getSource() == problemRecommendButton) {
					System.out.println("problemRecommendButton clicked!");
				} else if (e.getSource() == myPageButton) {
					System.out.println("myPageButton clicked!");
				}
			}
		}
	}
}















