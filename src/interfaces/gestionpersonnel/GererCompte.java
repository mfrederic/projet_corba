package interfaces.gestionpersonnel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.Personne;
import Gestion_acces.personne;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

public class GererCompte extends JPanel {
	private interfaceGestionPersonnelSwing window;
	
	private static final long serialVersionUID = 2485760557889406242L;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JComboBox<statutPersonne> comboBoxStatut;
	private JComboBox<rolePersonne> comboBoxRole;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private JLabel lblError;
	private JTable table;
	private personne[] listPersonnes;
	
	private Personne currentSelected;


	/**
	 * Create the panel.
	 */
	public GererCompte(interfaceGestionPersonnelSwing window) {
		setLayout(null);
		this.window = window;
		
		JLabel lblCrationCompte = new JLabel("Gestion compte");
		lblCrationCompte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblCrationCompte.setBounds(10, 11, 230, 26);
		add(lblCrationCompte);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.setPane(new GPMenu(window));
			}
		});
		btnRetour.setBounds(181, 14, 89, 23);
		add(btnRetour);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 272, 100, 14);
		add(lblNom);
		
		textFieldNom = new JTextField();
		lblNom.setLabelFor(textFieldNom);
		textFieldNom.setBounds(120, 269, 150, 20);
		add(textFieldNom);
		textFieldNom.setColumns(10);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(120, 297, 150, 20);
		add(textFieldPrenom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setLabelFor(textFieldPrenom);
		lblPrnom.setBounds(10, 300, 100, 14);
		add(lblPrnom);
		
		comboBoxStatut = new JComboBox<statutPersonne>();
		comboBoxStatut.addItem(statutPersonne.temporaire);
		comboBoxStatut.addItem(statutPersonne.permanent);
		comboBoxStatut.setBounds(120, 325, 150, 20);
		add(comboBoxStatut);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setLabelFor(comboBoxStatut);
		lblStatut.setBounds(10, 328, 100, 14);
		add(lblStatut);
		
		comboBoxRole = new JComboBox<rolePersonne>();
		comboBoxRole.addItem(rolePersonne.RH);
		comboBoxRole.addItem(rolePersonne.accueil);
		comboBoxRole.addItem(rolePersonne.basique);
		comboBoxRole.setBounds(120, 353, 150, 20);
		add(comboBoxRole);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setLabelFor(comboBoxRole);
		lblRole.setBounds(10, 356, 100, 14);
		add(lblRole);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setColumns(10);
		textFieldLogin.setBounds(120, 381, 150, 20);
		add(textFieldLogin);
		
		JLabel lblUser = new JLabel("Login");
		lblUser.setLabelFor(textFieldLogin);
		lblUser.setBounds(10, 384, 100, 14);
		add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 412, 100, 14);
		add(lblPassword);
		
		passwordField = new JPasswordField();
		lblPassword.setLabelFor(passwordField);
		passwordField.setBounds(120, 409, 150, 20);
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
		btnCrer.setBounds(181, 440, 89, 23);
		add(btnCrer);
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setBounds(10, 471, 260, 40);
		add(lblError);
		
		
		listPersonnes = null;
		try {
			listPersonnes = window.getCltGestPers().chercherPersonnes(new String(), new String());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		table = new JTable(new PersonneModel(listPersonnes));
		table.setAutoCreateRowSorter(true);
		table.setBounds(10, 48, 420, 210);
		add(table);
		
		ListSelectionModel listSelectionModel = table.getSelectionModel();        
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
		            return;
				
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        if (!lsm.isSelectionEmpty()) {
		            personne current = listPersonnes[lsm.getMinSelectionIndex()];
		            currentSelected = new Personne(current.idPers, current.nom, current.prenom, current.ph, current.statut.toString(), current.role.toString());
		            setFormValue();
		        }
			}
		});

	}
	
	public void setFormValue() {
		textFieldNom.setText(currentSelected.getNomPersonne());
		textFieldPrenom.setText(currentSelected.getPrenomPersonne());
	}
	
	public class PersonneModel extends AbstractTableModel {
		private static final long serialVersionUID = -3240581296997491550L;
		
		private Personne[] listePersonne;
		private final String[] listeColonnes = {"Id", "Nom", "Prenom", "Photo", "Statut", "Role"};

		public PersonneModel(Personne[] liste) {
			super();
			listePersonne = liste;
		}
		
		public PersonneModel(personne[] liste) {
			super();
			ArrayList<Personne> list = new ArrayList<Personne>();
			for(personne pers : liste) {
				list.add(new Personne(pers.idPers, pers.nom, pers.prenom, pers.ph, pers.statut.toString(), pers.role.toString()));
			}
			listePersonne = new Personne[list.size()];
			listePersonne = (Personne[]) list.toArray(listePersonne);
		}
		
		@Override
		public int getColumnCount() {
			return listeColonnes.length;
		}
		
		@Override
		public String getColumnName(int columnIndex) {
			return listeColonnes[columnIndex];
		}

		@Override
		public int getRowCount() {
			return listePersonne.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
				case 0:
					return listePersonne[row].getIdPersonne();
				case 1:
					return listePersonne[row].getNomPersonne();
				case 2:
					return listePersonne[row].getPrenomPersonne();
				case 3:
					return listePersonne[row].getPhotoPersonne();
				case 4:
					return listePersonne[row].getStatutPersonne();
				case 5:
					return listePersonne[row].getRolePersonne();
				default:
					return null;
			}
		}
		
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			switch (columnIndex) {
				case 0:
					return Integer.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				default:
					return Object.class;
			}
		}
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
			lblError.setText("<html>Le(s) champ(s) <br>" + errorFields + " <br>doivent etre renseignes.</html>");
			return false;
		}
		
		return true;
	}
}
