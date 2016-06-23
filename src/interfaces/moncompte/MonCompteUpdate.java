package interfaces.moncompte;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class MonCompteUpdate extends JPanel {
	private static final long serialVersionUID = 6901479461579606886L;
	
	private JPasswordField passwordField_1;
	private JPasswordField passwordFieldBis;
	private JLabel lblError;

	/**
	 * Create the panel.
	 */
	public MonCompteUpdate(InterfaceMonCompteSwing window) {
		setLayout(null);
		
		JLabel lblModifierLePassword = new JLabel("Modifier le password");
		lblModifierLePassword.setFont(new Font("Calibri", Font.BOLD, 18));
		lblModifierLePassword.setBounds(10, 10, 196, 22);
		add(lblModifierLePassword);
		
		JLabel lblNouveau = new JLabel("Nouveau");
		lblNouveau.setBounds(10, 43, 55, 16);
		add(lblNouveau);
		
		JLabel lblNouveaubis = new JLabel("Nouveau (bis)");
		lblNouveaubis.setBounds(10, 71, 86, 16);
		add(lblNouveaubis);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(170, 43, 158, 20);
		add(passwordField_1);
		
		passwordFieldBis = new JPasswordField();
		passwordFieldBis.setBounds(170, 69, 158, 20);
		add(passwordFieldBis);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setFont(new Font("Calibri", Font.PLAIN, 11));
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
		btnEnregistrer.setBounds(239, 100, 89, 23);
		add(btnEnregistrer);
		
		JButton btnRetour = new JButton("Deconnexion");
		btnRetour.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new MonCompteLogin(window));
				window.getFrame().setBounds(100, 100, 245, 245);
			}
		});
		btnRetour.setBounds(216, 12, 112, 23);
		add(btnRetour);
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 132, 318, 33);
		add(lblError);
	}
}
