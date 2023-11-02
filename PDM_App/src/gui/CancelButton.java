package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CancelButton extends JButton{
	public CancelButton() {
		this("취소");
	}
	public CancelButton(String btnName) {
		super.setText(btnName);
		super.addActionListener(new CancleButtonListener());
	}
	
	class CancleButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("취소버튼 클릭!!");
			// 프레임 닫기
			Component source = (Component) e.getSource();
			JFrame frame = (JFrame) SwingUtilities.getRoot(source); 
			frame.dispose();
		}
		
	}
}
