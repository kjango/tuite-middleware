package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import model.Tuite;
import model.User;
import java.awt.Dimension;
import java.awt.Rectangle;

public class TuitePanel extends JPanel {
	
	private Tuite tuite;

	/**
	 * Create the panel.
	 */


	public TuitePanel(Tuite tuite) {
		super();
		setSize(new Dimension(450, 80));
		setPreferredSize(new Dimension(450, 80));
		setMaximumSize(new Dimension(450, 80));
		this.tuite = tuite;
		initilize();
	}
	
	private void initilize(){
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), tuite.getMyUser().getRealName() + " @ " + tuite.getCreatedAt(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JButton btnFollowUnfollow = new JButton("Follow / Unfollow");
		btnFollowUnfollow.setPreferredSize(new Dimension(80, 13));
		add(btnFollowUnfollow, BorderLayout.EAST);
		
		JLabel labelTuite = new JLabel(tuite.getText());
		add(labelTuite, BorderLayout.CENTER);
		
		JLabel lblImage = new JLabel("");
		add(lblImage, BorderLayout.WEST);
		
		lblImage.setIcon(new ImageIcon(tuite.getMyUser().getPhoto()));

		System.out.println("lala");
	}

}
