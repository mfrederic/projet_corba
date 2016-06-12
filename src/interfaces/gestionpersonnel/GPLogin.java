package interfaces.gestionpersonnel;

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

public class GPLogin extends JPanel {
	private String userLogin;
	
	private JLabel lblError;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public GPLogin(interfaceGestionPersonnelSwing window) {
		setLayout(null);
		
		JLabel label = new JLabel("Connexion");
		label.setBounds(10, 10, 81, 23);
		label.setFont(new Font("Calibri", Font.BOLD, 18));
		add(label);
		
		JLabel label_1 = new JLabel("Login");
		label_1.setBounds(10, 39, 84, 14);
		add(label_1);
		
		textFieldLogin = new JTextField();
		label_1.setLabelFor(textFieldLogin);
		textFieldLogin.setBounds(104, 36, 163, 20);
		textFieldLogin.setColumns(10);
		add(textFieldLogin);
		
		JLabel label_2 = new JLabel("Password");
		label_2.setBounds(10, 69, 84, 14);
		add(label_2);
		
		passwordField = new JPasswordField();
		label_2.setLabelFor(passwordField);
		passwordField.setBounds(104, 66, 163, 20);
		add(passwordField);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.setBounds(147, 98, 120, 23);
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String login = textFieldLogin.getText();
				String password = new String(passwordField.getPassword());
				
				if(login.length() == 0 || password.length() == 0)
					lblError.setText("Le login et password doivent �tre renseign�s.");
				else if(login.equals("fred") && password.equals("test")) {
					lblError.setText(" ");
					window.setUserLogin(login);
					window.setPane(new GPMenu(window));
				} else
					lblError.setText("Le login ou password est incorrecte.");
			}
		});
		add(btnConnexion);
		
		lblError = new JLabel(" ");
		lblError.setBounds(10, 132, 257, 32);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		add(lblError);

	}

}
