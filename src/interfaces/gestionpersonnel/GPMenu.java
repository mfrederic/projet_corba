package interfaces.gestionpersonnel;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GPMenu extends JPanel {
	private static final long serialVersionUID = -1032838324223332591L;

	/**
	 * Create the panel.
	 */
	public GPMenu(interfaceGestionPersonnelSwing window) {
		setLayout(null);
		
		JLabel lblGestionCompte = new JLabel("Menu");
		lblGestionCompte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblGestionCompte.setBounds(10, 11, 230, 26);
		add(lblGestionCompte);
		
		JButton btnCrerUnCompte = new JButton("Cr\u00E9er un compte");
		btnCrerUnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new CreerCompte(window));
			}
		});
		btnCrerUnCompte.setBounds(10, 48, 160, 23);
		add(btnCrerUnCompte);
		
		JButton btnAjouterPhoto = new JButton("Ajouter photo");
		btnAjouterPhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new AjouterPhoto(window));
			}
		});
		btnAjouterPhoto.setBounds(10, 82, 160, 23);
		add(btnAjouterPhoto);
		
		JButton btnSupprimerEmpreinte = new JButton("Supprimer empreinte");
		btnSupprimerEmpreinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new SupprimerEmpreinte(window));
			}
		});
		btnSupprimerEmpreinte.setBounds(10, 116, 160, 23);
		add(btnSupprimerEmpreinte);
		
		JButton btnNewButton = new JButton("Deconnexion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new GPLogin(window));
			}
		});
		btnNewButton.setBounds(10, 150, 160, 23);
		add(btnNewButton);

	}

}
