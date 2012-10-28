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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.User;
import control.CtrlLogin;

public class LoginScreen extends javax.swing.JFrame{

	private JTextField loginField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
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
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setResizable(false);
		setMinimumSize(new Dimension(354, 205));
		setLocationByPlatform(true);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentHidden(ComponentEvent arg0) {
			}
		});
		setTitle("Tuiter");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 328, 155);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblLogin.setBounds(35, 25, 35, 22);
		panel.add(lblLogin);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(10, 57, 60, 22);
		panel.add(lblPassword);
		
		loginField = new JTextField();
		loginField.setBounds(80, 27, 217, 20);
		panel.add(loginField);
		loginField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				User user = null;
			 
				CtrlLogin ctrlLogin = new CtrlLogin(loginField.getText(), passwordField.getPassword().toString());
				user = ctrlLogin.CtrlLoginTO.getUser();
				
				if (user != null){
					MainScreen ms = new MainScreen(user);
					ms.setVisible(true);
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid login or password", "Warning!", 0);
				}
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
