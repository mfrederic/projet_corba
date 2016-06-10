package interfaces.empreintes;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EmpreintesLogin extends JPanel {
	private JLabel lblError;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public EmpreintesLogin(InterfaceEmpreinteSwing window) {
		setLayout(null);
		
		JLabel label = new JLabel("Connexion");
		label.setBounds(10, 10, 81, 23);
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		add(label);
		
		JLabel label_1 = new JLabel("Login");
		label_1.setBounds(10, 39, 84, 14);
		add(label_1);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(121, 36, 100, 20);
		textFieldLogin.setColumns(10);
		add(textFieldLogin);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(10, 69, 84, 14);
		add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(121, 67, 100, 20);
		add(passwordField);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(86, 127, 83, 23);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				String password = new String(passwordField.getPassword());
				
				if(login.length() == 0 || password.length() == 0)
					lblError.setText("Le login et password doivent être renseignés.");
				else if(login.equals("fred") && password.equals("test")) {
					lblError.setText(" ");
					window.setUserLogin(login);
					window.setPane(new EmpreinteMenu(window));
				} else
					lblError.setText("Le login ou password est incorrecte.");
			}
		});
		add(btnConnexion);
		
		lblError = new JLabel(" ");
		lblError.setBounds(10, 94, 257, 32);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		add(lblError);

	}

}
