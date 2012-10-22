package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import control.CtrlLogin;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class loginScreen {

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
					loginScreen window = new loginScreen();
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
	public loginScreen() {
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
		
		JButton btnRegister = new JButton("REGISTRAR");
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