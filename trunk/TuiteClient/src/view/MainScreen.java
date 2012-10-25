package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import model.Tuite;
import model.User;

public class MainScreen {

	private JFrame frmTuite;
	
	private static Tuite tuite;
	private static User user;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		//bloco para testes
		
		BufferedImage photo;
		try {
			photo = ImageIO.read(new File("img1.gif"));
			user = new User(0, "user@tests.com", "Hombre De Testes", photo, new Date(), false, null, null, null, null);
			tuite = new Tuite(0, "Cacete, este é um tuiteste.", new Date(), user);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 

		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frmTuite.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTuite = new JFrame();
		frmTuite.setTitle("Tuite");
		frmTuite.setBounds(100, 100, 611, 442);
		frmTuite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmTuite.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Tuite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JButton btnTuite = new JButton("Tuite");
		panel_1.add(btnTuite, BorderLayout.EAST);
		
		JFormattedTextField formattedTextFieldTuite = new JFormattedTextField();
		formattedTextFieldTuite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_1.add(formattedTextFieldTuite, BorderLayout.CENTER);
		
		TuitePanel panel_2 = new TuitePanel(tuite);
		frmTuite.getContentPane().add(panel_2, BorderLayout.CENTER);
		
		JMenuBar menuBar = new JMenuBar();
		frmTuite.setJMenuBar(menuBar);
		
		JMenu mnArchive = new JMenu("Archive");
		menuBar.add(mnArchive);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnArchive.add(mntmExit);
		
		JMenu mnFriends = new JMenu("Friends");
		menuBar.add(mnFriends);
	}

}
