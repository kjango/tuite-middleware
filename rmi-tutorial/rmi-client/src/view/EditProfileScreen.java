package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

import model.User;

import base.Compute;

public class EditProfileScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRealName;
	private JTextField textFieldUserName;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JCheckBox chckbxProtecTuite;
	
	private Compute compute;
	private User user;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfileScreen frame = new EditProfileScreen(new User(0,"@teste","rafael",null,true,null,null,null,null),null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public EditProfileScreen(User user, Compute compute){
		this.compute = compute;
		this.user = user;
		initialize();
		
		if(user.isProtectedTuite())
			chckbxProtecTuite.setSelected(true);
		else
			chckbxProtecTuite.setSelected(false);
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 312, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Edit Profile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		final JRadioButton btnRealName = new JRadioButton("Real Name:");
		btnRealName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnRealName.isSelected())
					textFieldRealName.setEditable(true);
				else{
					textFieldRealName.setText(null);
					textFieldRealName.setEditable(false);
				}
					
					
			}
		});
		btnRealName.setBounds(8, 36, 127, 25);
		panel.add(btnRealName);
		
		final JRadioButton btnUserName = new JRadioButton("User Name:");
		btnUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnUserName.isSelected())
					textFieldUserName.setEditable(true);
				else{
					textFieldUserName.setText(null);
					textFieldUserName.setEditable(false);
				}
			}
		});
		btnUserName.setBounds(8, 81, 127, 25);
		panel.add(btnUserName);
		
		final JRadioButton btnPassword = new JRadioButton("Password:");
		btnPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnPassword.isSelected())
					textFieldPassword.setEditable(true);
				else{
					textFieldPassword.setText(null);
					textFieldPassword.setEditable(false);
				}
			}
		});
		btnPassword.setBounds(8, 129, 127, 25);
		panel.add(btnPassword);
		
		final JRadioButton btnEmail = new JRadioButton("Email:");
		btnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEmail.isSelected())
					textFieldEmail.setEditable(true);
				else{
					textFieldEmail.setText(null);
					textFieldEmail.setEditable(false);
				}
			}
		});
		btnEmail.setBounds(8, 181, 127, 25);
		panel.add(btnEmail);
		
		textFieldRealName = new JTextField();
		textFieldRealName.setEditable(false);
		textFieldRealName.setBounds(133, 37, 116, 22);
		panel.add(textFieldRealName);
		textFieldRealName.setColumns(10);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setEditable(false);
		textFieldUserName.setBounds(133, 82, 116, 22);
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setEditable(false);
		textFieldPassword.setBounds(133, 130, 116, 22);
		panel.add(textFieldPassword);
		textFieldPassword.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBounds(133, 182, 116, 22);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO implementar a ação de editar
				if(btnRealName.isSelected())
					System.out.println("RealName");
				if(btnUserName.isSelected())
					System.out.println("UserName");
				if(btnPassword.isSelected())
					System.out.println("Password");
				if(btnEmail.isSelected())
					System.out.println("Email");
				
			}
		});
		btnEdit.setBounds(8, 283, 97, 25);
		panel.add(btnEdit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(152, 283, 97, 25);
		panel.add(btnCancel);
		
		chckbxProtecTuite = new JCheckBox("Protect Tuite");
		chckbxProtecTuite.setBounds(8, 234, 113, 25);
		panel.add(chckbxProtecTuite);
		
	
	}
}
