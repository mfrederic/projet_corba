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
import javax.swing.JScrollPane;
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
	private static final long serialVersionUID = 2485760557889406242L;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JComboBox<statutPersonne> comboBoxStatut;
	private JComboBox<rolePersonne> comboBoxRole;
	private JTextField textFieldId;
	private JLabel lblError;
	private JTable table;
	private personne[] listPersonnes;
	
	private Personne currentSelected;
	private JTextField textFieldPhoto;


	/**
	 * Create the panel.
	 */
	public GererCompte(interfaceGestionPersonnelSwing window) {
		setLayout(null);
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
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 48, 420, 130);
		add(scrollPane);

		listPersonnes = null;
		try {
			listPersonnes = window.getCltGestPers().chercherPersonnes(new String(), new String());
		} catch (droitsInsuffisants e2) {
			lblError.setText(e2.raison);
		}
		
		PersonneModel personneModel = new PersonneModel(listPersonnes);
		table = new JTable(personneModel);
		scrollPane.setViewportView(table);
		table.setFont(new Font("Calibri", Font.PLAIN, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setAutoCreateRowSorter(true);
		
		ListSelectionModel listSelectionModel = table.getSelectionModel();        
		
		JPanel updateAccountPanel = new JPanel();
		updateAccountPanel.setBounds(10, 189, 285, 262);
		add(updateAccountPanel);
		updateAccountPanel.setLayout(null);
		updateAccountPanel.setVisible(false);
		
		JLabel lblUser = new JLabel("Id");
		lblUser.setBounds(10, 14, 100, 14);
		updateAccountPanel.add(lblUser);
		
		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setEditable(false);
		lblUser.setLabelFor(textFieldId);
		textFieldId.setBounds(120, 11, 150, 20);
		updateAccountPanel.add(textFieldId);
		textFieldId.setColumns(10);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(10, 42, 100, 14);
		updateAccountPanel.add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setBounds(120, 39, 150, 20);
		updateAccountPanel.add(textFieldNom);
		textFieldNom.setColumns(10);
		lblNom.setLabelFor(textFieldNom);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setBounds(10, 70, 100, 14);
		updateAccountPanel.add(lblPrnom);
		lblPrnom.setLabelFor(textFieldPrenom);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setBounds(120, 67, 150, 20);
		updateAccountPanel.add(textFieldPrenom);
		textFieldPrenom.setColumns(10);
		
		JLabel lblStatut = new JLabel("Statut");
		lblStatut.setBounds(10, 98, 100, 14);
		updateAccountPanel.add(lblStatut);
		lblStatut.setLabelFor(comboBoxStatut);
		
		comboBoxStatut = new JComboBox<statutPersonne>();
		comboBoxStatut.setBounds(120, 95, 150, 20);
		updateAccountPanel.add(comboBoxStatut);
		comboBoxStatut.addItem(statutPersonne.temporaire);
		comboBoxStatut.addItem(statutPersonne.permanent);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(10, 126, 100, 14);
		updateAccountPanel.add(lblRole);
		lblRole.setLabelFor(comboBoxRole);
		
		comboBoxRole = new JComboBox<rolePersonne>();
		comboBoxRole.setBounds(120, 123, 150, 20);
		updateAccountPanel.add(comboBoxRole);
		comboBoxRole.addItem(rolePersonne.RH);
		comboBoxRole.addItem(rolePersonne.accueil);
		comboBoxRole.addItem(rolePersonne.basique);
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setBounds(10, 154, 100, 14);
		updateAccountPanel.add(lblPhoto);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(181, 182, 89, 23);
		updateAccountPanel.add(btnModifier);
		
		lblError = new JLabel("");
		lblError.setBounds(10, 213, 260, 40);
		updateAccountPanel.add(lblError);
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		
		textFieldPhoto = new JTextField();
		textFieldPhoto.setEnabled(false);
		textFieldPhoto.setEditable(false);
		lblPhoto.setLabelFor(textFieldPhoto);
		textFieldPhoto.setBounds(120, 151, 150, 20);
		updateAccountPanel.add(textFieldPhoto);
		textFieldPhoto.setColumns(10);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkEmptyFields())
					return;
				lblError.setText("");
				
				try {
					window.getCltGestPers().modifierInfos(Short.valueOf(textFieldId.getText()),
							textFieldNom.getText(),
							textFieldPrenom.getText(),
							statutPersonne.from_int(comboBoxStatut.getSelectedIndex()),
							rolePersonne.from_int(comboBoxRole.getSelectedIndex()));
					lblError.setText(window.getCltGestPers().getMessage());
					
					personneModel.updateModel(window.getCltGestPers().chercherPersonnes(new String(), new String()));
					personneModel.fireTableDataChanged();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (droitsInsuffisants e1) {
					lblError.setText(e1.raison);
				}
			}
		});
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
		    		updateAccountPanel.setVisible(true);
		        } else {
		    		updateAccountPanel.setVisible(false);
		        }
			}
		});

	}
	
	public void setFormValue() {
		textFieldNom.setText(currentSelected.getNomPersonne());
		textFieldPrenom.setText(currentSelected.getPrenomPersonne());
		textFieldId.setText(String.valueOf(currentSelected.getIdPersonne()));
		textFieldPhoto.setText(currentSelected.getPhotoPersonne());

		if(currentSelected.getRolePersonne().equals(rolePersonne.RH.toString()))
			comboBoxRole.setSelectedItem(rolePersonne.RH);
		else if(currentSelected.getRolePersonne().equals(rolePersonne.accueil.toString()))
			comboBoxRole.setSelectedItem(rolePersonne.accueil);
		else if(currentSelected.getRolePersonne().equals(rolePersonne.basique.toString()))
			comboBoxRole.setSelectedItem(rolePersonne.basique);

		if(currentSelected.getStatutPersonne().equals(statutPersonne.permanent.toString()))
			comboBoxStatut.setSelectedItem(statutPersonne.permanent);
		else if(currentSelected.getStatutPersonne().equals(statutPersonne.temporaire.toString()))
			comboBoxStatut.setSelectedItem(statutPersonne.temporaire);
	}
	
	public class PersonneModel extends AbstractTableModel {
		private static final long serialVersionUID = -3240581296997491550L;
		
		private Personne[] listePersonne;
		private final String[] listeColonnes = {"Id", "Nom", "Prenom", "Photo", "Statut", "Role"};

		public PersonneModel(Personne[] liste) {
			super();
			listePersonne = liste;
		}
		
		public void updateModel(personne[] chercherPersonnes) {
			ArrayList<Personne> list = new ArrayList<Personne>();
			for(personne pers : chercherPersonnes) {
				list.add(new Personne(pers.idPers, pers.nom, pers.prenom, pers.ph, pers.statut.toString(), pers.role.toString()));
			}
			listePersonne = new Personne[list.size()];
			listePersonne = (Personne[]) list.toArray(listePersonne);
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
		if(textFieldId.getText().length() == 0)
			errorFields += "login, ";
		
		if(errorFields.length() > 0) {
			lblError.setText("<html>Le(s) champ(s) <br>" + errorFields + " <br>doivent etre renseignes.</html>");
			return false;
		}
		
		return true;
	}
}
