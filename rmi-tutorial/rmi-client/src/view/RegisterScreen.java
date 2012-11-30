package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import model.RegisterTO;
import model.User;
import control.CtrlRegister;

public class RegisterScreen extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel label;
	private JCheckBox chckbxProtectTuite;
	private JButton btnRegister;
	private JButton btnCancel;
	
	private JFrame mother;
	private JFrame me;
	private JLabel lblUserName;
	private JTextField textFieldUserName;

	private CtrlRegister ctrlRegister;

	/**
	 * Create the application.
	 */
	public RegisterScreen(JFrame mother) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.mother = mother;
		
		try {
			ctrlRegister = new CtrlRegister();
		} catch (Exception e) {
			
		}
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setTitle("Tuite");
		setBounds(100, 100, 250, 197);
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub				
				mother.setEnabled(true);

				
			}
			
			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblName = new JLabel("Real Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblName);
		
		textFieldName = new JTextField();
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
		lblUserName = new JLabel("User Name:");
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblUserName);
		
		textFieldUserName = new JTextField();
		panel.add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblEmail);
		
		textFieldEmail = new JTextField();
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblPassword);
		
		passwordField = new JPasswordField();
		panel.add(passwordField);
		
		label = new JLabel("");
		panel.add(label);
		
		chckbxProtectTuite = new JCheckBox("Protect Tuite?");
		chckbxProtectTuite.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(chckbxProtectTuite);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean ok = true;
				if (textFieldName.getText().isEmpty()){
					ok = false;
				}
				if (textFieldEmail.getText().isEmpty()){
					ok = false;
				}
				if (passwordField.getText().isEmpty()){
					ok = false;
				}
				if (textFieldUserName.getText().isEmpty()){
					ok = false;
				}
				if (ok){
						RegisterTO registerTO = new RegisterTO(new User(0, textFieldEmail.getText(), textFieldName.getText(), textFieldUserName.getText(), null, new Date(), chckbxProtectTuite.isSelected(), null, null, null, null), passwordField.getText());
						registerTO = ctrlRegister.doRegistry(registerTO);
						if (registerTO.isRegistered()){
							JOptionPane.showMessageDialog(null, "User registered!", "Success!", 1);
							dispose();
						}else{
							JOptionPane.showMessageDialog(null, registerTO.getErrorMessage(), "Warning!", 0);
						}
				}else{
					JOptionPane.showMessageDialog(null, "All fields required!", "Warning!", 0);
				}
			}
		});
		panel.add(btnRegister);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mother.setEnabled(true);
				dispose();
			}
		});
		panel.add(btnCancel);
		
		
		repaint();
	}

}
