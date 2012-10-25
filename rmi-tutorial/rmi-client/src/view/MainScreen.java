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
import javax.swing.JScrollPane;
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
			photo = ImageIO.read(new File("img1.jpg"));
			user = new User(0, "user@tests.com", "Hombre De Testes", photo, new Date(), false, null, null, null, null);
			tuite = new Tuite(0, "Cacete, este é um tuiteste.", new Date(), user);
			System.out.println("mama");
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
		
		JPanel panelNewTuite = new JPanel();
		panelNewTuite.setBorder(new TitledBorder(null, "Tuite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panelNewTuite);
		panelNewTuite.setLayout(new BorderLayout(0, 0));
		
		JButton btnTuite = new JButton("Tuite");
		panelNewTuite.add(btnTuite, BorderLayout.EAST);
		
		JFormattedTextField formattedTextFieldTuite = new JFormattedTextField();
		formattedTextFieldTuite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNewTuite.add(formattedTextFieldTuite, BorderLayout.CENTER);
		
		JPanel panelTimeLine = new JPanel();
		panelTimeLine.setBorder(new TitledBorder(null, "TimeLine", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmTuite.getContentPane().add(panelTimeLine, BorderLayout.CENTER);
		panelTimeLine.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTuites = new JScrollPane();
		panelTimeLine.add(scrollPaneTuites, BorderLayout.CENTER);
		
		TuitePanel panel_Teste = new TuitePanel(tuite);
		scrollPaneTuites.setViewportView(panel_Teste);
		
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
