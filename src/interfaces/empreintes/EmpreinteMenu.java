package interfaces.empreintes;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EmpreinteMenu extends JPanel {
	private static final long serialVersionUID = -3529440159512198097L;

	/**
	 * Create the panel.
	 */
	public EmpreinteMenu(InterfaceEmpreinteSwing window) {
		
		JButton btnAjouterEmpreinte = new JButton("Ajouter Empreinte");
		btnAjouterEmpreinte.setBounds(10, 45, 121, 23);
		btnAjouterEmpreinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new AjouterEmpreinte(window));
			}
		});
		setLayout(null);
		add(btnAjouterEmpreinte);
		
		JButton btnNewButton = new JButton("Modifier empreinte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new ModifierEmpreinte(window));
			}
		});
		btnNewButton.setBounds(10, 74, 121, 23);
		add(btnNewButton);
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new EmpreintesLogin(window));
			}
		});
		btnDeconnexion.setBounds(10, 103, 121, 23);
		add(btnDeconnexion);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setBounds(10, 10, 45, 23);
		lblMenu.setFont(new Font("Calibri", Font.BOLD, 18));
		add(lblMenu);

	}

}
