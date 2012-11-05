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

public class EditProfileScreen extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldRealName;
	private JTextField textFieldScreenName;
	private JTextField textFieldPassword;
	private JTextField textFieldEmail;
	private JCheckBox chckbxProtecTuite;
	
	private Compute compute;
	private User user;
	/**
	 * Launch the application.
	 */

	
	public EditProfileScreen(User user, Compute compute){
		setTitle("Tuite");
		this.compute = compute;
		this.user = user;
		initialize();
		
		if(user.isProtectedTuite()){
			chckbxProtecTuite.setSelected(true);
		}else{
			chckbxProtecTuite.setSelected(false);
		}
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
		
		final JRadioButton btnScreenName = new JRadioButton("User Name:");
		btnScreenName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnScreenName.isSelected())
					textFieldScreenName.setEditable(true);
				else{
					textFieldScreenName.setText(null);
					textFieldScreenName.setEditable(false);
				}
			}
		});
		btnScreenName.setBounds(8, 81, 127, 25);
		panel.add(btnScreenName);
		
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
		
		textFieldScreenName = new JTextField();
		textFieldScreenName.setEditable(false);
		textFieldScreenName.setBounds(133, 82, 116, 22);
		panel.add(textFieldScreenName);
		textFieldScreenName.setColumns(10);
		
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
				String Email = null;
				String RealName = null;
				String ScreenName = null;
				String Password = null;
				boolean ProtectTuite;
				boolean ok = true;
				
				if(btnRealName.isSelected()){
					RealName = textFieldRealName.getText();
					if(textFieldRealName.getText().isEmpty())
						ok = false;
				}
				if(btnScreenName.isSelected()){
					ScreenName = textFieldScreenName.getText();
					if(textFieldScreenName.getText().isEmpty())
						ok = false;
				}
				if(btnPassword.isSelected()){
					Password = textFieldPassword.getText();
					if(textFieldPassword.getText().isEmpty())
						ok = false;
				}
				if(btnEmail.isSelected()){
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
				User u = t.getUser();
				
				System.out.println("Usuário Editado:");
				System.out.println("Usuario: "+ u.getRealName());
				System.out.println("Email: "+u.getEmail());
				System.out.println("LoginName: "+ u.getLoginName());
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
		
	
	}
}
