package interfaces.gestionpersonnel;

import interfaces.gestionpersonnel.InterfaceGestionPersonnelSwing.PersonneComboBox;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;

import Gestion_acces.personne;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.Personne;

import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import Gestion_acces.compte;
import Gestion_acces.statutPersonne;
import Gestion_acces.rolePersonne;

import javax.swing.JPasswordField;

public class GPGestion extends JPanel {
	private static final long serialVersionUID = -1241512579162739300L;
	private InterfaceGestionPersonnelSwing window;
	private Personne currentSelected;
	
	private JTable table;
	private JLabel lblError;
	private JTextField textFieldNomCreer;
	private JTextField textFieldPrenomCreer;
	private JTextField textFieldLoginCreer;
	private JPasswordField passwordFieldCreer;
	private JTabbedPane tabbedPane;
	private JTextField textFieldNom;
	private JTextField textFieldPrenom;
	private JTextField textFieldId;
	private JTextField textFieldPhoto;
	private JComboBox<rolePersonne> comboBoxRole;
	private JComboBox<statutPersonne> comboBoxStatut;
	private JComboBox<rolePersonne> comboBoxRoleCreer;
	private JComboBox<statutPersonne> comboBoxStatutCreer;
	private PersonneModel personneModel;

	/**
	 * Create the panel.
	 */
	public GPGestion(InterfaceGestionPersonnelSwing w) {
		setLayout(null);
		
		this.window = w;
		
		JLabel lblGestionDesComptes = new JLabel("Gestion des comptes");
		lblGestionDesComptes.setFont(new Font("Calibri", Font.BOLD, 18));
		lblGestionDesComptes.setBounds(10, 10, 200, 38);
		add(lblGestionDesComptes);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 46, 430, 2);
		add(separator);
		
