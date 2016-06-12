package interfaces.moncompte;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
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
		label.setBounds(10, 10, 211, 23);
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		add(label);
		
		JLabel label_1 = new JLabel("Login");
		label_1.setBounds(10, 39, 84, 14);
		add(label_1);
		
		textFieldLogin = new JTextField();
		label_1.setLabelFor(textFieldLogin);
		textFieldLogin.setBounds(121, 36, 100, 20);
		textFieldLogin.setColumns(10);
		add(textFieldLogin);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(10, 69, 84, 14);
		add(label_2);
		
		passwordField = new JPasswordField();
		label_2.setLabelFor(passwordField);
		passwordField.setBounds(121, 67, 100, 20);
		add(passwordField);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(121, 98, 100, 23);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				String password = new String(passwordField.getPassword());
				
				if(login.length() == 0 || password.length() == 0)
					lblError.setText("Le login et password doivent etre renseignes.");
			
				else if (window.getCltEmpreintes().authentifier(login, password)) {
					lblError.setText(window.getCltEmpreintes().getMessage());
					window.setPane(new MonCompteUpdate(window));
					
				} else
					lblError.setText(window.getCltEmpreintes().getMessage());
			}
		});
		add(btnConnexion);
		
		lblError = new JLabel(" ");
		lblError.setBounds(10, 132, 211, 32);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		add(lblError);

	}

}
