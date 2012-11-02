package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import control.CtrlTuite;

import base.Compute;

import model.Tuite;
import model.User;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;

public class MainScreen extends javax.swing.JFrame {

	private User user;
	private JPanel panelTimeLine;
	private JFormattedTextField formattedTextFieldTuite;
	JButton btnTuite;
	private Compute compute;

	public MainScreen(User user,Compute compute) {
		this.user = user;
		this.compute = compute;
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
		
		formattedTextFieldTuite = new JFormattedTextField();
		formattedTextFieldTuite.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnTuite.doClick();
				}
			}
		});
		formattedTextFieldTuite.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(!formattedTextFieldTuite.getText().isEmpty()){
					btnTuite.setEnabled(true);
				}else{
					btnTuite.setEnabled(false);
				}
			}
		});
		formattedTextFieldTuite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNewTuite.add(formattedTextFieldTuite, BorderLayout.CENTER);
		
		btnTuite = new JButton("Tuite");
		btnTuite.setEnabled(false);
		btnTuite.setMnemonic('t');
		btnTuite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tuite tuite;
				//TODO Finalizar a implementação dessa ação
				System.err.println(formattedTextFieldTuite.getText());

				//Criando o TO
				Tuite t = new Tuite(0,formattedTextFieldTuite.getText().toString(), new Date(), user);
				
				//Criando o controle
				CtrlTuite ctrl = new CtrlTuite();
				ctrl.truncate(t);
				
				//Chamar doTuite
				tuite = ctrl.doTuite(t, compute);
				
				//Print de teste
				System.out.println("\n\n\nEstou aqui no cliente:  "+tuite.getText());
				
				formattedTextFieldTuite.setText("");
			}
		});
		panelNewTuite.add(btnTuite, BorderLayout.EAST);

		
		JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		panelTimeLine = new JPanel();
		panelTimeLine.setBorder(new TitledBorder(null, "Timeline for " + user.getRealName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panelTimeLine);
		panelTimeLine.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchive = new JMenu("File");
		mnArchive.setMnemonic('f');
		menuBar.add(mnArchive);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnArchive.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic('e');
		menuBar.add(mnEdit);
		
		JMenuItem mntmEdit = new JMenuItem("Profile");
		mntmEdit.setMnemonic('r');
		mnEdit.add(mntmEdit);
		
		JMenu mnFriends = new JMenu("Search");
		mnFriends.setMnemonic('s');
		menuBar.add(mnFriends);
		
		JMenuItem mntmSearchPeople = new JMenuItem("People");
		mntmSearchPeople.setMnemonic('p');
		mnFriends.add(mntmSearchPeople);
		
		JMenuItem mntmSearchTuites = new JMenuItem("Tuites");
		mntmSearchTuites.setMnemonic('t');
		mnFriends.add(mntmSearchTuites);
		
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
