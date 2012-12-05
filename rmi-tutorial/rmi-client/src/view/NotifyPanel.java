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
import model.NotifyTO;
import model.User;

public class NotifyPanel extends JPanel {

	private User otherUser;
	private MainScreen mainScreen;
	private NotifyTO notifyTO;
	private NotifyPanel me;
	protected OtherTLScreen otherTLScreen;

	/**
	 * Create the panel.
	 */
	public NotifyPanel(NotifyTO notifyTO, MainScreen ms) {
		this.otherUser = (User) notifyTO.getObjectBaseSource();
		this.mainScreen = ms;
		this.notifyTO = notifyTO;
		me = this;
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
				if (!mainScreen.isTwitter()) {
					notifyTO.setOptionYesNo(true);
					//TODO enviar de volta pro servidor...
				}else{
				}
				
				mainScreen.removeFollowNotification(me);
			}
		});
		btnPanel.add(btnAllow, BorderLayout.NORTH);
		
		JButton btnDeny = new JButton("Deny");
		btnDeny.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!mainScreen.isTwitter()) {
					notifyTO.setOptionYesNo(false);
					//TODO enviar de volta pro servidor...
				}else{
				}
				mainScreen.removeFollowNotification(me);
			}
		});
		btnPanel.add(btnDeny, BorderLayout.SOUTH);

	}

}
