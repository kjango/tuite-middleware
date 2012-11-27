package view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import model.LoginTO;
import twitter4j.Twitter;
import control.CtrlLogin;
import control.CtrlTwitter;

public class LoginScreen extends javax.swing.JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField loginField;
	private JPasswordField passwordField;
	private JFrame me;
	private JRadioButton rdbtnTuiter;
	private JRadioButton rdbtnTwitter;
	private JButton btnRegister;
	private JButton btnLogin;
	private JButton btnQuit;
	private Twitter twitter;
	private CtrlLogin ctrlLogin;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		me = this;
		
		try {
			ctrlLogin = new CtrlLogin();
		} catch (Exception ex) {
			
		}
		
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
		loginField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnLogin.doClick();
				}
			}
		});
		panel.add(loginField);
		loginField.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean ok = true;
				if (loginField.getText().isEmpty()){
					ok = false;
				}
				if (passwordField.getText().isEmpty()){
					ok = false;
				}
				if(ok){
			
					LoginTO loginTO = null;
					//using Twitter
					if (rdbtnTwitter.isSelected()){
						CtrlTwitter ctrlTwitter = new CtrlTwitter(twitter);
						//TODO verificar as alterações necessarias
						//loginTO = ctrlTwitter.twLogin(loginField.getText(), Util.GeraMD5(passwordField.getPassword().toString()));
						ctrlTwitter.twLogin(loginField.getText(), passwordField.getText().toString());
					}else{	
						//TODO verificar esse password.string
						loginTO = new LoginTO(loginField.getText(), passwordField.getText().toString());
						loginTO = ctrlLogin.doLogin(loginTO);
						
						if (loginTO != null) {
							
							if (loginTO.isValidated()){
								MainScreen ms = new MainScreen(loginTO.getUser());
								ms.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Server Message: " + loginTO.getErrorMessage(), "Warning!", 0);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Invalid login or password", "Warning!", 0);
						}
					}
					

				}else{
					JOptionPane.showMessageDialog(null, "Please type your login and password", "Warning!", 0);
				}

			}
		});
		btnLogin.setBounds(10, 121, 89, 23);
		panel.add(btnLogin);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterScreen rs = new RegisterScreen(me);
				rs.setVisible(true);
				//rs.getlis
				setEnabled(false);
				
			}
		});
		btnRegister.setBounds(109, 121, 89, 23);
		panel.add(btnRegister);
		
		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(208, 121, 89, 23);
		panel.add(btnQuit);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnLogin.doClick();
				}
			}
		});
		passwordField.setBounds(80, 59, 217, 20);
		panel.add(passwordField);
		
		ButtonGroup buttonGroup = new ButtonGroup();
				
		rdbtnTuiter = new JRadioButton("Tuiter");
		rdbtnTuiter.setBounds(20, 91, 109, 23);
		panel.add(rdbtnTuiter);
		buttonGroup.add(rdbtnTuiter);
		rdbtnTuiter.setSelected(true);
		
		rdbtnTwitter = new JRadioButton("Twitter");
		rdbtnTwitter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (rdbtnTwitter.isSelected()){
					btnRegister.setEnabled(false);
				}else{
					btnRegister.setEnabled(true);
				}
			}
		});
		rdbtnTwitter.setBounds(188, 91, 109, 23);
		panel.add(rdbtnTwitter);
		buttonGroup.add(rdbtnTwitter);
		
	}
}
