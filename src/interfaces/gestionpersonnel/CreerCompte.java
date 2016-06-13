package interfaces.gestionpersonnel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListDataListener;

import model.Porte;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

public class CreerCompte extends JPanel {
	private interfaceGestionPersonnelSwing window;
	
	private static final long serialVersionUID = 2485760557889406242L;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JComboBox<statutPersonne> comboBoxStatut;
	private JComboBox<rolePersonne> comboBoxRole;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JLabel lblError;

	/**
	 * Create the panel.
	 */
	public CreerCompte(interfaceGestionPersonnelSwing window) {
		setLayout(null);
		this.window = window;
		
		JLabel lblCrationCompte = new JLabel("Cr\u00E9ation compte");
		lblCrationCompte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCrationCompte.setBounds(10, 11, 230, 26);
		add(lblCrationCompte);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 51, 100, 14);
		add(lblNom);
		
		textFieldNom = new JTextField();
		lblNom.setLabelFor(textFieldNom);
		textFieldNom.setBounds(120, 48, 150, 20);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(120, 76, 150, 20);
		add(textFieldPrenom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setLabelFor(textFieldPrenom);
		lblPrnom.setBounds(10, 79, 100, 14);
		add(lblPrnom);
		
		comboBoxStatut = new JComboBox<statutPersonne>();
		comboBoxStatut.addItem(statutPersonne.temporaire);
		comboBoxStatut.addItem(statutPersonne.permanent);
		comboBoxStatut.setBounds(120, 104, 150, 20);
		add(comboBoxStatut);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setLabelFor(comboBoxStatut);
		lblStatut.setBounds(10, 107, 100, 14);
		add(lblStatut);
		
		comboBoxRole = new JComboBox<rolePersonne>();
		comboBoxRole.addItem(rolePersonne.RH);
		comboBoxRole.addItem(rolePersonne.accueil);
		comboBoxRole.addItem(rolePersonne.basique);
		comboBoxRole.setBounds(120, 132, 150, 20);
		add(comboBoxRole);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setLabelFor(comboBoxRole);
		lblRole.setBounds(10, 135, 100, 14);
		add(lblRole);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		textFieldLogin.setBounds(120, 160, 150, 20);
		add(textFieldLogin);
		
		JLabel lblUser = new JLabel("Login");
		lblUser.setLabelFor(textFieldLogin);
		lblUser.setBounds(10, 163, 100, 14);
		add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 191, 100, 14);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		lblPassword.setLabelFor(passwordField);
		passwordField.setBounds(120, 188, 150, 20);
		add(passwordField);
		
		JButton btnCrer = new JButton("Cr\u00E9er");
		btnCrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkEmptyFields())
					return;
				lblError.setText("");
				
				try {
					window.getCltGestPers().creerCompte(
							textFieldNom.getText(),
							textFieldPrenom.getText(),
							statutPersonne.from_int(comboBoxStatut.getSelectedIndex()),
							rolePersonne.from_int(comboBoxRole.getSelectedIndex()),
							textFieldLogin.getText(),
							new String(passwordField.getPassword())
					);
					lblError.setText(window.getCltGestPers().getMessage());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (droitsInsuffisants e1) {
					lblError.setText(e1.raison);
				}
			}
		});
		btnCrer.setBounds(181, 219, 89, 23);
		add(btnCrer);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new GPMenu(window));
			}
		});
		btnRetour.setBounds(181, 14, 89, 23);
		add(btnRetour);
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setBounds(10, 250, 260, 40);
		add(lblError);

	}
	
	public boolean checkEmptyFields() {
		String errorFields = "";
		if(textFieldNom.getText().length() == 0)
			errorFields += "Nom, ";
		if(textFieldPrenom.getText().length() == 0)
			errorFields += "Prenom, ";
		if(comboBoxStatut.getSelectedItem() == null)
			errorFields += "Statut, ";
		if(comboBoxRole.getSelectedItem() == null)
			errorFields += "Role, ";
		if(textFieldLogin.getText().length() == 0)
			errorFields += "login, ";
		if(passwordField.getPassword().length == 0)
			errorFields += "Password, ";
		
		if(errorFields.length() > 0) {
			lblError.setText("<html>Le(s) champ(s) <br>" + errorFields + " <br>doivent �tre renseign�s.</html>");
			return false;
		}
		
		return true;
	}
	
}
