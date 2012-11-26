package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.FollowTO;
import model.Tuite;
import model.User;
import control.CtrlUser;

public class TuitePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tuite tuite;
	private User myUser;
	private User otherUser;
	private JButton btnFollowUnfollow;
	private MainScreen mainScreen;
	private CtrlUser ctrlUser;

	/**
	 * Create the panel.
	 * 
	 * @wbp.parser.constructor
	 */

	public TuitePanel(User myUser, Tuite tuite, MainScreen mainScreen) {
		super();

		try {
			ctrlUser = new CtrlUser();
		} catch (Exception ex) {

		}

		this.myUser = myUser;
		this.tuite = tuite;
		this.mainScreen = mainScreen;
		initilizeTuite();
	}

	public TuitePanel(User myUser, User otherUser, MainScreen mainScreen) {
		super();
		this.myUser = myUser;
		this.otherUser = otherUser;
		this.mainScreen = mainScreen;

		try {
			ctrlUser = new CtrlUser();
		} catch (Exception ex) {

		}
		initilizeUser();
	}

	private void initilizeTuite() {
		setSize(new Dimension(450, 80));
		setPreferredSize(new Dimension(450, 80));
		setMaximumSize(new Dimension(450, 80));
		setBorder(new TitledBorder(
				UIManager.getBorder("TitledBorder.border"),
				tuite.getMyUser().getLoginName() + " @ " + tuite.getCreatedAt(),
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JLabel lblImage = new JLabel("");
		add(lblImage, BorderLayout.WEST);

		ImageIcon photo = new ImageIcon();
		photo = tuite.getMyUser().getPhoto();
		if (photo == null) {
			photo = new ImageIcon("noImg.jpg");
		}
		lblImage.setIcon(photo);

		JTextArea textAreaTuite = new JTextArea(tuite.getText());
		textAreaTuite.setEditable(false);
		textAreaTuite.setWrapStyleWord(true);
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setBackground(new Color(240, 240, 240));
		add(textAreaTuite, BorderLayout.CENTER);

		String btnText = "Follow";
		if (ctrlUser.doesFollow(myUser, tuite.getMyUser())) {
			btnText = "Unfollow";
		}

		if (myUser.getId() != tuite.getMyUser().getId()) {
			btnFollowUnfollow = new JButton(btnText);
			btnFollowUnfollow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// creating the TO
					FollowTO followTO = new FollowTO(myUser, tuite.getMyUser());

					if (btnFollowUnfollow.getText().equals("Follow")) {
						// TODO seguir
						followTO = ctrlUser.doFollow(followTO);
					} else {
						// TODO desseguir xD
						followTO = ctrlUser.doUnFollow(followTO);
					}

					mainScreen.setUser(followTO.getFollower());
					mainScreen.update();
					mainScreen.update();
				}
			});
			btnFollowUnfollow.setPreferredSize(new Dimension(90, 13));
			add(btnFollowUnfollow, BorderLayout.EAST);
		}

	}

	private void initilizeUser() {
		setSize(new Dimension(450, 80));
		setPreferredSize(new Dimension(450, 80));
		setMaximumSize(new Dimension(450, 80));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				otherUser.getLoginName(), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));



		JLabel lblImage = new JLabel("");
		add(lblImage, BorderLayout.WEST);

		ImageIcon photo = new ImageIcon();
		photo = otherUser.getPhoto();
		if (photo == null) {
			photo = new ImageIcon("noImg.jpg");
		}
		lblImage.setIcon(photo);

		JTextArea textAreaTuite = new JTextArea(otherUser.getRealName());
		textAreaTuite.setEditable(false);
		textAreaTuite.setWrapStyleWord(true);
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setBackground(new Color(240, 240, 240));
		add(textAreaTuite, BorderLayout.CENTER);
		


		if (myUser.getId() != otherUser.getId()) {
			
			String btnText = "Follow";
			if (ctrlUser.doesFollow(myUser, otherUser)) {
				btnText = "Unfollow";
			}
						
			btnFollowUnfollow = new JButton(btnText);
			btnFollowUnfollow.setPreferredSize(new Dimension(90, 13));
			add(btnFollowUnfollow, BorderLayout.EAST);
			btnFollowUnfollow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// creating the TO
					FollowTO followTO = new FollowTO(myUser, otherUser);

					if (btnFollowUnfollow.getText().equals("Follow")) {
						// TODO seguir
						followTO = ctrlUser.doFollow(followTO);
					} else {
						// TODO desseguir xD
						followTO = ctrlUser.doUnFollow(followTO);
					}

					mainScreen.setUser(followTO.getFollower());
					mainScreen.update();
				}
			});

		}

	}

}
