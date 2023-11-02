package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CancelButton extends JButton{
	public CancelButton() {
		this("���");
	}
	public CancelButton(String btnName) {
		super.setText(btnName);
		super.addActionListener(new CancleButtonListener());
	}
	
	class CancleButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("��ҹ�ư Ŭ��!!");
			// ������ �ݱ�
			Component source = (Component) e.getSource();
			JFrame frame = (JFrame) SwingUtilities.getRoot(source); 
			frame.dispose();
		}
		
	}
}
