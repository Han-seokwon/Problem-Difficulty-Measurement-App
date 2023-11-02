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

public class PasswordResetUsernameEmailCheckFrame extends JFrame {
	private JTextField usernameField;
	private JTextField emailField;
	private JButton nextButton;
	private User user;
	public PasswordResetUsernameEmailCheckFrame() {
		
		// 컴포넌트 생성 및 초기화
		JLabel usernameLabel = new JLabel("유저 이름:");
		usernameField = new JTextField();
		JLabel emailLabel = new JLabel("이메일:");
		emailField = new JTextField();
		nextButton = new JButton("다음");
		// 다음 버튼에 이벤트 핸들러 적용
		nextButton.addActionListener(new CheckNameEmailListener());
		
		
		// 레이아웃
		setLayout(new GridLayout(3, 2, 70, 70));
		
		// 각 컴포넌트 프레임에 추가
		add(usernameLabel);
		add(usernameField);
		add(emailLabel);
		add(emailField);
		add(new CancelButton()); 		
		add(nextButton);	

		// 프레임 속성 설정
		setTitle("비밀번호 초기화");
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
				// 유저가 존재하는 것을 확인했으므로 예외처리 필요없음
				user = UserDBManager.findUserByEmail(email); // 유저 설정 -> 참조로 전달하므로 다른 프레임에도 공유됨
				if( username.equals(user.getUsername())) {
					dispose();
					// 비밀번호 초기화용 질문, 답변 확인 프레임 생성
					new PasswordResetAnswerCheckFrame(user); 				
				} else {
					JOptionPane.showMessageDialog(null, "유저 이름이 일치하지 않습니다.", "인증 실패", JOptionPane.WARNING_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(null, "해당 유저가 존재하지 않습니다.", "인증 실패", JOptionPane.WARNING_MESSAGE);
			}

		}

	}
}