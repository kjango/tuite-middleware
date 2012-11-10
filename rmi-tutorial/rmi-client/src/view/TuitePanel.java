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
import base.Compute;
import control.CtrlUser;

public class TuitePanel extends JPanel {
	
	private Tuite tuite;
	private User myUser;
	private User User;
	private JButton btnFollowUnfollow;
	private Compute compute;

	/**
	 * Create the panel.
	 * @wbp.parser.constructor
	 */


	public TuitePanel(User myUser, Tuite tuite, Compute compute) {
		super();
		this.tuite = tuite;
		this.myUser = myUser;
		this.compute = compute;
		initilizeTuite();
	}
	
	public TuitePanel(User myUser, User user, Compute compute) {
		super();
		this.myUser = myUser;
		this.User = user;
		this.compute = compute;
		initilizeUser();
	}
	
	private void initilizeTuite(){
		setSize(new Dimension(450, 80));
		setPreferredSize(new Dimension(450, 80));
		setMaximumSize(new Dimension(450, 80));
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), tuite.getMyUser().getLoginName() + " @ " + tuite.getCreatedAt(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		CtrlUser ctrlUser = new CtrlUser();
		String btnText = "Follow";
		if(ctrlUser.doesFollow(myUser, tuite.getMyUser())){
			btnText = "Unfollow";
		}
		
		if(myUser.getId() != tuite.getMyUser().getId()){
			btnFollowUnfollow = new JButton(btnText);
			btnFollowUnfollow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					//creating the TO
					FollowTO followTO = new FollowTO(myUser, tuite.getMyUser());
					CtrlUser ctrlUser = new CtrlUser();
					if (btnFollowUnfollow.getText().equals("Follow")){
						//TODO seguir
						ctrlUser.doFollow(followTO, compute);
					}else{
						//TODO desseguir xD
						ctrlUser.doUnFollow(followTO, compute);
					}
					repaint();
				}
			});
			btnFollowUnfollow.setPreferredSize(new Dimension(90, 13));
			add(btnFollowUnfollow, BorderLayout.EAST);	
		}
		
		
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
		
		CtrlUser cc = new CtrlUser();
		String btnText = "Follow";
		if(cc.doesFollow(myUser, tuite.getMyUser())){
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
