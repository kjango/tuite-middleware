package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import model.FollowTO;
import model.SearchTO;
import model.Tuite;
import model.TuiteTO;
import model.User;
import control.CtrlLogin;
import control.CtrlSearch;
import control.CtrlTuite;
import control.CtrlUser;
import java.awt.Window.Type;

public class MainScreen extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private JPanel panelTimeLine;
	private JTextArea textAreaTuite;
	private JButton btnTuite;
	private JLabel lblCharCount;
	private JScrollPane scrollPane;
	private JTextField textFieldSearchPeople;
	private JTextField textFieldSearchTuites;
	private JScrollPane scrollPaneFollowing;
	private JScrollPane scrollPaneFollowers;
	private JPanel panelFollowersList;
	private JPanel panelFollowingList;
	private MainScreen me = this;
	
	private CtrlTuite ctrlTuite;
	private CtrlUser ctrlUser;
	private CtrlLogin ctrlLogin;
	private JButton btnSearchTuites;
	private JButton btnSearchPeople;
	private JPanel panelResPeople;
	private JPanel panelResTuites;
	private ArrayList<OtherTLScreen> tlList = new ArrayList<OtherTLScreen>();
	private JLabel lblUserPhoto;

	public MainScreen(User user, CtrlLogin ctrlLogin) {
		setType(Type.POPUP);
		this.user = user;
		this.ctrlLogin = ctrlLogin;
		
		try {
			ctrlTuite = new CtrlTuite(this);
			ctrlUser = new CtrlUser(this);
		} catch (Exception ex){
			
		}
		
		initialize();
	}
	
	protected void finalize() throws Throwable
	{
	  //do finalization here
	  ctrlLogin.doLogoff(user);
	  super.finalize(); //not necessary if extending Object.
	  
	} 

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Tuite - " + user.getRealName());
		setBounds(100, 100, 611, 442);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelTuites = new JPanel();
		tabbedPane.addTab("Timeline", null, panelTuites, null);
		tabbedPane.setMnemonicAt(0, 1);
		panelTuites.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNewTuite = new JPanel();
		panelTuites.add(panelNewTuite, BorderLayout.NORTH);
		panelNewTuite.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new TitledBorder(null, "Tuite", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNewTuite.add(panelButtons);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		textAreaTuite = new JTextArea();
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setRows(3);
		textAreaTuite.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnTuite.doClick();
//					textAreaTuite.setText("");
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
//					btnTuite.doClick();
					textAreaTuite.setText("");
				}
			}
		});
		textAreaTuite.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblCharCount.setText(String.valueOf(140 - textAreaTuite.getText().length()));
				
				if(textAreaTuite.getText().isEmpty()){
					btnTuite.setEnabled(false);
				}else{
					btnTuite.setEnabled(true);
				}
			}
		});
		panelButtons.add(textAreaTuite, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panelButtons.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		btnTuite = new JButton("Tuite");
		btnTuite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Criando o TO
				Tuite tuite = new Tuite(0,textAreaTuite.getText().toString(), new Timestamp(System.currentTimeMillis()), user);
				TuiteTO t = new TuiteTO(tuite);
				
				//Criando o controle
				ctrlTuite.truncate(t);
				
				//Chamar doTuite
				t = ctrlTuite.doTuite(t);

				user = t.getTuite().getMyUser();
				
				update();
				
				//Print de teste
//				System.out.println("\n\n\nEstou aqui no cliente:  "+t.getTuite().getText());
				
				textAreaTuite.setText("");
			}
		});
		btnTuite.setMnemonic('t');
		btnTuite.setEnabled(false);
		panel_1.add(btnTuite, BorderLayout.CENTER);
		
		lblCharCount = new JLabel("140");
		panel_1.add(lblCharCount, BorderLayout.SOUTH);
		lblCharCount.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblUserPhoto = new JLabel("");
		panelButtons.add(lblUserPhoto, BorderLayout.WEST);
		
		ImageIcon photo = new ImageIcon();
		photo = user.getPhoto();
		if (photo == null){
			photo = new ImageIcon("noImg.jpg");
		}
		lblUserPhoto.setIcon(photo);

		
		scrollPane = new JScrollPane();
		panelTuites.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setAutoscrolls(true);
		
		panelTimeLine = new JPanel();
		panelTimeLine.setBorder(new TitledBorder(null, "Timeline for " + user.getRealName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panelTimeLine);
		panelTimeLine.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelFollowing = new JPanel();
		tabbedPane.addTab("Following", null, panelFollowing, null);
		panelFollowing.setLayout(new BorderLayout(0, 0));
		
		scrollPaneFollowing = new JScrollPane();
		panelFollowing.add(scrollPaneFollowing);
		scrollPaneFollowing.setAutoscrolls(true);
		
		panelFollowingList = new JPanel();
		panelFollowingList.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "You are following", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panelFollowing.add(panelFollowingList);
		scrollPaneFollowing.setViewportView(panelFollowingList);
		panelFollowingList.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelFollowers = new JPanel();
		tabbedPane.addTab("Followers", null, panelFollowers, null);
		panelFollowers.setLayout(new BorderLayout(0, 0));
		
		scrollPaneFollowers = new JScrollPane();
		panelFollowers.add(scrollPaneFollowers);
		scrollPaneFollowers.setAutoscrolls(true);
		
		panelFollowersList = new JPanel();
		panelFollowersList.setBorder(new TitledBorder(null, "Your followers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panelFollowers.add(panelFollowersList);
		scrollPaneFollowers.setViewportView(panelFollowersList);
		panelFollowersList.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelTuiteSearch = new JPanel();
		tabbedPane.addTab("Search Tuites", null, panelTuiteSearch, null);
		tabbedPane.setMnemonicAt(3, 2);
		panelTuiteSearch.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTuiteSearch.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		textFieldSearchTuites = new JTextField();
		textFieldSearchTuites.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearchTuites.doClick();
					updateUser();
//					textAreaTuite.setText("");
				}
			}
		});
		textFieldSearchTuites.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(textFieldSearchTuites.getText().isEmpty()){
					btnSearchTuites.setEnabled(false);
				}else{
					btnSearchTuites.setEnabled(true);
				}
			}
		});
		textFieldSearchTuites.setColumns(10);
		panel.add(textFieldSearchTuites, BorderLayout.CENTER);
		
		btnSearchTuites = new JButton("Search");
		btnSearchTuites.setEnabled(false);
		btnSearchTuites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textFieldSearchTuites.getText().isEmpty()){					
					CtrlSearch ctrlSearch;
					try {
						
						//1 is for tuites, 2 for ppl
						SearchTO searchTO = new SearchTO(getUser(), textFieldSearchTuites.getText(), 1);
						ctrlSearch = new CtrlSearch();
						searchTO = ctrlSearch.doSearch(searchTO);
						
						panelResTuites.removeAll();
						for (Tuite tuit : searchTO.getResultTuites()) {
							panelResTuites.add(new TuitePanel(tuit, me));
						}
						repaint();
						textFieldSearchTuites.setText("");
						
					} catch (RemoteException e) {
						e.printStackTrace();
					}

				}
			}
		});
		panel.add(btnSearchTuites, BorderLayout.EAST);
		
		JScrollPane scrollPaneSearchTuites = new JScrollPane();
		panelTuiteSearch.add(scrollPaneSearchTuites, BorderLayout.CENTER);
		scrollPaneSearchTuites.setAutoscrolls(true);
		
		panelResTuites = new JPanel();
		panelResTuites.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panelTuiteSearch.add(panelResTuites, BorderLayout.SOUTH);
		scrollPaneSearchTuites.setViewportView(panelResTuites);
		panelResTuites.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelPeopleSearch = new JPanel();
		tabbedPane.addTab("Search People", null, panelPeopleSearch, null);
		tabbedPane.setMnemonicAt(4, 3);
		panelPeopleSearch.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPeopleSearch.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		textFieldSearchPeople = new JTextField();
		textFieldSearchPeople.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearchPeople.doClick();
