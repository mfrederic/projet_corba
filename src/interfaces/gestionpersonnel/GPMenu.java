package interfaces.gestionpersonnel;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Gestion_acces.rolePersonne;

public class GPMenu extends JPanel {
	private static final long serialVersionUID = -1032838324223332591L;

	/**
	 * Create the panel.
	 */
	public GPMenu(InterfaceGestionPersonnelSwing window) {
		setLayout(null);
		
		JLabel lblGestionCompte = new JLabel("Menu");
		lblGestionCompte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblGestionCompte.setBounds(80, 6, 51, 22);
		add(lblGestionCompte);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 190, 140);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		add(panel);
		
		JButton btnCrerUnCompte = new JButton("Cr\u00E9er un compte");
		btnCrerUnCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new CreerCompte(window));
			}
		});
		panel.add(btnCrerUnCompte);
		
		if(window.getCltGestPers().getPersConnectee().role == rolePersonne.RH) {
			JButton btnAjouterPhoto = new JButton("Ajouter photo");
			btnAjouterPhoto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					window.setPane(new AjouterPhoto(window));
				}
			});
			panel.add(btnAjouterPhoto);
		}
		
		JButton btnSupprimerEmpreinte = new JButton("Supprimer empreinte");
		btnSupprimerEmpreinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new SupprimerEmpreinte(window));
			}
		});
		
		JButton btnGrerLesComptes = new JButton("G\u00E9rer les comptes");
		btnGrerLesComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.setPane(new GererCompte(window));
			}
		});
		panel.add(btnGrerLesComptes);
		panel.add(btnSupprimerEmpreinte);
		
		JButton btnNewButton = new JButton("Deconnexion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new GPLogin(window));
			}
		});
		panel.add(btnNewButton);
	}
}
