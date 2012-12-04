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
import java.util.Iterator;

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
import control.CtrlTwitter;
import control.CtrlUser;
import control.RunUpdate;

import java.awt.Window.Type;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class MainScreen.
 */
public class MainScreen extends javax.swing.JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user. */
	private User user;
	
	/** The panel timeline. */
	private JPanel panelTimeLine;
	
	/** The text area to write tweets. */
	private JTextArea textAreaTuite;
	
	/** The btn tuite. */
	private JButton btnTuite;
	
	/** The lbl char count. */
	private JLabel lblCharCount;
	
	/** The scroll pane. */
	private JScrollPane scrollPane;
	
	/** The text field search people. */
	private JTextField textFieldSearchPeople;
	
	/** The text field search tuites. */
	private JTextField textFieldSearchTuites;
	
	/** The scroll pane following. */
	private JScrollPane scrollPaneFollowing;
	
	/** The scroll pane followers. */
	private JScrollPane scrollPaneFollowers;
	
	/** The panel followers list. */
	private JPanel panelFollowersList;
	
	/** The panel following list. */
	private JPanel panelFollowingList;
	
	/** The me. */
	private MainScreen me = this;

	/** The ctrl tuite. */
	private CtrlTuite ctrlTuite;
	
	/** The ctrl user. */
	private CtrlUser ctrlUser;
	
	/** The ctrl login. */
	private CtrlLogin ctrlLogin;
	
	/** The btn search tuites. */
	private JButton btnSearchTuites;
	
	/** The btn search people. */
	private JButton btnSearchPeople;
	
	/** The panel res people. */
	private JPanel panelResPeople;
	
	/** The panel res tuites. */
	private JPanel panelResTuites;
	
	/** The tl list. */
	private ArrayList<OtherTLScreen> tlList = new ArrayList<OtherTLScreen>();
	
	/** The lbl user photo. */
	private JLabel lblUserPhoto;
	private boolean isTwitter = false;
	private CtrlTwitter ctrlTwitter;
	private JPanel panelShowNotifications;
	private JTabbedPane tabbedPane;


	/**
	 * @wbp.parser.constructor
	 */
	/**
	 * Instantiates a new main screen.
	 *
	 * @param user the user
	 * @param ctrlLogin the ctrl login
	 * @wbp.parser.constructor
	 */
	public MainScreen(User user, CtrlLogin ctrlLogin) {

		setType(Type.POPUP);
		this.user = user;
		this.ctrlLogin = ctrlLogin;
		this.isTwitter = false;

		try {
			ctrlTuite = new CtrlTuite(this);
			ctrlUser = new CtrlUser(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		initialize();
	}
	
	public MainScreen(User user, CtrlTwitter ctrlTwitter) {

		setType(Type.POPUP);
		this.user = user;
		this.ctrlTwitter = ctrlTwitter;
		this.isTwitter = true;

		try {
//			ctrlTuite = new CtrlTuite(this);
//			ctrlUser = new CtrlUser(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		initialize();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#finalize()
	 */
	protected void finalize() throws Throwable {
		if (!isTwitter) {
			// do finalization here
			ctrlLogin.doLogoff(user);
		}
		super.finalize(); // not necessary if extending Object.

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Thread thread = new Thread(new RunUpdate(me));
		thread.start();
		
		setTitle("Tuite - " + user.getRealName());
		setBounds(100, 100, 611, 442);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (!isTwitter) {
					ctrlLogin.doLogoff(user);
				}
			}
		});

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panelTuites = new JPanel();
		tabbedPane.addTab("Timeline", null, panelTuites, null);
		tabbedPane.setMnemonicAt(0, 1);
		panelTuites.setLayout(new BorderLayout(0, 0));

		JPanel panelNewTuite = new JPanel();
		panelTuites.add(panelNewTuite, BorderLayout.NORTH);
		panelNewTuite.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new TitledBorder(null, "Tuite",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNewTuite.add(panelButtons);
		panelButtons.setLayout(new BorderLayout(0, 0));

		textAreaTuite = new JTextArea();
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setRows(3);
		textAreaTuite.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnTuite.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// btnTuite.doClick();
					textAreaTuite.setText("");
				}
			}
		});
		textAreaTuite.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				lblCharCount.setText(String.valueOf(140 - textAreaTuite
						.getText().length()));

				if (textAreaTuite.getText().isEmpty()) {
					btnTuite.setEnabled(false);
				} else {
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
				if (!isTwitter) {
					// Criando o TO
					Tuite tuite = new Tuite(0, textAreaTuite.getText()
							.toString(), new Timestamp(
							System.currentTimeMillis()), user);
					TuiteTO t = new TuiteTO(tuite);
					// Criando o controle
					ctrlTuite.truncate(t);
					// Chamar doTuite
					t = ctrlTuite.doTuite(t);
					user = t.getTuite().getMyUser();
				}else{
					newTuite(ctrlTwitter.twittar(textAreaTuite.getText()));
//					updateUser();
				}
				update();

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
		if (photo == null) {
			photo = new ImageIcon("noImg.jpg");
		}
		lblUserPhoto.setIcon(photo);

		scrollPane = new JScrollPane();
		panelTuites.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setAutoscrolls(true);

		panelTimeLine = new JPanel();
		scrollPane.setBorder(new TitledBorder(null, "Timeline for "
				+ user.getRealName(), TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		scrollPane.setViewportView(panelTimeLine);
		panelTimeLine.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelFollowing = new JPanel();
		tabbedPane.addTab("Following", null, panelFollowing, null);
		panelFollowing.setLayout(new BorderLayout(0, 0));

		scrollPaneFollowing = new JScrollPane();
		panelFollowing.add(scrollPaneFollowing);
		scrollPaneFollowing.setAutoscrolls(true);

		panelFollowingList = new JPanel();
		scrollPaneFollowing.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "You are following",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// panelFollowing.add(panelFollowingList);
		scrollPaneFollowing.setViewportView(panelFollowingList);
		panelFollowingList.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelFollowers = new JPanel();
		tabbedPane.addTab("Followers", null, panelFollowers, null);
		panelFollowers.setLayout(new BorderLayout(0, 0));

		scrollPaneFollowers = new JScrollPane();
		panelFollowers.add(scrollPaneFollowers);
		scrollPaneFollowers.setAutoscrolls(true);

		panelFollowersList = new JPanel();
		scrollPaneFollowers.setBorder(new TitledBorder(null, "Your followers",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// panelFollowers.add(panelFollowersList);
		scrollPaneFollowers.setViewportView(panelFollowersList);
		panelFollowersList.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelTuiteSearch = new JPanel();
		tabbedPane.addTab("Search Tuites", null, panelTuiteSearch, null);
		tabbedPane.setMnemonicAt(3, 2);
		panelTuiteSearch.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelTuiteSearch.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));

		textFieldSearchTuites = new JTextField();
		textFieldSearchTuites.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearchTuites.doClick();
				}
			}
		});
		textFieldSearchTuites.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if (textFieldSearchTuites.getText().isEmpty()) {
					btnSearchTuites.setEnabled(false);
				} else {
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
				if (!textFieldSearchTuites.getText().isEmpty()) {
					ArrayList<Tuite> res = new ArrayList<Tuite>();
					if (!isTwitter) {
						CtrlSearch ctrlSearch;
						try {

							// 1 is for tuites, 2 for ppl
							SearchTO searchTO = new SearchTO(getUser(),
									textFieldSearchTuites.getText(), 1);
							ctrlSearch = new CtrlSearch();
							searchTO = ctrlSearch.doSearch(searchTO);
							
							res = searchTO.getResultTuites();


						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}else{
						res = ctrlTwitter.searchTweets(textFieldSearchTuites.getText());
					}

					panelResTuites.removeAll();
					for (Tuite tuit : res) {
						panelResTuites.add(new TuitePanel(tuit, me));
					}
					
					textFieldSearchTuites.setText("");
					panelResTuites.repaint();
					repaint();
				}
			}
		});
		panel.add(btnSearchTuites, BorderLayout.EAST);

		JScrollPane scrollPaneSearchTuites = new JScrollPane();
		panelTuiteSearch.add(scrollPaneSearchTuites, BorderLayout.CENTER);
		scrollPaneSearchTuites.setAutoscrolls(true);

		panelResTuites = new JPanel();
		scrollPaneSearchTuites.setBorder(new TitledBorder(null, "Results",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// panelTuiteSearch.add(panelResTuites, BorderLayout.SOUTH);
		scrollPaneSearchTuites.setViewportView(panelResTuites);
		panelResTuites.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panelPeopleSearch = new JPanel();
		tabbedPane.addTab("Search People", null, panelPeopleSearch, null);
		tabbedPane.setMnemonicAt(4, 3);
		panelPeopleSearch.setLayout(new BorderLayout(0, 0));

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Search",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPeopleSearch.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		textFieldSearchPeople = new JTextField();
		textFieldSearchPeople.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearchPeople.doClick();
				}
			}
		});
		textFieldSearchPeople.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				if (textFieldSearchPeople.getText().isEmpty()) {
					btnSearchPeople.setEnabled(false);
				} else {
					btnSearchPeople.setEnabled(true);
				}
			}
		});
		panel_3.add(textFieldSearchPeople, BorderLayout.CENTER);
		textFieldSearchPeople.setColumns(10);

		btnSearchPeople = new JButton("Search");
		btnSearchPeople.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textFieldSearchPeople.getText().isEmpty()) {
					ArrayList<User> res = new ArrayList<User>();
					if (!isTwitter) {
						CtrlSearch ctrlSearch;
						try {

							// 1 is for tuites, 2 for ppl
							SearchTO searchTO = new SearchTO(getUser(),
									textFieldSearchPeople.getText(), 2);
							ctrlSearch = new CtrlSearch();
							searchTO = ctrlSearch.doSearch(searchTO);
							res = searchTO.getResultUsers();

						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}else{
						res = ctrlTwitter.searchPeople(textFieldSearchPeople.getText());
					}

					panelResPeople.removeAll();
					for (User usr : res) {
						panelResPeople.add(new TuitePanel(usr, me));
					}
					repaint();
					textFieldSearchPeople.setText("");

				}
			}
		});
		btnSearchPeople.setEnabled(false);
		panel_3.add(btnSearchPeople, BorderLayout.EAST);

		JScrollPane scrollPaneSearchPeople = new JScrollPane();
		scrollPaneSearchPeople.setAutoscrolls(true);
		panelPeopleSearch.add(scrollPaneSearchPeople, BorderLayout.CENTER);

		panelResPeople = new JPanel();
		scrollPaneSearchPeople.setBorder(new TitledBorder(null, "Results",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		// panelPeopleSearch.add(panelResPeople, BorderLayout.WEST);
		scrollPaneSearchPeople.setViewportView(panelResPeople);
		panelResPeople.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelNotifications = new JPanel();
		tabbedPane.addTab("", null, panelNotifications, null);
		panelNotifications.setLayout(new BorderLayout(0, 0));
		
		
		JScrollPane scrollPaneNotifications = new JScrollPane();
		scrollPaneNotifications.setBorder(new TitledBorder(null, "Your notifications", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPaneNotifications.setAutoscrolls(true);
		panelNotifications.add(scrollPaneNotifications, BorderLayout.CENTER);
		
		panelShowNotifications = new JPanel();
		scrollPaneNotifications.setViewportView(panelShowNotifications);
		panelShowNotifications.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane.setTitleAt(5, "Notifications (" + panelShowNotifications.getComponentCount() + ")");

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchive = new JMenu("File");
		mnArchive.setMnemonic('f');
		menuBar.add(mnArchive);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!isTwitter) {
					ctrlLogin.doLogoff(user);
				}
				System.exit(0);
			}
		});

		JMenuItem mntmLogout = new JMenuItem("Logout");
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (!isTwitter) {
					ctrlLogin.doLogoff(user);
				}
				LoginScreen loginScreen = new LoginScreen();
				dispose();
				loginScreen.setVisible(true);

			}
		});
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateUser();
			}
		});
		mnArchive.add(mntmRefresh);
		mntmRefresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		mntmLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_MASK));
		mnArchive.add(mntmLogout);
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				InputEvent.CTRL_MASK));
		mnArchive.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setMnemonic('e');
		menuBar.add(mnEdit);

		JMenuItem mntmEdit = new JMenuItem("Profile");
		mntmEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditProfileScreen editProfileScreen = new EditProfileScreen(me);
				editProfileScreen.setVisible(true);
				me.setEnabled(false);
			}
		});
		mntmEdit.setMnemonic('r');
		mnEdit.add(mntmEdit);

		update();

	}

	/**
	 * Update.
	 */
	public void update() {
		setTitle("Tuite - " + user.getRealName());
		scrollPane.setBorder(new TitledBorder(null, "Timeline for "
				+ user.getRealName(), TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		lblUserPhoto.setIcon(user.getPhoto());
		panelTimeLine.removeAll();
		panelFollowingList.removeAll();
		panelFollowersList.removeAll();

		if (isTwitter) {
//			for (Tuite tu : user.getTuites()) {
//				TuitePanel t = new TuitePanel(tu, this);
//				panelTimeLine.add(t, panelTimeLine.getComponents().length);
//			}
			for (int i = user.getTuites().size() - 1; i >= 0; i--) {
				Tuite tu = user.getTuites().get(i);
				TuitePanel t = new TuitePanel(tu, this);
				panelTimeLine.add(t, 0);
			}
		}else{
			for (Tuite tu : user.getTuites()) {
				TuitePanel t = new TuitePanel(tu, this);
				panelTimeLine.add(t, 0);
			}
		}
		for (User u : user.getFollowing()) {
			TuitePanel tp = new TuitePanel(u, this);
			panelFollowingList.add(tp);
		}
		for (User u : user.getFollowers()) {
			TuitePanel tp = new TuitePanel(u, this);
			panelFollowersList.add(tp);
		}
		for (Component tp : panelResTuites.getComponents()) {
			if (tp instanceof TuitePanel) {
				((TuitePanel) tp).update();
			}
		}
		for (Component tp : panelResPeople.getComponents()) {
			if (tp instanceof TuitePanel) {
				((TuitePanel) tp).update();
			}
		}
		for (OtherTLScreen otl : tlList) {
			otl.update();
		}

		if (!isTwitter) {
			tabbedPane.setTitleAt(
					5,
					"Notifications ("
							+ panelShowNotifications.getComponentCount() + ")");
		}else{
			panelShowNotifications.removeAll();
			ArrayList<User> alN = ctrlTwitter.getNotifications();
			
			for (User u : alN) {
				setFollowNotification(u);
			}
		}
		scrollPane.getVerticalScrollBar().setValue(0);
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
		verticalScrollBar.setValue(verticalScrollBar.getMinimum());
		horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());

		repaint();
	}

	/**
	 * Update user.
	 */
	public void updateUser() {
		User userTemp;
		if (!isTwitter()) {
			userTemp = ctrlUser.refreshUser(user);
		}else{
			userTemp = ctrlTwitter.getFullUser();
		}
		setUser(userTemp);
		update();
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the ctrl user.
	 *
	 * @return the ctrl user
	 */
	public CtrlUser getCtrlUser() {
		return ctrlUser;
	}

	/**
	 * Adds the tl.
	 *
	 * @param otherTLScreen the other tl screen
	 * @return true, if successful
	 */
	public boolean addTL(OtherTLScreen otherTLScreen) {
		tlList.add(otherTLScreen);
		return true;
	}

	/**
	 * Removes the tl.
	 *
	 * @param otherTLScreen the other tl screen
	 * @return true, if successful
	 */
	public boolean removeTL(OtherTLScreen otherTLScreen) {
		tlList.remove(otherTLScreen);
		return true;
	}

	/**
	 * Notify user for follow.
	 *
	 * @param followTO: the follow to
	 */
	public void notifyUserForFollow(FollowTO followTO) {
		// System.out.println("User: " + user.getLoginName() +
		// " want to follow you, Accept?");

//		int x = JOptionPane.showConfirmDialog(this, followTO.getFollower()
//				.getRealName() + " wants to follow you. Do you accept?",
//				"Aplicação", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
//		if (x == JOptionPane.YES_OPTION) {
//			// dispose();
//			followTO.setNotifyFollower(false);
//			ctrlUser.updateNotifyFromFollow(followTO);
//		} else if (x == JOptionPane.NO_OPTION) {
//			// dispose();
//			followTO.setNotifyFollower(true);
//			ctrlUser.doUnFollow(followTO);
//		}
		
		setFollowNotification(followTO.getFollower());
	}

	public boolean isTwitter() {
		return isTwitter;
	}

	public void setTwitter(boolean isTwitter) {
		this.isTwitter = isTwitter;
	}

	public CtrlTwitter getCtrlTwitter() {
		return ctrlTwitter;
	}

	public void setCtrlTwitter(CtrlTwitter ctrlTwitter) {
		this.ctrlTwitter = ctrlTwitter;
	}
	
	public void newTuite(Tuite tuite){
		TuitePanel t = new TuitePanel(tuite, this);
		panelTimeLine.add(t, 0);
		user.addMyTuite(tuite, 0);
		user.addTuite(tuite, 0);
		repaint();
	}
	
	public void setFollowNotification(User user) {
		panelShowNotifications.add(new NotifyPanel(user, this));
		tabbedPane.setTitleAt(5, "Notifications (" + panelShowNotifications.getComponentCount() + ")");
		repaint();
	}

}
