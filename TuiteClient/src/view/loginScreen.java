package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import control.CtrlLogin;

public class LoginScreen {

	private JFrame frmTuiter;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frmTuiter.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTuiter = new JFrame();
		frmTuiter.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmTuiter.setResizable(false);
		frmTuiter.setMinimumSize(new Dimension(354, 205));
		frmTuiter.setLocationByPlatform(true);
		frmTuiter.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
		});
		frmTuiter.setTitle("Tuiter");
		frmTuiter.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 328, 155);
		frmTuiter.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setBounds(35, 25, 35, 22);
		panel.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(10, 57, 60, 22);
		panel.add(lblPassword);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(80, 27, 217, 20);
		panel.add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CtrlLogin ctrlLogin = new CtrlLogin();
				ctrlLogin.login(textFieldLogin.getText(), passwordField.getPassword());
			}
		});
		btnLogin.setBounds(10, 103, 89, 23);
		panel.add(btnLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(109, 103, 89, 23);
		panel.add(btnRegister);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(208, 103, 89, 23);
		panel.add(btnQuit);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 59, 217, 20);
		panel.add(passwordField);
	}
}
