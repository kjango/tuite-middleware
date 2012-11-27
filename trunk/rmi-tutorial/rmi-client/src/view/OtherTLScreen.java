package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Tuite;
import model.User;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class OtherTLScreen extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainScreen mainScreen;
	private User timeLineOwner;
	private JPanel panelTimeLine;
	private JScrollPane scrollPane;
	private JPanel panelFollowing;
	private JPanel panelFollowers;
	private JPanel panelName;
	private JScrollPane scrollPaneFollowing;
	private JPanel panelFollowingList;
	private JScrollPane scrollPaneFollowers;
	private JPanel panelFollowersList;
	private JLabel lblRealName;
	private JLabel lblPhoto;


	/**
	 * Create the application.
	 */
	public OtherTLScreen(MainScreen mainScreen, User timeLineOwner) {

		this.mainScreen = mainScreen;
		this.timeLineOwner = timeLineOwner;

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Tuite - " + timeLineOwner.getRealName());
		setBounds(100, 100, 611, 442);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelTuites = new JPanel();
		tabbedPane.addTab("Timeline", null, panelTuites, null);
		tabbedPane.setMnemonicAt(0, 1);
		panelTuites.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelTuites.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setAutoscrolls(true);
		
		panelTimeLine = new JPanel();
		panelTimeLine.setBorder(new TitledBorder(null, "Timeline for " + timeLineOwner.getRealName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(panelTimeLine);
		panelTimeLine.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelName = new JPanel();
		scrollPane.setColumnHeaderView(panelName);
		panelName.setLayout(new BorderLayout(0, 0));
		
		lblPhoto = new JLabel("");
		panelName.add(lblPhoto, BorderLayout.WEST);
		
		lblRealName = new JLabel("New label");
		panelName.add(lblRealName, BorderLayout.CENTER);
		
		ImageIcon photo = new ImageIcon();
		photo = timeLineOwner.getPhoto();
		if (photo == null){
			photo = new ImageIcon("noImg.jpg");
		}
		lblPhoto.setIcon(photo);
		
		lblRealName.setText(timeLineOwner.getRealName());
		
		panelFollowing = new JPanel();
		tabbedPane.addTab("Following", null, panelFollowing, null);
		panelFollowing.setLayout(new BorderLayout(0, 0));
		
		scrollPaneFollowing = new JScrollPane();
		panelFollowing.add(scrollPaneFollowing);
		scrollPaneFollowing.setAutoscrolls(true);
		
		panelFollowingList = new JPanel();
		panelFollowingList.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), timeLineOwner.getLoginName() + " is following", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panelFollowing.add(panelFollowingList);
		scrollPaneFollowing.setViewportView(panelFollowingList);
		panelFollowingList.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelFollowers = new JPanel();
		tabbedPane.addTab("Followers", null, panelFollowers, null);
		panelFollowers.setLayout(new BorderLayout(0, 0));
		
		scrollPaneFollowers = new JScrollPane();
		panelFollowers.add(scrollPaneFollowers);
		scrollPaneFollowers.setAutoscrolls(true);
		
		panelFollowersList = new JPanel();
		panelFollowersList.setBorder(new TitledBorder(null, timeLineOwner.getLoginName() + " followers", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//		panelFollowers.add(panelFollowersList);
		scrollPaneFollowers.setViewportView(panelFollowersList);
		panelFollowersList.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		update();
		
	}
	
	public void update(){
		setTitle(timeLineOwner.getRealName());
		panelTimeLine.setBorder(new TitledBorder(null, "Timeline for " + timeLineOwner.getRealName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panelTimeLine.removeAll();
		
		for (Tuite tu : timeLineOwner.getTuites()) {
			TuitePanel t = new TuitePanel(tu, mainScreen);
			panelTimeLine.add(t, 0);
		}
		    
	    for (User u : timeLineOwner.getFollowing()) {
			TuitePanel tp = new TuitePanel(u, mainScreen);
			panelFollowingList.add(tp);
		}
	    for (User u : timeLineOwner.getFollowers()) {
			TuitePanel tp = new TuitePanel(u, mainScreen);
			panelFollowersList.add(tp);
		}

		scrollPane.getVerticalScrollBar().setValue(0);
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
	    JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
	    verticalScrollBar.setValue(verticalScrollBar.getMinimum());
	    horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
	    
	    repaint();
	}

}
