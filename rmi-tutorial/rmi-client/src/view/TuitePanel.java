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

public class TuitePanel extends JPanel {
	
	private Tuite tuite;
	private User user;

	/**
	 * Create the panel.
	 */


	public TuitePanel(User user, Tuite tuite) {
		super();
		this.tuite = tuite;
		this.user = user;
		setSize(new Dimension(450, 80));
		setPreferredSize(new Dimension(450, 80));
		setMaximumSize(new Dimension(450, 80));
		initilize();
	}
	
	private void initilize(){
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), tuite.getMyUser().getRealName() + " @ " + tuite.getCreatedAt(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		CtrlClient cc = new CtrlClient();
		String btnText = "Follow";
		if(cc.follows(user, tuite.getMyUser())){
			btnText = "Unfollow";
		}
		JButton btnFollowUnfollow = new JButton(btnText);
		btnFollowUnfollow.setPreferredSize(new Dimension(90, 13));
		add(btnFollowUnfollow, BorderLayout.EAST);
		
		JLabel labelTuite = new JLabel(tuite.getText());
		add(labelTuite, BorderLayout.CENTER);
		
		JLabel lblImage = new JLabel("");
		add(lblImage, BorderLayout.WEST);
		
		BufferedImage photo = null;
		//photo = tuite.getMyUser().getPhoto();
		if (photo == null){
			try {
				photo = ImageIO.read(new File("noImg.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		lblImage.setIcon(new ImageIcon(tuite.getMyUser().getPhoto()));
		lblImage.setIcon(new ImageIcon(photo));
	}

}
