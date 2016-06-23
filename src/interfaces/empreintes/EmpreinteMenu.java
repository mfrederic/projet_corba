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
		
		JButton btnAjouterEmpreinte = new JButton("Ajouter empreinte");
		btnAjouterEmpreinte.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAjouterEmpreinte.setBounds(10, 44, 163, 29);
		btnAjouterEmpreinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new AjouterEmpreinte(window));
				window.getFrame().setBounds(100, 100, 280, 165);
			}
		});
		setLayout(null);
		add(btnAjouterEmpreinte);
		
		JButton btnNewButton = new JButton("Modifier empreinte");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new ModifierEmpreinte(window));
				window.getFrame().setBounds(100, 100, 305, 175);
			}
		});
		btnNewButton.setBounds(10, 84, 163, 29);
		add(btnNewButton);
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new EmpreintesLogin(window));
				window.getFrame().setBounds(100, 100, 315, 205);
			}
		});
		btnDeconnexion.setBounds(10, 124, 163, 29);
		add(btnDeconnexion);
		
		JLabel lblMenu = new JLabel("Menu empreinte");
		lblMenu.setBounds(10, 11, 152, 22);
		lblMenu.setFont(new Font("Calibri", Font.BOLD, 18));
		add(lblMenu);

	}

}
