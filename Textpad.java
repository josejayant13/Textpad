import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
public class Textpad extends JFrame implements ActionListener{
	
	JTextArea area;
	JScrollPane pane;
	String text;
	Textpad(){
		setBounds(0,0,1270,720);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(new Color(0x00adb5));
		menubar.setForeground(new Color(0x000000));
		menubar.setBorder(BorderFactory.createEmptyBorder());
		menubar.setFont(new Font("CONSOLAS", Font.BOLD, 20));
		//File Menu
		JMenu file = new JMenu("File");
		JMenuItem newdoc = new JMenuItem("New");
		newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newdoc.addActionListener(this);
		
		JMenuItem open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(this);
		
		JMenuItem print = new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));		
		print.addActionListener(this);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));		
		exit.addActionListener(this);
		file.add(newdoc);
		file.add(open);
		file.add(save);
		file.add(print);
		file.add(exit);
		
		//Edit Menu
		JMenu edit = new JMenu("Edit");
		JMenuItem copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		
		JMenuItem paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		
		JMenuItem cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		
		JMenuItem selectAll = new JMenuItem("Select All");
		selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));		
		selectAll.addActionListener(this);
		
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(selectAll);

		//Help Menu
		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About Textpad");
		about.addActionListener(this);
		
		JMenuItem website = new JMenuItem("Visit Website!");
		website.addActionListener(this);
		
		help.add(about);
		help.add(website);
		
		//Menu Bar
		menubar.add(file);
		menubar.add(edit);
		menubar.add(help);
		
		setJMenuBar(menubar);
		
		area = new JTextArea();
		area.setFont(new Font("CONSOLAS", Font.PLAIN, 18));
		area.setForeground(new Color(0x222831));
		area.setBackground(new Color(0xeeeeee));
		area.setLineWrap(true);
		area.setWrapStyleWord(true);
		area.setBorder(BorderFactory.createEmptyBorder());
		
		pane = new JScrollPane(area);
		pane.setBorder(BorderFactory.createEmptyBorder());
		add(pane , BorderLayout.CENTER);
		
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals("New"))
		{
			area.setText("");
		}
		else if(e.getActionCommand().equals("Open"))
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
			chooser.addChoosableFileFilter(restrict);
			int action = chooser.showOpenDialog(this);
			if(action!=JFileChooser.APPROVE_OPTION)
				return;
			File file = chooser.getSelectedFile();
			try {
					BufferedReader reader = new BufferedReader(new FileReader(file));
					area.read(reader,null);
			}catch(Exception ev)
			{
			}
		}
		else if(e.getActionCommand().equals("Print"))
				{
					try {
						area.print();
					}
					catch(Exception ev)
					{
						
					}
				}
	
		else if(e.getActionCommand().equals("Save"))
		{
			JFileChooser saveAs = new JFileChooser();
			saveAs.setApproveButtonText("Save");
			saveAs.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
			saveAs.addChoosableFileFilter(restrict);
			int action = saveAs.showOpenDialog(this);
			if(action!=JFileChooser.APPROVE_OPTION)
				return;
		
		File filename = new File(saveAs.getSelectedFile() + ".txt");
		BufferedWriter outFile = null;
		try {
			outFile = new BufferedWriter(new FileWriter(filename));
			area.write(outFile);
		}catch(Exception ev){}
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Copy"))
		{
			text = area.getSelectedText();
		}
		else if(e.getActionCommand().equals("Paste"))
		{
			area.insert(text,area.getCaretPosition());
		}
		else if(e.getActionCommand().equals("Cut"))
		{
			text = area.getSelectedText();
			area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
			
		}
		else if(e.getActionCommand().equals("Select All")) {
			area.selectAll();
		}
		else if(e.getActionCommand().equals("About Textpad"))
		{
			new About().setVisible(true);
		}
		else if(e.getActionCommand().equals("Visit Website!"))
		{
			try {
				  Desktop desktop = java.awt.Desktop.getDesktop();
				  URI oURL = new URI("https://josejayant13.github.io/Resume/");
				  desktop.browse(oURL);
				} catch (Exception ev) {
				
				}
		}
			
	}
	
	public static void main(String[] args) {
		
		new Textpad().setVisible(true);

	}

}
