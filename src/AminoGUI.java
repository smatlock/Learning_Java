package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class AminoGUI extends JFrame
{
	private static final long serialVersionUID = 3794059922116115530L;
	
	private JTextArea aTextArea = new JTextArea();
	private JButton cancelButton = new JButton("cancel");
	private JButton redoButton = new JButton("re-do");
	private JPanel buttonPanel = new JPanel();
	
	public AminoGUI(String title)
	{
		super(title);
		setLocationRelativeTo(null);
		setSize(350,350);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonPanel.setLayout(new BorderLayout());
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(aTextArea, BorderLayout.CENTER);
		aTextArea.setText("************~~~~~************\n"
				+ "AMINO ACID QUIZ.\n"
				+ "************~~~~~************\n\n");
		
		setJMenuBar(getMyMenuBar());
		getContentPane().add(buttonPane(), BorderLayout.SOUTH);
		
//		updateTextField();
		setVisible(true);
	}
	
	
	private JPanel buttonPane()
	{
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1,3));
		buttonPane.add(redoButton);
		buttonPane.add(cancelButton);
		return buttonPane;
	}
	
	private JMenuBar getMyMenuBar()
	{
		JMenuBar jmenuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("Selection");
		fileMenu.setMnemonic('S');
		jmenuBar.add(fileMenu);
		
		JMenuItem timeLimit = new JMenuItem("Time Trial");
		fileMenu.add(timeLimit);
		timeLimit.setMnemonic('T');
		
		JMenuItem questionLimit = new JMenuItem("Max Questions");
		fileMenu.add(questionLimit);
		questionLimit.setMnemonic('Q');
		
		return jmenuBar;
	}
	
	
	public static void main(String[] args)
	{
		new AminoGUI("Amino Quiz");
	}
	
}
