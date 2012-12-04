package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.FollowTO;
import model.User;
import javax.swing.JButton;

public class NotifyPanel extends JPanel {

	private User otherUser;
	private MainScreen mainScreen;
	protected OtherTLScreen otherTLScreen;

	/**
	 * Create the panel.
	 */
	public NotifyPanel(User otherUser, MainScreen ms) {
		this.otherUser = otherUser;
		this.mainScreen = ms;
		initialize();
	}
	
	public void initialize(){

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

		JTextArea textAreaTuite = new JTextArea(otherUser.getRealName() + " wants to follow you.");
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
		
		JPanel btnPanel = new JPanel();
		add(btnPanel, BorderLayout.EAST);
		btnPanel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAllow = new JButton("Allow");
		btnAllow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				int x = JOptionPane.showConfirmDialog(this, followTO.getFollower()
//				.getRealName() + " wants to follow you. Do you accept?",
//				"Aplica��o", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
//		if (x == JOptionPane.YES_OPTION) {
//			// dispose();
//			followTO.setNotifyFollower(false);
//			ctrlUser.updateNotifyFromFollow(followTO);
//		} else if (x == JOptionPane.NO_OPTION) {
//			// dispose();
//			followTO.setNotifyFollower(true);
//			ctrlUser.doUnFollow(followTO);
//		}
				FollowTO followTO = new FollowTO(mainScreen.getUser(), otherUser);
				followTO.setNotifyFollower(false);
				mainScreen.getCtrlUser().updateNotifyFromFollow(followTO);
			}
		});
		btnPanel.add(btnAllow, BorderLayout.NORTH);
		
		JButton btnDeny = new JButton("Deny");
		btnDeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FollowTO followTO = new FollowTO(mainScreen.getUser(), otherUser);
				followTO.setNotifyFollower(true);
				mainScreen.getCtrlUser().doUnFollow(followTO);
			}
		});
		btnPanel.add(btnDeny, BorderLayout.SOUTH);

	}

}
