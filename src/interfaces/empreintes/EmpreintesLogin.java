package interfaces.empreintes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
		label.setBounds(58, 6, 99, 22);
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		add(label);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 46, 28, 16);
		add(lblUser);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setBounds(84, 44, 114, 20);
		textFieldLogin.setColumns(10);
		add(textFieldLogin);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(10, 69, 59, 16);
		add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(84, 67, 114, 20);
		add(passwordField);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(84, 118, 114, 29);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				String password = new String(passwordField.getPassword());
				
				if(login.length() == 0 || password.length() == 0)
					lblError.setText("Le login et password doivent �tre renseign�s.");
			
				else if (window.getCltEmpreintes().authentifier(login, password)) {
					lblError.setText(window.getCltEmpreintes().getMessage());
					window.setPane(new EmpreinteMenu(window));
					
				} else
					lblError.setText(window.getCltEmpreintes().getMessage());
			}
		});
		add(btnConnexion);
		
		lblError = new JLabel(" ");
		lblError.setBounds(10, 94, 235, 22);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		add(lblError);

	}

}
