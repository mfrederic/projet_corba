package interfaces.gestionpersonnel;

import interfaces.gestionpersonnel.InterfaceGestionPersonnelSwing.PersonneComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Gestion_acces.personne;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

public class SupprimerEmpreinte extends JPanel {
	private static final long serialVersionUID = 2782894538680040010L;
	private JLabel lblError;

	/**
	 * Create the panel.
	 */
	public SupprimerEmpreinte(InterfaceGestionPersonnelSwing window) {
		setLayout(null);
		
		JLabel lblSupprimerUneEmpreinte = new JLabel("Supprimer une empreinte");
		lblSupprimerUneEmpreinte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblSupprimerUneEmpreinte.setBounds(10, 11, 284, 28);
		add(lblSupprimerUneEmpreinte);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur");
		lblUtilisateur.setBounds(10, 50, 101, 14);
		add(lblUtilisateur);
		
		personne[] listPersonnes = null;
		try {
			listPersonnes = window.getCltGestPers().chercherPersonnes(new String(), new String());
		} catch (droitsInsuffisants e2) {
			e2.printStackTrace();
		}
		JComboBox<Object> comboBox = new JComboBox<Object>(PersonneComboBox.getInstances(listPersonnes));
		comboBox.setBounds(10, 76, 284, 20);
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
					PersonneComboBox p = (PersonneComboBox) comboBox.getSelectedItem();
					window.getCltGestPers().supprimerEmpreinte((short) p.getIdPersonne());
					lblError.setText(window.getCltGestPers().getMessage());
				} catch (droitsInsuffisants e1) {
					lblError.setText(e1.raison);
				}
			}
		});
		btnSupprimer.setBounds(206, 117, 89, 23);
		add(btnSupprimer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//window.setPane(new GPMenu(window));
			}
		});
		btnAnnuler.setBounds(118, 117, 89, 23);
		add(btnAnnuler);

		
	}

}