		JButton btnDeconnexion = new JButton("Deconnexion");
		btnDeconnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				window.getFrame().setBounds(100, 100, 292, 215);
				window.setPane(new GPLogin(window));
			}
		});
		btnDeconnexion.setBounds(290, 19, 150, 23);
		add(btnDeconnexion);
		
		JScrollPane scrollPaneListPersonne = new JScrollPane();
		scrollPaneListPersonne.setBounds(10, 59, 430, 109);
		add(scrollPaneListPersonne);
		
		table = new JTable(new PersonneModel());
		scrollPaneListPersonne.setViewportView(table);
		table.setFont(new Font("Calibri", Font.PLAIN, 11));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneListPersonne.setViewportView(table);
		
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
		            return;
				
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        if (!lsm.isSelectionEmpty()) {
		        	tabbedPane.setSelectedIndex(1);
		            currentSelected = ((PersonneModel) table.getModel()).getPersonneAt(lsm.getMinSelectionIndex());
		            setFormValue();
		        } else {
		        	currentSelected = null;
		        }
			}
		});
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setBounds(10, 174, 430, 38);
		add(lblError);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 223, 428, 340);
		add(tabbedPane);
		tabbedPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		createTabCreer();
		createTabMaj();
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
	
	public void createTabMaj() {
		JPanel panelMaj = new JPanel();
		tabbedPane.addTab("MaJ", null, panelMaj, null);
		panelMaj.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 14, 100, 14);
		panelMaj.add(lblId);
		
		textFieldId = new JTextField();
		textFieldId.setEnabled(false);
		textFieldId.setEditable(false);
		textFieldId.setColumns(10);
		textFieldId.setBounds(120, 11, 150, 20);
		panelMaj.add(textFieldId);
		
		JLabel label_1 = new JLabel("Prenom");
		label_1.setBounds(10, 42, 100, 14);
		panelMaj.add(label_1);
		
		textFieldPrenom = new JTextField();
		textFieldPrenom.setColumns(10);
		textFieldPrenom.setBounds(120, 39, 150, 20);
		panelMaj.add(textFieldPrenom);
		
		JLabel label = new JLabel("Nom");
		label.setBounds(10, 70, 100, 14);
		panelMaj.add(label);
		
		textFieldNom = new JTextField();
		textFieldNom.setColumns(10);
		textFieldNom.setBounds(120, 67, 150, 20);
		panelMaj.add(textFieldNom);
		
		JLabel label_4 = new JLabel("Statut");
		label_4.setBounds(10, 98, 100, 14);
		panelMaj.add(label_4);
		
		comboBoxStatut = new JComboBox<statutPersonne>();
		comboBoxStatut.setBounds(120, 95, 150, 20);
		panelMaj.add(comboBoxStatut);
		comboBoxStatut.addItem(statutPersonne.temporaire);
		comboBoxStatut.addItem(statutPersonne.permanent);
		
		JLabel label_5 = new JLabel("Role");
		label_5.setBounds(10, 126, 100, 14);
		panelMaj.add(label_5);
		
		comboBoxRole = new JComboBox<rolePersonne>();
		comboBoxRole.setBounds(120, 123, 150, 20);
		panelMaj.add(comboBoxRole);
		comboBoxRole.addItem(rolePersonne.RH);
		comboBoxRole.addItem(rolePersonne.accueil);
		comboBoxRole.addItem(rolePersonne.basique);
		
		JLabel lblErrorUpdate = new JLabel("");
		lblErrorUpdate.setVerticalAlignment(SwingConstants.TOP);
		lblErrorUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrorUpdate.setForeground(Color.RED);
		lblErrorUpdate.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblErrorUpdate.setBounds(10, 259, 360, 80);
		panelMaj.add(lblErrorUpdate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!checkEmptyFieldsUpdate())
					return;
				lblErrorUpdate.setText("");
				
				try {
					window.getCltGestPers().modifierInfos(
							Short.valueOf(textFieldId.getText()),
							textFieldNom.getText(),
							textFieldPrenom.getText(),
							statutPersonne.from_int(comboBoxStatut.getSelectedIndex()),
							rolePersonne.from_int(comboBoxRole.getSelectedIndex())
					);
					table.setModel(new PersonneModel());
					lblErrorUpdate.setText(window.getCltGestPers().getMessage());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (droitsInsuffisants e1) {
					lblErrorUpdate.setText(e1.raison);
				}
			}
		});
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnUpdate.setBounds(181, 154, 89, 23);
		panelMaj.add(btnUpdate);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrorUpdate.setText("");
				try {
					window.getCltGestPers().supprimerPersonne(Short.valueOf(textFieldId.getText()));
					table.setModel(new PersonneModel());
					lblErrorUpdate.setText(window.getCltGestPers().getMessage());
				} catch (droitsInsuffisants e1) {
					lblErrorUpdate.setText(e1.raison);
				} catch (NumberFormatException e2) {
					lblErrorUpdate.setText("Aucun compte selectionne.");
				}
			}
		});
		btnSupprimer.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnSupprimer.setForeground(Color.RED);
		btnSupprimer.setBounds(82, 154, 89, 23);
		panelMaj.add(btnSupprimer);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblErrorUpdate.setText("");
				String photo = new String(textFieldPhoto.getText());
				try {
					window.getCltGestPers().ajouterPhoto(Short.valueOf(textFieldId.getText()), photo);
					table.setModel(new PersonneModel());
					lblErrorUpdate.setText(window.getCltGestPers().getMessage());
				} catch (NumberFormatException e1) {
					lblErrorUpdate.setText("Aucun compte selectionne.");
					e1.printStackTrace();
				} catch (droitsInsuffisants e1) {
					lblErrorUpdate.setText(e1.raison);
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblPhoto = new JLabel("Photo");
		lblPhoto.setBounds(10, 205, 100, 14);
		panelMaj.add(lblPhoto);
		
		textFieldPhoto = new JTextField();
		textFieldPhoto.setBounds(120, 202, 150, 20);
		panelMaj.add(textFieldPhoto);
		textFieldPhoto.setColumns(10);
		btnModifier.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnModifier.setBounds(280, 201, 89, 23);
		panelMaj.add(btnModifier);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 188, 401, 2);
		panelMaj.add(separator);
		
		JButton btnSupprimerEmpreinte = new JButton("Supprimer Empreinte");
		btnSupprimerEmpreinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					window.getCltGestPers().supprimerEmpreinte(Short.valueOf(textFieldId.getText()));
					table.setModel(new PersonneModel());
					lblErrorUpdate.setText(window.getCltGestPers().getMessage());
				} catch (droitsInsuffisants e1) {
					lblErrorUpdate.setText(e1.raison);
				}
			}
		});
		btnSupprimerEmpreinte.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnSupprimerEmpreinte.setBounds(219, 233, 150, 23);
		panelMaj.add(btnSupprimerEmpreinte);
	}
	
	public void createTabCreer() {
		JPanel panelCreer = new JPanel();
		tabbedPane.addTab("Creer", null, panelCreer, null);
		panelCreer.setLayout(null);
		
		JLabel labelNomCreer = new JLabel("Nom");
		labelNomCreer.setBounds(10, 14, 100, 14);
		panelCreer.add(labelNomCreer);
		
		textFieldNomCreer = new JTextField();
		textFieldNomCreer.setColumns(10);
		textFieldNomCreer.setBounds(120, 11, 150, 20);
		panelCreer.add(textFieldNomCreer);
		
		JLabel labelPrenomCreer = new JLabel("Pr\u00E9nom");
		labelPrenomCreer.setBounds(10, 42, 100, 14);
		panelCreer.add(labelPrenomCreer);
		
		textFieldPrenomCreer = new JTextField();
		textFieldPrenomCreer.setColumns(10);
		textFieldPrenomCreer.setBounds(120, 39, 150, 20);
		panelCreer.add(textFieldPrenomCreer);
		
		JLabel lblLoginCreer = new JLabel("Login");
		lblLoginCreer.setBounds(10, 70, 100, 14);
		panelCreer.add(lblLoginCreer);
		
		textFieldLoginCreer = new JTextField();
		textFieldLoginCreer.setColumns(10);
		textFieldLoginCreer.setBounds(120, 67, 150, 20);
		panelCreer.add(textFieldLoginCreer);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(10, 98, 100, 14);
		panelCreer.add(lblPassword);
		
		passwordFieldCreer = new JPasswordField();
		passwordFieldCreer.setBounds(120, 95, 150, 20);
		panelCreer.add(passwordFieldCreer);
		
		JLabel labelStatutCreer = new JLabel("Statut");
		labelStatutCreer.setBounds(10, 126, 100, 14);
		panelCreer.add(labelStatutCreer);
		
		comboBoxStatutCreer = new JComboBox<statutPersonne>();
		comboBoxStatutCreer.setBounds(120, 123, 150, 20);
		panelCreer.add(comboBoxStatutCreer);
		comboBoxStatutCreer.addItem(statutPersonne.temporaire);
		comboBoxStatutCreer.addItem(statutPersonne.permanent);
		
		JLabel labelRoleCreer = new JLabel("Role");
		labelRoleCreer.setBounds(10, 154, 100, 14);
		panelCreer.add(labelRoleCreer);
		
		comboBoxRoleCreer = new JComboBox<rolePersonne>();
		comboBoxRoleCreer.setBounds(120, 151, 150, 20);
		panelCreer.add(comboBoxRoleCreer);
		comboBoxRoleCreer.addItem(rolePersonne.RH);
		comboBoxRoleCreer.addItem(rolePersonne.accueil);
		comboBoxRoleCreer.addItem(rolePersonne.basique);
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkEmptyFieldsCreer())
					return;
				lblError.setText("");
				
				try {
					window.getCltGestPers().creerPersonne(
							textFieldNomCreer.getText(),
							textFieldPrenomCreer.getText(),
							statutPersonne.from_int(comboBoxStatutCreer.getSelectedIndex()),
							rolePersonne.from_int(comboBoxRoleCreer.getSelectedIndex()),
							textFieldLoginCreer.getText(),
							new String(passwordFieldCreer.getPassword())
					);
					table.setModel(new PersonneModel());
					lblError.setText(window.getCltGestPers().getMessage());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (droitsInsuffisants e1) {
					lblError.setText(e1.raison);
				}
			}
		});
		btnCreer.setBounds(181, 182, 89, 23);
		panelCreer.add(btnCreer);
		
		JLabel lblErrorCreer = new JLabel("");
		lblErrorCreer.setVerticalAlignment(SwingConstants.TOP);
		lblErrorCreer.setHorizontalAlignment(SwingConstants.LEFT);
		lblErrorCreer.setForeground(Color.RED);
		lblErrorCreer.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblErrorCreer.setBounds(10, 216, 360, 80);
		panelCreer.add(lblErrorCreer);
		
		
	}
	
	public boolean checkEmptyFieldsCreer() {
		String errorFields = "";
		if(textFieldNomCreer.getText().length() == 0)
			errorFields += "Nom, ";
		if(textFieldPrenomCreer.getText().length() == 0)
			errorFields += "Prenom, ";
		if(comboBoxStatutCreer.getSelectedItem() == null)
			errorFields += "Statut, ";
		if(comboBoxRoleCreer.getSelectedItem() == null)
			errorFields += "Role, ";
		if(textFieldLoginCreer.getText().length() == 0)
			errorFields += "login, ";
		if(passwordFieldCreer.getPassword().length == 0)
			errorFields += "Password, ";
		
		if(errorFields.length() > 0) {
			lblError.setText("<html>Le(s) champ(s) <br>" + errorFields + " <br>doivent etre renseignes.</html>");
			return false;
		}
		
		return true;
	}
	
	public boolean checkEmptyFieldsUpdate() {
		String errorFields = "";
		if(textFieldNom.getText().length() == 0)
			errorFields += "Nom, ";
		if(textFieldPrenom.getText().length() == 0)
			errorFields += "Prenom, ";
		
		if(errorFields.length() > 0) {
			lblError.setText("<html>Le(s) champ(s) <br>" + errorFields + " <br>doivent etre renseignes.</html>");
			return false;
		}
		
		return true;
	}
	
	public class PersonneModel extends AbstractTableModel {
		private static final long serialVersionUID = -3240581296997491550L;
		
		private Personne[] listePersonne;
		private compte[] listeCompte;
		private final String[] listeColonnes = {"Id", "Nom", "Prenom", "Photo", "Statut", "Role", "Empreinte"};


		public PersonneModel(Personne[] liste) {
			super();
			listePersonne = liste;
		}
		
		public Personne getPersonneAt(int minSelectionIndex) {
			if(listePersonne.length < minSelectionIndex)
				return listePersonne[listePersonne.length-1];
			return listePersonne[minSelectionIndex];
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
		
		public PersonneModel() {
			super();
			ArrayList<Personne> list = new ArrayList<Personne>();
			personne[] liste = null;
			try {
				liste = window.getCltGestPers().chercherPersonnes(new String(), new String());
				listeCompte = window.getCltGestPers().chercherComptes(new String(), new String());
				
			} catch (droitsInsuffisants e) {
				e.printStackTrace();
			}
			for(personne pers : liste) {
				list.add(new Personne(pers.idPers, pers.nom, pers.prenom, pers.ph, pers.statut.toString(), pers.role.toString()));
			}
			listePersonne = new Personne[list.size()];
			listePersonne = (Personne[]) list.toArray(listePersonne);
		}
		
		@Override
		public void fireTableDataChanged() {
			super.fireTableDataChanged();
		};
		
		public void updateAllContent() {
			ArrayList<Personne> list = new ArrayList<Personne>();
			personne[] liste = null;
			try {
				liste = window.getCltGestPers().chercherPersonnes(new String(), new String());
				listeCompte = window.getCltGestPers().chercherComptes(new String(), new String());
				
			} catch (droitsInsuffisants e) {
				e.printStackTrace();
			}
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
				case 6:
					compte current = getCompteFromPersonneId(listePersonne[row].getIdPersonne());
					if(current != null) {
						return (current.empreinte.equals("")) ? "Non" : "Oui";
					}
					return "?";
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
				case 6:
					return String.class;
				default:
					return Object.class;
			}
		}
		
		public compte getCompteFromPersonneId(int persId) {
			for(compte c : listeCompte) {
				if(c.refPersonne == (short) persId) {
					return c;
				}
			}
			return null;
		}
	}
}
