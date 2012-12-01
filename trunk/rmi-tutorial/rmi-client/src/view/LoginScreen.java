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
import model.User;
import twitter4j.Twitter;
import control.CtrlLogin;
import control.CtrlTwitter;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginScreen.
 */
public class LoginScreen extends javax.swing.JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The login field. */
	private JTextField loginField;
	
	/** The password field. */
	private JPasswordField passwordField;
	
	/** The me. */
	private JFrame me;
	
	/** The rdbtn tuiter. */
	private JRadioButton rdbtnTuiter;
	
	/** The rdbtn twitter. */
	private JRadioButton rdbtnTwitter;
	
	/** The btn register. */
	private JButton btnRegister;
	
	/** The btn login. */
	private JButton btnLogin;
	
	/** The btn quit. */
	private JButton btnQuit;
	
	/** The twitter. */
	private Twitter twitter;
	
	/** The ctrl login. */
	private CtrlLogin ctrlLogin;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
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
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
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
				if (loginField.getText().isEmpty()) {
					ok = false;
				}
				if (passwordField.getText().isEmpty()) {
					ok = false;
				}

				if (ok) {
						// using tuiter
						LoginTO loginTO = null;

						// TODO verificar esse password.string
						loginTO = new LoginTO(loginField.getText(),
								passwordField.getText().toString());
						loginTO = ctrlLogin.doLogin(loginTO);

						if (loginTO != null) {

							if (loginTO.isValidated()) {
								MainScreen ms = new MainScreen(loginTO
										.getUser(), ctrlLogin);
								ms.setVisible(true);
								dispose();
							} else {
								JOptionPane.showMessageDialog(
										null,
										"Server Message: "
												+ loginTO.getErrorMessage(),
										"Warning!", 0);
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"Invalid login or password", "Warning!", 0);
						}

				} else {
					JOptionPane.showMessageDialog(null,
							"Please type your login and password", "Warning!",
							0);
				}

			}
		});
		btnLogin.setBounds(59, 90, 89, 23);
		panel.add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterScreen rs = new RegisterScreen(me);
				rs.setVisible(true);
				// rs.getlis
				setEnabled(false);

			}
		});
		btnRegister.setBounds(158, 90, 89, 23);
		panel.add(btnRegister);

		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnQuit.setBounds(158, 121, 89, 23);
		panel.add(btnQuit);

		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		passwordField.setBounds(80, 59, 217, 20);
		panel.add(passwordField);
		
		JButton btnTwitter = new JButton("Twitter");
		btnTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// using Twitter
					CtrlTwitter ctrlTwitter = new CtrlTwitter(twitter);
					User usr = ctrlTwitter.twLogin();
					
					if (usr != null) {
						MainScreen ms = new MainScreen(usr, ctrlTwitter);
						ms.setVisible(true);
						dispose();
					}
			}
		});
		btnTwitter.setBounds(59, 121, 89, 23);
		panel.add(btnTwitter);


	}
}
