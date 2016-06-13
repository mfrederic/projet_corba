package interfaces.moncompte;

import javax.swing.JPanel;

import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MonCompteUpdate extends JPanel {

	private InterfaceMonCompteSwing window;
	private JPasswordField passwordField_1;
	private JPasswordField passwordFieldBis;
	private JLabel lblError;

	/**
	 * Create the panel.
	 */
	public MonCompteUpdate(InterfaceMonCompteSwing window) {
		this.window = window;
		setLayout(null);
		
		JLabel lblModifierLePassword = new JLabel("Modifier le password");
		lblModifierLePassword.setFont(new Font("Calibri", Font.BOLD, 18));
		lblModifierLePassword.setBounds(44, 6, 196, 22);
		add(lblModifierLePassword);
		
		JLabel lblNouveau = new JLabel("Nouveau");
		lblNouveau.setBounds(10, 43, 55, 16);
		add(lblNouveau);
		
		JLabel lblNouveaubis = new JLabel("Nouveau (bis)");
		lblNouveaubis.setBounds(10, 71, 86, 16);
		add(lblNouveaubis);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(125, 45, 158, 20);
		add(passwordField_1);
		
		passwordFieldBis = new JPasswordField();
		passwordFieldBis.setBounds(125, 69, 158, 20);
		add(passwordFieldBis);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField_1.getPassword());
				String password_bis = new String(passwordFieldBis.getPassword());
				
				if(!password.equals(password_bis)) {
					lblError.setText("Les mots de passe ne correspondent pas.");
					return;
				}
				
				window.getCltMonCompte().modifierMdp(window.getCltMonCompte().getUserConnecte(), password);
				lblError.setText(window.getCltMonCompte().getMessage());
				
			}
		});
		btnEnregistrer.setBounds(171, 98, 112, 29);
		add(btnEnregistrer);
		
		JButton btnRetour = new JButton("Deconnexion");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new MonCompteLogin(window));
			}
		});
		btnRetour.setBounds(42, 98, 127, 29);
		add(btnRetour);
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 132, 273, 33);
		add(lblError);
	}
	
}
