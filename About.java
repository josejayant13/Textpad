import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame implements ActionListener {
	JButton b1;
	About()
	{
		setBounds(600,200,500,400);
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
		Image i2 = i1.getImage().getScaledInstance(400, 52, Image.SCALE_SMOOTH);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel l1 =  new JLabel(i3);
		l1.setBounds(45,50,400,52);
		add(l1);
		
		JLabel l2 = new JLabel("<html>My Name is Jose Jayant.<br>"
									+ "I'm a Student in NIT Durgapur.<br>"+
										"This is a simple text editing app.<br>");
		l2.setBounds(80,100,400,100);
		l2.setFont(new Font("CONSOLAS", Font.ITALIC, 16));
		add(l2);
		b1 = new JButton("OK");
		b1.addActionListener(this);
		b1.setBounds(200, 190, 80, 25);
		add(b1);
	}
	public void actionPerformed(ActionEvent e)
	{
		this.setVisible(false);
	}
	public static void main(String[] args) {
	new About().setVisible(true);
;
	}

}
