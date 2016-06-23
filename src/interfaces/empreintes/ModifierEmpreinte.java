package interfaces.empreintes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

public class ModifierEmpreinte extends JPanel {
	private static final long serialVersionUID = 5003639343730043476L;
	private JTextField textFieldEmpreinte;
	private JLabel lblError;
	
	/**
	 * Create the panel.
	 */
	public ModifierEmpreinte(InterfaceEmpreinteSwing window) {
		setLayout(null);
		
		JLabel lblAjouterEmpreinte = new JLabel("Modifier empreinte");
		lblAjouterEmpreinte.setBounds(10, 11, 178, 22);
		lblAjouterEmpreinte.setFont(new Font("Calibri", Font.BOLD, 18));
		add(lblAjouterEmpreinte);
		
		JLabel lblEmpreinte = new JLabel("Empreinte");
		lblEmpreinte.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEmpreinte.setBounds(10, 48, 86, 14);
		add(lblEmpreinte);
		
		textFieldEmpreinte = new JTextField();
		textFieldEmpreinte.setFont(new Font("Calibri", Font.PLAIN, 11));
		textFieldEmpreinte.setBounds(106, 45, 176, 20);
		add(textFieldEmpreinte);
		textFieldEmpreinte.setColumns(10);
		
		JButton btnAjouter = new JButton("Modifier");
		btnAjouter.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empreinte = textFieldEmpreinte.getText();
				if(empreinte.length() == 0)
					lblError.setText("L'empreinte est obligatoire.");
				else {
					try {
						window.getCltEmpreintes().modifierEmpreinte(window.getCltEmpreintes().getUserConnecte(), empreinte);
						lblError.setText(window.getCltEmpreintes().getMessage());
					} catch (droitsInsuffisants e1) {
						// TODO Auto-generated catch block
						lblError.setText(e1.raison);
					}
				}
			}
		});
		btnAjouter.setBounds(186, 76, 96, 22);
		add(btnAjouter);
		
		lblError = new JLabel(" ");
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 109, 272, 40);
		add(lblError);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new EmpreinteMenu(window));
				window.getFrame().setBounds(100, 100, 200, 200);
			}
		});
		btnRetour.setBounds(198, 12, 84, 22);
		add(btnRetour);

	}

}
