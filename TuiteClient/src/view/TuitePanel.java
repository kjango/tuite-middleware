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

public class TuitePanel extends JPanel {
	
	private Tuite tuite;

	/**
	 * Create the panel.
	 */
	public TuitePanel() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), tuite.getMyUser().getRealName() + " @ " + tuite.getCreatedAt(), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JButton btnFollowUnfollow = new JButton("Follow / Unfollow");
		add(btnFollowUnfollow, BorderLayout.EAST);
		
		JLabel labelTuite = new JLabel("New label");
		add(labelTuite, BorderLayout.CENTER);
		
		JLabel lblImage = new JLabel("");
		add(lblImage, BorderLayout.WEST);
		
		lblImage.setIcon(new ImageIcon(tuite.getMyUser().getPhoto()));


	}

	public TuitePanel(Tuite tuite) {
		super();
		this.tuite = tuite;
	}

}
