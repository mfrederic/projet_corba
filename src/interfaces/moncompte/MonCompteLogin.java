package interfaces.moncompte;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonCompteLogin extends JPanel {
	private static final long serialVersionUID = -2225760132342488615L;
	
	private JLabel lblError;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public MonCompteLogin(InterfaceMonCompteSwing window) {
		setLayout(null);
		
		JLabel label = new JLabel("Connexion");
		label.setBounds(64, 6, 99, 22);
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		add(label);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblUser.setBounds(10, 42, 28, 16);
		add(lblUser);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblUser.setLabelFor(textFieldLogin);
		textFieldLogin.setBounds(89, 40, 130, 20);
		textFieldLogin.setColumns(10);
		add(textFieldLogin);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setBounds(10, 69, 59, 16);
		add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setLabelFor(passwordField);
		passwordField.setBounds(89, 67, 130, 20);
		add(passwordField);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnConnexion.setBounds(109, 98, 112, 29);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				String password = new String(passwordField.getPassword());
				
				if(login.length() == 0 || password.length() == 0)
					lblError.setText("Le login et password doivent etre renseignes.");
				else
					try {
						if (window.getCltMonCompte().authentifier(login, password)) {
							lblError.setText(window.getCltMonCompte().getMessage());
							window.setPane(new MonCompteUpdate(window));
							window.getFrame().setBounds(100, 100, 350, 195);
							
						} else
							lblError.setText(window.getCltMonCompte().getMessage());
					} catch (droitsInsuffisants e1) {
						// TODO Auto-generated catch block
						lblError.setText(e1.raison);
					}
			}
		});
		add(btnConnexion);
		
		lblError = new JLabel(" ");
		lblError.setBounds(10, 139, 211, 32);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		add(lblError);

	}

}
