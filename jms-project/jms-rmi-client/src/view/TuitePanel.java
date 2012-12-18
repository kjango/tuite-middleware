package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

public class TuitePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tuite tuite;
	private User otherUser;
	private JButton btnFollowUnfollow;
	private MainScreen mainScreen;
	private OtherTLScreen otherTLScreen = null;

	// private CtrlUser ctrlUser;

	/**
	 * Create the panel.
	 * 
	 * @wbp.parser.constructor
	 */

	public TuitePanel(Tuite tuite, MainScreen mainScreen) {
		super();

		// try {
		// ctrlUser = new CtrlUser();
		// } catch (Exception ex) {
		//
		// }

		// this.myUser = mainScreen.getUser();
		this.tuite = tuite;
		this.mainScreen = mainScreen;
		this.otherUser = tuite.getMyUser();
		btnFollowUnfollow = new JButton("Follow");
		initilizeTuite();
	}

	public TuitePanel(User otherUser, MainScreen mainScreen) {
		super();
		// this.myUser = mainScreen.getUser();
		this.otherUser = otherUser;
		this.mainScreen = mainScreen;

		// try {
		// ctrlUser = new CtrlUser();
		// } catch (Exception ex) {
		//
		// }
		btnFollowUnfollow = new JButton("Follow");
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
		textAreaTuite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() == 2){
					if (!mainScreen.isTwitter()) {
						User usr = mainScreen.getCtrlUser().refreshUser(
								otherUser);
						otherTLScreen = new OtherTLScreen(mainScreen, usr);
						otherTLScreen.setVisible(true);
					}else{
						User usr = mainScreen.getCtrlTwitter().getUserTimeline(
								otherUser);
						usr = mainScreen.getCtrlTwitter().getFollowers(usr);
						usr = mainScreen.getCtrlTwitter().getFollowing(usr);
						otherTLScreen = new OtherTLScreen(mainScreen, usr);
						otherTLScreen.setVisible(true);
					}
				}
			}
		});
		textAreaTuite.setEditable(false);
		textAreaTuite.setWrapStyleWord(true);
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setBackground(new Color(240, 240, 240));
		add(textAreaTuite, BorderLayout.CENTER);

		update();

		if (mainScreen.getUser().getId() != tuite.getMyUser().getId()) {
			btnFollowUnfollow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					User updated = null;
					
					FollowTO followTO = new FollowTO(mainScreen.getUser(),
							otherUser);

					if (btnFollowUnfollow.getText().equals("Follow")) {
						if (!mainScreen.isTwitter()) {
							followTO = mainScreen.getCtrlUser().doFollow(
									followTO);
							updated = followTO.getFollower();
						}else{
							updated = mainScreen.getCtrlTwitter().follow(mainScreen.getUser(), otherUser);
						}
					} else {
						if (!mainScreen.isTwitter()) {
							followTO = mainScreen.getCtrlUser().doUnFollow(
									followTO);
							updated = followTO.getFollower();
						}else{
							updated = mainScreen.getCtrlTwitter().unFollow(mainScreen.getUser(), otherUser);
						}
					}

					mainScreen.setUser(updated);
					if (!mainScreen.isTwitter()) {
						mainScreen.updateUser();
					}else{
						mainScreen.update();
					}
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
		textAreaTuite.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if ( e.getClickCount() == 2){
					if (!mainScreen.isTwitter()) {
						User usr = mainScreen.getCtrlUser().refreshUser(
								otherUser);
						otherTLScreen = new OtherTLScreen(mainScreen, usr);
						otherTLScreen.setVisible(true);
					}else{
						User usr = mainScreen.getCtrlTwitter().getUserTimeline(
								otherUser);
						usr = mainScreen.getCtrlTwitter().getFollowers(usr);
						usr = mainScreen.getCtrlTwitter().getFollowing(usr);
						otherTLScreen = new OtherTLScreen(mainScreen, usr);
						otherTLScreen.setVisible(true);
					}
				}
			}
		});
		textAreaTuite.setEditable(false);
		textAreaTuite.setWrapStyleWord(true);
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setBackground(new Color(240, 240, 240));
		add(textAreaTuite, BorderLayout.CENTER);

		if (mainScreen.getUser().getId() != otherUser.getId()) {

			update();

			// btnFollowUnfollow = new JButton(btnText);
			btnFollowUnfollow.setPreferredSize(new Dimension(90, 13));
			add(btnFollowUnfollow, BorderLayout.EAST);
			
			btnFollowUnfollow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					User updated = null;
					
					FollowTO followTO = new FollowTO(mainScreen.getUser(),
							otherUser);

					if (btnFollowUnfollow.getText().equals("Follow")) {
						if (!mainScreen.isTwitter()) {
							followTO = mainScreen.getCtrlUser().doFollow(
									followTO);
							updated = followTO.getFollower();
						}else{
							updated = mainScreen.getCtrlTwitter().follow(mainScreen.getUser(), otherUser);
						}
					} else {
						if (!mainScreen.isTwitter()) {
							followTO = mainScreen.getCtrlUser().doUnFollow(
									followTO);
							updated = followTO.getFollower();
						}else{
							updated = mainScreen.getCtrlTwitter().unFollow(mainScreen.getUser(), otherUser);
						}
					}

					mainScreen.setUser(updated);
					if (!mainScreen.isTwitter()) {
						mainScreen.updateUser();
					}else{
						mainScreen.update();
					}
				}
			});

		}

	}

	public void update() {
		if (!mainScreen.isTwitter()) {
			String btnText = "Follow";
			if (mainScreen.getCtrlUser().doesFollow(mainScreen.getUser(),
					otherUser)) {
				btnText = "Unfollow";
			}
			btnFollowUnfollow.setText(btnText);
			repaint();
			if (otherTLScreen != null) {
				otherTLScreen.update();
			}
		}else{
			String btnText = "Follow";
			if (mainScreen.getCtrlTwitter().doesFollow(mainScreen.getUser(),
					otherUser)) {
				btnText = "Unfollow";
			}
			btnFollowUnfollow.setText(btnText);
			repaint();
			if (otherTLScreen != null) {
				otherTLScreen.update();
			}
		}
	}

}
