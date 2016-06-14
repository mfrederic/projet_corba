package interfaces.gestionpersonnel;

import interfaces.gestionpersonnel.InterfaceGestionPersonnelSwing.PersonneComboBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gestion_acces.personne;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

public class AjouterPhoto extends JPanel {
	private static final long serialVersionUID = -3808190305188198934L;
	private JTextField textFieldValeur;
	private JLabel lblError;

	/**
	 * Create the panel.
	 */
	public AjouterPhoto(InterfaceGestionPersonnelSwing window) {
		setLayout(null);
		
		JLabel lblAouterPhoto = new JLabel("Aouter photo");
		lblAouterPhoto.setFont(new Font("Calibri", Font.BOLD, 18));
		lblAouterPhoto.setBounds(10, 11, 153, 25);
		add(lblAouterPhoto);
		
		
		JLabel lblListeDesUtilisateurs = new JLabel("Liste des utilisateurs");
		lblListeDesUtilisateurs.setBounds(10, 47, 252, 14);
		add(lblListeDesUtilisateurs);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setBounds(10, 112, 65, 14);
		add(lblPhoto);
		
		JButton btnChoisirUnFichier = new JButton("Choisir un fichier");
		btnChoisirUnFichier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int random = (int)(Math.random() * (350 - 1)) + 1;
				textFieldValeur.setText(String.valueOf(random));
			}
		});
		btnChoisirUnFichier.setBounds(85, 108, 177, 23);
		add(btnChoisirUnFichier);
		
		textFieldValeur = new JTextField();
		textFieldValeur.setEnabled(false);
		textFieldValeur.setEditable(false);
		textFieldValeur.setBounds(176, 142, 86, 20);
		add(textFieldValeur);
		textFieldValeur.setColumns(10);

		personne[] listPersonnes = null;
		try {
			listPersonnes = window.getCltGestPers().chercherPersonnes(new String(), new String());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		JComboBox<Object> comboBox = new JComboBox<Object>(PersonneComboBox.getInstances(listPersonnes));
		comboBox.setBounds(10, 72, 252, 20);
		add(comboBox);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldValeur.getText().length() == 0) {
					lblError.setText("Vous devez choisir une photo.");
					return;
				}
				
				try {
					PersonneComboBox p = (PersonneComboBox) comboBox.getSelectedItem();
					window.getCltGestPers().ajouterPhoto((short) p.getIdPersonne(), textFieldValeur.getText());
					lblError.setText(window.getCltGestPers().getMessage());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (droitsInsuffisants e1) {
					lblError.setText(e1.raison);
				}
			}
		});
		btnAjouter.setBounds(142, 220, 120, 23);
		add(btnAjouter);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 173, 252, 36);
		add(lblError);
		
		JButton btnRetour = new JButton("retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new GPMenu(window));
			}
		});
		btnRetour.setBounds(173, 13, 89, 23);
		add(btnRetour);

	}
	
}
