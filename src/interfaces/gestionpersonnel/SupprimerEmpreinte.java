package interfaces.gestionpersonnel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.SwingConstants;

import Gestion_acces.rolePersonne;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite;

public class SupprimerEmpreinte extends JPanel {
	private static final long serialVersionUID = 2782894538680040010L;
	private interfaceGestionPersonnelSwing window;
	private JLabel lblError;

	/**
	 * Create the panel.
	 */
	public SupprimerEmpreinte(interfaceGestionPersonnelSwing window) {
		setLayout(null);
		
		this.window = window;
		
		JLabel lblSupprimerUneEmpreinte = new JLabel("Supprimer une empreinte");
		lblSupprimerUneEmpreinte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblSupprimerUneEmpreinte.setBounds(10, 11, 284, 28);
		add(lblSupprimerUneEmpreinte);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur");
		lblUtilisateur.setBounds(10, 50, 101, 14);
		add(lblUtilisateur);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Fred", "Marie", "R\u00E9my"}));
		comboBox.setBounds(10, 75, 284, 20);
		add(comboBox);
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 140, 284, 38);
		add(lblError);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					supprimerEmpreinte((String) comboBox.getSelectedItem());
				} catch (droitsInsuffisants e1) {
					lblError.setText("Droits insuffisants : " + e1.raison);
				}
			}
		});
		btnSupprimer.setBounds(205, 106, 89, 23);
		add(btnSupprimer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new GPMenu(window));
			}
		});
		btnAnnuler.setBounds(106, 106, 89, 23);
		add(btnAnnuler);

		
	}
	
	private void supprimerEmpreinte(String user) throws droitsInsuffisants {
		if ((window.getPersTemp().role == rolePersonne.accueil) || (window.getPersTemp().role == rolePersonne.RH)){

			try {
				window.getMonAuthentification().getMonAuthentification().supprimerEmpreinte(user, window.getCleserveur());
			} catch (accesRefuse e) {
				lblError.setText("Acces refuse : " + e.raison);
			} catch (compteInexistant e) {
				lblError.setText("Compte inexistant : (user: " + e.user + ")");
			} catch (suppressionInterdite e) {
				lblError.setText("Vous n'avez pas le droit de supprimer l'empreinte (role = " + e.role + ")");
			}

		
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH ou Accueil");
		}
	}

}
