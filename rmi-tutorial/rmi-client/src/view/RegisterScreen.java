package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import control.CtrlRegister;

import model.RegisterTO;

import base.Compute;

public class RegisterScreen extends javax.swing.JFrame{

	private JFrame frmTuite;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private JLabel label;
	private JCheckBox chckbxProtectTuite;
	private JButton btnRegister;
	private JButton btnCancel;
	
	private Compute compute;


	/**
	 * Create the application.
	 */
	public RegisterScreen(Compute compute) {
		this.compute = compute;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTuite = new JFrame();
		frmTuite.setTitle("Tuite");
		frmTuite.setBounds(100, 100, 250, 197);
		frmTuite.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmTuite.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblName = new JLabel("Name:");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panel.add(lblName);
		
		textFieldName = new JTextField();
		panel.add(textFieldName);
		textFieldName.setColumns(10);
		
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
				if (ok){
					//crio o TO pra fazer o registro

					RegisterTO registerTO = new RegisterTO(textFieldEmail.getText(), textFieldName.getText(), passwordField.getText(), chckbxProtectTuite.isSelected());
					CtrlRegister ctrlRegister = new CtrlRegister();
					ok = ctrlRegister.doRegistry(registerTO, compute);
					
					if (ok){
						JOptionPane.showMessageDialog(null, "User registered!", "Success!", 0);
					}else{
						JOptionPane.showMessageDialog(null, "Sorry, user not created.", "Warning!", 0);
					}
					
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "All fields required!", "Warning!", 0);
				}
				
				
			}
		});
		panel.add(btnRegister);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnCancel);
		
		
		repaint();
	}

}
