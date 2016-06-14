package interfaces.empreintes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AjouterEmpreinte extends JPanel {
	private static final long serialVersionUID = 5003639343730043476L;
	private JTextField textFieldEmpreinte;
	private JLabel lblError;
	
	/**
	 * Create the panel.
	 */
	public AjouterEmpreinte(InterfaceEmpreinteSwing window) {
		setLayout(null);
		
		JLabel lblAjouterEmpreinte = new JLabel("Ajouter empreinte");
		lblAjouterEmpreinte.setBounds(65, 6, 169, 22);
		lblAjouterEmpreinte.setFont(new Font("Calibri", Font.BOLD, 18));
		add(lblAjouterEmpreinte);
		
		JLabel lblEmpreinte = new JLabel("Empreinte");
		lblEmpreinte.setBounds(10, 58, 63, 16);
		add(lblEmpreinte);
		
		textFieldEmpreinte = new JTextField();
		textFieldEmpreinte.setBounds(96, 56, 160, 20);
		add(textFieldEmpreinte);
		textFieldEmpreinte.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empreinte = textFieldEmpreinte.getText();
				if(empreinte.length() == 0)
					lblError.setText("L'empreinte est obligatoire.");
				else {
					window.getCltEmpreintes().ajouterEmpreinte(window.getCltEmpreintes().getUserConnecte(), empreinte);
					lblError.setText(window.getCltEmpreintes().getMessage());
				}
			}
		});
		btnAjouter.setBounds(87, 88, 90, 29);
		add(btnAjouter);
		
		lblError = new JLabel(" ");
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 70, 224, 28);
		add(lblError);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new EmpreinteMenu(window));
			}
		});
		btnRetour.setBounds(174, 88, 82, 29);
		add(btnRetour);

	}

}
