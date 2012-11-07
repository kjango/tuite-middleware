package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JCheckBox;

import control.CtrlRegister;
import control.CtrlTuite;

import model.RegisterTO;
import model.Tuite;
import model.User;

import base.Compute;
import javax.swing.JPasswordField;

public class EditProfileScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRealName;
	private JTextField textFieldScreenName;
	private JTextField textFieldEmail;
	private JCheckBox chckbxProtecTuite;
	private JCheckBox chckbxRealName;
	private JCheckBox chckbxUserName;
	private JCheckBox chckbxPassword;
	private JCheckBox chckbxEmail;
	
	private Compute compute;
	private User user;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */

	
	public EditProfileScreen(User user, Compute compute){
		setTitle("Tuite");
		this.compute = compute;
		this.user = user;
		initialize();
		setFields();
		
		if(user.isProtectedTuite()){
			chckbxProtecTuite.setSelected(true);
		}else{
			chckbxProtecTuite.setSelected(false);
		}
	}
	
	public void setFields(){
		textFieldRealName.setText(user.getRealName());
		textFieldScreenName.setText(user.getLoginName());
		textFieldEmail.setText(user.getEmail());
	}

	/**
	 * Create the frame.
	 */
	public void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 283, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Edit Profile", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textFieldRealName = new JTextField();
		textFieldRealName.setEditable(false);
		textFieldRealName.setBounds(133, 37, 116, 22);
		panel.add(textFieldRealName);
		textFieldRealName.setColumns(10);
		
		textFieldScreenName = new JTextField();
		textFieldScreenName.setEditable(false);
		textFieldScreenName.setBounds(133, 82, 116, 22);
		panel.add(textFieldScreenName);
		textFieldScreenName.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBounds(133, 182, 116, 22);
		panel.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO implementar a ação de editar
				String Email = null;
				String RealName = null;
				String ScreenName = null;
				String Password = null;
				boolean ProtectTuite;
				boolean ok = true;
				
				if(chckbxRealName.isSelected()){
					RealName = textFieldRealName.getText();
					if(textFieldRealName.getText().isEmpty())
						ok = false;
				}
				if(chckbxUserName.isSelected()){
					ScreenName = textFieldScreenName.getText();
					if(textFieldScreenName.getText().isEmpty())
						ok = false;
				}
				if(chckbxPassword.isSelected()){
					Password = passwordField.getText();
					if(passwordField.getText().isEmpty())
						ok = false;
				}
				if(chckbxEmail.isSelected()){
					Email = textFieldEmail.getText();
					if(textFieldEmail.getText().isEmpty())
						ok = false;
				}
				if(chckbxProtecTuite.isSelected())
					ProtectTuite = true;
				else
					ProtectTuite = false;
				
				if(ok){
				
//				//Criando o TO
				RegisterTO t = new RegisterTO(Email,RealName,ScreenName,Password,ProtectTuite);
				t.setUser(user);
			
//				//Criando o controle
				CtrlRegister ctrl = new CtrlRegister();
				
//				//Chamar doEditProfile e receber no retorno o RegisterTO com dados editados
				t = ctrl.doEditProfile(t, compute);
				user = t.getUser();
				
				//Fill the fields;
				setFields();
				
				JOptionPane.showMessageDialog(null, 
						"Usuário Editado:"+
						"\nUsuario: "+ user.getRealName()+
						"\nEmail: "+user.getEmail()+
						"\nLoginName: "+ user.getLoginName(), "Success!", 1);
				
//				System.out.println("Usuário Editado:");
//				System.out.println("Usuario: "+ u.getRealName());
//				System.out.println("Email: "+u.getEmail());
//				System.out.println("LoginName: "+ u.getLoginName());
//				
//				//Print de teste
//				System.out.println("\n\n\nEstou aqui no cliente:  "+tuite.getText());
				
				}
				else
					JOptionPane.showMessageDialog(null, "All fields required!", "Warning!", 0);

				
			}
		});
		btnEdit.setBounds(8, 283, 97, 25);
		panel.add(btnEdit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(152, 283, 97, 25);
		panel.add(btnCancel);
		
		chckbxProtecTuite = new JCheckBox("Protect Tuite");
		chckbxProtecTuite.setBounds(8, 234, 113, 25);
		panel.add(chckbxProtecTuite);
		
		chckbxRealName = new JCheckBox("Real Name:");
		chckbxRealName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxRealName.isSelected()){
					textFieldRealName.setText(null);
					textFieldRealName.setEditable(true);
				}
				else{
					textFieldRealName.setText(user.getRealName());
					textFieldRealName.setEditable(false);
				}
			}
		});
		chckbxRealName.setBounds(8, 36, 113, 25);
		panel.add(chckbxRealName);
		
		chckbxUserName = new JCheckBox("User Name:");
		chckbxUserName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxUserName.isSelected()){
					textFieldScreenName.setText(null);
					textFieldScreenName.setEditable(true);
				}
				else{
					textFieldScreenName.setText(user.getLoginName());
					textFieldScreenName.setEditable(false);
				}
			}
		});
		chckbxUserName.setBounds(8, 81, 113, 25);
		panel.add(chckbxUserName);
		
		chckbxPassword = new JCheckBox("Password:");
		chckbxPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxPassword.isSelected()){
					passwordField.setEditable(true);
				}
				else{
					passwordField.setText(null);
					passwordField.setEditable(false);
				}
			}
		});
		chckbxPassword.setBounds(8, 129, 113, 25);
		panel.add(chckbxPassword);
		
		chckbxEmail = new JCheckBox("Email:");
		chckbxEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxEmail.isSelected()){
					textFieldEmail.setText(null);
					textFieldEmail.setEditable(true);
				}
				else{
					textFieldEmail.setText(user.getEmail());
					textFieldEmail.setEditable(false);
				}
			}
		});
		chckbxEmail.setBounds(8, 181, 113, 25);
		panel.add(chckbxEmail);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setBounds(133, 130, 116, 22);
		panel.add(passwordField);
		
	
	}
}
