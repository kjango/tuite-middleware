package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import model.Tuite;
import model.User;

public class MainScreen extends javax.swing.JFrame {

	private User user;
	private JPanel panelTimeLine;

	public MainScreen(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Tuite - " + user.getRealName());
		setBounds(100, 100, 611, 442);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelNewTuite = new JPanel();
		panelNewTuite.setBorder(new TitledBorder(null, "Tuite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelNewTuite);
		panelNewTuite.setLayout(new BorderLayout(0, 0));
		
		JButton btnTuite = new JButton("Tuite");
		panelNewTuite.add(btnTuite, BorderLayout.EAST);
		
		JFormattedTextField formattedTextFieldTuite = new JFormattedTextField();
		formattedTextFieldTuite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNewTuite.add(formattedTextFieldTuite, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		panelTimeLine = new JPanel();
		panelTimeLine.setBorder(new TitledBorder(null, "Timeline for " + user.getRealName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panelTimeLine);
		panelTimeLine.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchive = new JMenu("Archive");
		menuBar.add(mnArchive);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnArchive.add(mntmExit);
		
		JMenu mnFriends = new JMenu("Friends");
		menuBar.add(mnFriends);
		
		updateTimeLine();
		
	}
	
	public void updateTimeLine(){
		for (int i = user.getTuites().size() -1 ; i > user.getTuites().size() - 11 && i >= 0 ; i--) {
			Tuite tu = user.getTuites().get(i);
			TuitePanel t = new TuitePanel(user, tu);
			panelTimeLine.add(t);
		}
//		this.
	}

}