//					textAreaTuite.setText("");
				}
			}
		});
		textFieldSearchPeople.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if(textFieldSearchPeople.getText().isEmpty()){
					btnSearchPeople.setEnabled(false);
				}else{
					btnSearchPeople.setEnabled(true);
				}
			}
		});
		panel_3.add(textFieldSearchPeople, BorderLayout.CENTER);
		textFieldSearchPeople.setColumns(10);
		
		btnSearchPeople = new JButton("Search");
		btnSearchPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!textFieldSearchPeople.getText().isEmpty()){
					
					CtrlSearch ctrlSearch;
					try {
						
						//1 is for tuites, 2 for ppl
						SearchTO searchTO = new SearchTO(getUser(), textFieldSearchPeople.getText(), 2);
						ctrlSearch = new CtrlSearch();
						searchTO = ctrlSearch.doSearch(searchTO);
						
						panelResPeople.removeAll();
						for (User usr : searchTO.getResultUsers()) {
							panelResPeople.add(new TuitePanel(usr, me));
						}
						repaint();
						textFieldSearchPeople.setText("");
						
					} catch (RemoteException e) {
						e.printStackTrace();
					}

				}
			}
		});
		btnSearchPeople.setEnabled(false);
		panel_3.add(btnSearchPeople, BorderLayout.EAST);
		
		JScrollPane scrollPaneSearchPeople = new JScrollPane();
		scrollPaneSearchPeople.setAutoscrolls(true);
		panelPeopleSearch.add(scrollPaneSearchPeople, BorderLayout.CENTER);
		
		panelResPeople = new JPanel();
		panelResPeople.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panelPeopleSearch.add(panelResPeople, BorderLayout.WEST);
		scrollPaneSearchPeople.setViewportView(panelResPeople);
		panelResPeople.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchive = new JMenu("File");
		mnArchive.setMnemonic('f');
		menuBar.add(mnArchive);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ctrlLogin.doLogoff(user);
				System.exit(0);
			}
		});
		
		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ctrlLogin.doLogoff(user);
				LoginScreen loginScreen = new LoginScreen();
				dispose();
				loginScreen.setVisible(true);
				
			}
		});
		mntmLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mnArchive.add(mntmLogout);
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnArchive.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic('e');
		menuBar.add(mnEdit);
		
		JMenuItem mntmEdit = new JMenuItem("Profile");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditProfileScreen editProfileScreen = new EditProfileScreen(user, me);
				editProfileScreen.setVisible(true);
				me.setEnabled(false);
			}
		});
		mntmEdit.setMnemonic('r');
		mnEdit.add(mntmEdit);
		
		update();
		
	}
	
	public void update(){
		setTitle("Tuite - " + user.getRealName());
		panelTimeLine.setBorder(new TitledBorder(null, "Timeline for " + user.getRealName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblUserPhoto.setIcon(user.getPhoto());
		panelTimeLine.removeAll();
		panelFollowingList.removeAll();
		panelFollowersList.removeAll();
		
		for (Tuite tu : user.getTuites()) {
			TuitePanel t = new TuitePanel(tu, this);
			panelTimeLine.add(t, 0);
		}
		    
	    for (User u : user.getFollowing()) {
			TuitePanel tp = new TuitePanel(u, this);
			panelFollowingList.add(tp);
		}
	    for (User u : user.getFollowers()) {
			TuitePanel tp = new TuitePanel(u, this);
			panelFollowersList.add(tp);
		}
	    for (Component tp : panelResTuites.getComponents()){
	    	if (tp instanceof TuitePanel){
	    		((TuitePanel) tp).update();
	    	}
	    }
	    for (Component tp : panelResPeople.getComponents()){
	    	if (tp instanceof TuitePanel){
	    		((TuitePanel) tp).update();
	    	}
	    }
	    for (OtherTLScreen otl : tlList) {
			otl.update();
		}
	    
		scrollPane.getVerticalScrollBar().setValue(0);
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
	    JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
	    verticalScrollBar.setValue(verticalScrollBar.getMinimum());
	    horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
	    
	    repaint();
	}

	public void updateUser(){
		User userTemp = ctrlUser.refreshUser(user);
		setUser(userTemp);
		update();
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CtrlUser getCtrlUser() {
		return ctrlUser;
	}
	public boolean addTL(OtherTLScreen otherTLScreen) {
		tlList.add(otherTLScreen);
		return true;
	}
	public boolean removeTL(OtherTLScreen otherTLScreen) {
		tlList.remove(otherTLScreen);
		return true;
	}
	public void notifyUserForFollow(FollowTO followTO){
		System.out.println("User: " + user.getLoginName() + " want to follow you, Accept?");
		
		int x = JOptionPane.showConfirmDialog(this, "User: " + followTO.getFollower().getRealName() + " want to follow you, Accept?",
				"Aplicação", 
				JOptionPane.YES_OPTION, 
				JOptionPane.NO_OPTION); 
				if (x == JOptionPane.YES_OPTION){ 
					//dispose();
					followTO.setNotifyFollower(false);
					ctrlUser.updateNotifyFromFollow(followTO);
				} else if (x == JOptionPane.NO_OPTION){
					//dispose(); 
					followTO.setNotifyFollower(true);
					ctrlUser.doUnFollow(followTO);
				}
	}

}
