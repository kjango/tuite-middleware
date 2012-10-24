package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class registerScreen {

	private JFrame frmTuiter;
	private JTextField textFieldName;
	private JTextField textFieldEmail;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btnConfirm;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerScreen window = new registerScreen();
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
	public registerScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTuiter = new JFrame();
		frmTuiter.setResizable(false);
		frmTuiter.setTitle("Tuiter");
		frmTuiter.setBounds(100, 100, 266, 262);
		frmTuiter.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Register", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmTuiter.getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Full name:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 31, 110, 25);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(118, 31, 110, 25);
		textFieldName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 67, 110, 25);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(118, 67, 110, 25);
		textFieldEmail.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(10, 103, 110, 25);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 103, 110, 25);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password:");
		lblRepeatPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblRepeatPassword.setBounds(10, 139, 110, 25);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(118, 139, 110, 25);
		panel.setLayout(null);
		panel.add(lblNewLabel);
		panel.add(textFieldName);
		panel.add(lblNewLabel_1);
		panel.add(textFieldEmail);
		panel.add(lblNewLabel_2);
		panel.add(passwordField);
		panel.add(lblRepeatPassword);
		panel.add(passwordField_1);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(31, 200, 89, 23);
		panel.add(btnConfirm);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnCancel.setBounds(139, 200, 89, 23);
		panel.add(btnCancel);
	}

}
