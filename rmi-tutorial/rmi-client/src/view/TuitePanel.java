package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Tuite;
import model.User;
import control.CtrlClient;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;

public class TuitePanel extends JPanel {
	
	private Tuite tuite;
	private User myUser;
	private User User;
	private JButton btnFollowUnfollow;

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */


	public TuitePanel(User myUser, Tuite tuite) {
		super();
		this.tuite = tuite;
		this.myUser = myUser;
		initilizeTuite();
	}
	
	public TuitePanel(User myUser, User user) {
		super();
		this.myUser = myUser;
		this.User = user;
		initilizeUser();
	}
	
	private void initilizeTuite(){
		setSize(new Dimension(450, 80));
		setPreferredSize(new Dimension(450, 80));
		setMaximumSize(new Dimension(450, 80));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), tuite.getMyUser().getLoginName() + " @ " + tuite.getCreatedAt(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		CtrlClient cc = new CtrlClient();
		String btnText = "Follow";
		if(cc.follows(myUser, tuite.getMyUser())){
			btnText = "Unfollow";
		}
		btnFollowUnfollow = new JButton(btnText);
		btnFollowUnfollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnFollowUnfollow.getText().equals("Follow")){
					//TODO seguir
				}else{
					//TODO desseguir xD
				}
				repaint();
			}
		});
		btnFollowUnfollow.setPreferredSize(new Dimension(90, 13));
		add(btnFollowUnfollow, BorderLayout.EAST);
		
		JLabel lblImage = new JLabel("");
		add(lblImage, BorderLayout.WEST);
		
		ImageIcon photo = new ImageIcon();
		photo = tuite.getMyUser().getPhoto();
		if (photo == null){
			photo = new ImageIcon("noImg.jpg");
		}
		lblImage.setIcon(photo);
		
		JTextArea textAreaTuite = new JTextArea(tuite.getText());
		textAreaTuite.setEditable(false);
		textAreaTuite.setWrapStyleWord(true);
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setBackground(new Color(240, 240, 240));
		add(textAreaTuite, BorderLayout.CENTER);

	}
	
	private void initilizeUser(){
		setSize(new Dimension(450, 80));
		setPreferredSize(new Dimension(450, 80));
		setMaximumSize(new Dimension(450, 80));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), tuite.getMyUser().getLoginName(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		CtrlClient cc = new CtrlClient();
		String btnText = "Follow";
		if(cc.follows(myUser, tuite.getMyUser())){
			btnText = "Unfollow";
		}
		btnFollowUnfollow = new JButton(btnText);
		btnFollowUnfollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnFollowUnfollow.getText().equals("Follow")){
					//TODO seguir
				}else{
					//TODO desseguir xD
				}
				repaint();
			}
		});
		btnFollowUnfollow.setPreferredSize(new Dimension(90, 13));
		add(btnFollowUnfollow, BorderLayout.EAST);
		
		JLabel lblImage = new JLabel("");
		add(lblImage, BorderLayout.WEST);
		
		ImageIcon photo = new ImageIcon();
		photo = tuite.getMyUser().getPhoto();
		if (photo == null){
			photo = new ImageIcon("noImg.jpg");
		}
		lblImage.setIcon(photo);
		
		JTextArea textAreaTuite = new JTextArea(User.getRealName());
		textAreaTuite.setEditable(false);
		textAreaTuite.setWrapStyleWord(true);
		textAreaTuite.setLineWrap(true);
		textAreaTuite.setBackground(new Color(240, 240, 240));
		add(textAreaTuite, BorderLayout.CENTER);

	}

}
