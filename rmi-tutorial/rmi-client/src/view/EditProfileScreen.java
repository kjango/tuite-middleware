package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.RegisterTO;
import model.User;
import control.CtrlRegister;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The Class EditProfileScreen.
 */
public class EditProfileScreen extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** The text field for the user's real name. */
	private JTextField textFieldRealName;

	/** The text field for the user's screen name. */
	private JTextField textFieldScreenName;

	/** The text field for theuser's email. */
	private JTextField textFieldEmail;

	/** The chckbx protec tuite. */
	private JCheckBox chckbxProtecTuite;

	/** The chckbx real name. */
	private JCheckBox chckbxRealName;

	/** The chckbx user name. */
	private JCheckBox chckbxUserName;

	/** The chckbx password. */
	private JCheckBox chckbxPassword;

	/** The chckbx email. */
	private JCheckBox chckbxEmail;

	/** The user. */
	private User user;

	/** The main screen. */
	private MainScreen mother;

	/** The password field. */
	private JPasswordField passwordField;

	private File twImage = null;

	/**
	 * Launch the application.
	 * 
	 * @param user
	 *            : the user
	 * @param mother
	 *            : the main screen
	 */

	public EditProfileScreen(MainScreen mother) {

		setTitle("Tuite");
		this.user = mother.getUser();
		this.mother = mother;
		initialize();
		setFields();

		if (user.isProtectedTuite()) {
			chckbxProtecTuite.setSelected(true);
		} else {
			chckbxProtecTuite.setSelected(false);
		}
	}

	/**
	 * Sets the fields.
	 */
	public void setFields() {
		textFieldRealName.setText(mother.getUser().getRealName());
		textFieldScreenName.setText(mother.getUser().getLoginName());
		if (!mother.isTwitter()) {
			textFieldEmail.setText(mother.getUser().getEmail());
		} else {
			chckbxEmail.setEnabled(false);
			textFieldEmail.setEnabled(false);
			chckbxPassword.setEnabled(false);
			passwordField.setEnabled(false);
			chckbxUserName.setEnabled(false);
			textFieldScreenName.setEnabled(false);
			chckbxProtecTuite.setEnabled(false);
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

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				if (!mother.isTwitter()) {
					mother.setUser(user);
					mother.update();
				}
				mother.setEnabled(true);
			}
		});

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Edit Profile",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		textFieldRealName = new JTextField();
		textFieldRealName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textFieldRealName.getText().length() >= 20){
					String aux = textFieldRealName.getText().substring(0, 19);
					textFieldRealName.setText(aux);
				}
			}
		});

		textFieldRealName.setEditable(false);
		textFieldRealName.setBounds(133, 37, 116, 22);
		panel.add(textFieldRealName);
		textFieldRealName.setColumns(10);

		textFieldScreenName = new JTextField();
		textFieldScreenName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (textFieldScreenName.getText().length() >= 20){
					String aux = textFieldScreenName.getText().substring(0, 19);
					textFieldScreenName.setText(aux);
				}
			}
		});

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
				String email = null;
				String realName = null;
				String screenName = null;
				String lastScreenName = user.getLoginName();
				String password = null;
				boolean protectedTuite;
				boolean changed_login = false;
				boolean ok = true;

				if (chckbxRealName.isSelected()) {
					realName = textFieldRealName.getText();
					if (textFieldRealName.getText().isEmpty())
						ok = false;
				}
				if (chckbxUserName.isSelected()) {
					screenName = textFieldScreenName.getText();
					if (textFieldScreenName.getText().isEmpty())
						ok = false;
				}
				if (chckbxPassword.isSelected()) {
					password = passwordField.getText();
					if (passwordField.getText().isEmpty())
						ok = false;
				}
				if (chckbxEmail.isSelected()) {
					email = textFieldEmail.getText();
					if (textFieldEmail.getText().isEmpty())
						ok = false;
				}
				if (chckbxProtecTuite.isSelected())
					protectedTuite = true;
				else
					protectedTuite = false;

				user.setProtectedTuite(protectedTuite);

				if (ok) {

					if (!mother.isTwitter()) {
						// Criando o TO
						if (email != null)
							user.setEmail(email);
						if (realName != null)
							user.setRealName(realName);
						if (screenName != null) {
							user.setLoginName(screenName);
							if (!screenName.equals(lastScreenName))
								changed_login = true;
						}
						RegisterTO t = new RegisterTO(user, password);
						t.setModifiedLogin(changed_login);
						// t.setUser(user);
						// Criando o controle
						try {
							CtrlRegister ctrl = new CtrlRegister();

							// Chamar doEditProfile e receber no retorno o
							// RegisterTO com dados editados

							t = ctrl.doEditProfile(t);
							user = t.getUser();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						if (t.isRegistered()) {
							// Fill the fields;
							setFields();

							JOptionPane.showMessageDialog(null,
									"Changes saved!", "Success!", 1);
							dispose();

						} else {
							JOptionPane.showMessageDialog(null,
									t.getErrorMessage(), "Warning!", 0);
						}
					} else {
						// twitter
						boolean chchchaaanges = false;
						if (twImage != null) {
							mother.getCtrlTwitter().changeProfileImage(twImage);
							chchchaaanges = true;
						}
						if (chckbxRealName.isSelected()) {
							mother.getCtrlTwitter().changeRealName(
									textFieldRealName.getText());
							chchchaaanges = true;
						}
						if (chchchaaanges) {
							mother.updateUser();
							setFields();
							JOptionPane.showMessageDialog(null,
									"Changes saved!", "Success!", 1);
						}
						dispose();
						
					}
				} else{
					JOptionPane.showMessageDialog(null,
									"All fields required!",
									"Warning!", 0);
				}

			}
		});
		btnEdit.setBounds(8, 283, 115, 25);
		panel.add(btnEdit);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(133, 283, 116, 25);
		panel.add(btnCancel);

		chckbxProtecTuite = new JCheckBox("Protect Tuite");
		chckbxProtecTuite.setBounds(8, 234, 113, 25);
		panel.add(chckbxProtecTuite);

		chckbxRealName = new JCheckBox("Real Name:");
		chckbxRealName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (chckbxRealName.isSelected()) {
					// textFieldRealName.setText(null);
					textFieldRealName.setEditable(true);
				} else {
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
				if (chckbxUserName.isSelected()) {
					// textFieldScreenName.setText(null);
					textFieldScreenName.setEditable(true);
				} else {
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
				if (chckbxPassword.isSelected()) {
					passwordField.setEditable(true);
				} else {
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
				if (chckbxEmail.isSelected()) {
					// textFieldEmail.setText(null);
					textFieldEmail.setEditable(true);
				} else {
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

		JButton btnSelectImage = new JButton("Select Image");
		btnSelectImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpImagem();
			}
		});
		btnSelectImage.setBounds(133, 235, 114, 23);
		panel.add(btnSelectImage);

	}

	/**
	 * Upload the user's photo.
	 */
	public void UpImagem() {

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG, PNG & GIF Images", "jpg", "gif", "png");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// System.out.println("You chose to open this file: "
			// + chooser.getSelectedFile().getName());
		}

		try {
			if (!mother.isTwitter()) {
				user.setPhoto(new ImageIcon(chooser.getSelectedFile()
						.getAbsolutePath()));
			} else {
				twImage = chooser.getSelectedFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
