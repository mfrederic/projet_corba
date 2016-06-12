package interfaces.empreintes;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		lblAjouterEmpreinte.setBounds(10, 10, 139, 23);
		lblAjouterEmpreinte.setFont(new Font("Calibri", Font.BOLD, 18));
		add(lblAjouterEmpreinte);
		
		JLabel lblEmpreinte = new JLabel("Empreinte");
		lblEmpreinte.setBounds(10, 45, 86, 14);
		add(lblEmpreinte);
		
		textFieldEmpreinte = new JTextField();
		textFieldEmpreinte.setBounds(128, 42, 86, 20);
		add(textFieldEmpreinte);
		textFieldEmpreinte.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empreinte = textFieldEmpreinte.getText();
				if(empreinte.length() == 0)
					lblError.setText("L'empreinte est obligatoire.");
				else {
					window.cltEmpreintes.ajouterEmpreinte(window.cltEmpreintes.getUserConnecte(), empreinte);
					lblError.setText(window.cltEmpreintes.getMessage());
				}
			}
		});
		btnAjouter.setBounds(10, 109, 69, 23);
		add(btnAjouter);
		
		lblError = new JLabel(" ");
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 70, 224, 28);
		add(lblError);
		
		JButton btnRetour = new JButton("retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new EmpreinteMenu(window));
			}
		});
		btnRetour.setBounds(89, 109, 89, 23);
		add(btnRetour);

	}

}
