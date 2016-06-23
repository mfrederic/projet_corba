package interfaces.responsablezone;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.MalformedParametersException;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.Autorisation;
import model.Personne;
import Gestion_acces.autorisation;
import Gestion_acces.personne;
import Gestion_acces.structPlage;
import Gestion_acces.AnnuairePackage.personneInexistante;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import Gestion_acces.ServeurAutorisationPackage.plageIncoherente;

public class RespZoneGestion extends JPanel {
	private static final long serialVersionUID = -1241512579162739300L;
	private InterfaceRespZoneSwing window;
	private Autorisation currentSelected;
	private JTable tableGrants;
	private JTextField textFieldJourDebutCreate;
	private JTextField textFieldJourFinCreate;
	private JTextField textFieldTempsDebutCreate;
	private JTextField textFieldTempsFinCreate;
	private JComboBox<Short> comboBoxZoneCreate;
	private JLabel lblError;
	private JComboBox<PersonneComboBox> comboBoxPersonneCreate;
	private JTextField textFieldJourDebutUpdate;
	private JTextField textFieldJourFinUpdate;
	private JTextField textFieldTempsDebutUpdate;
	private JTextField textFieldTempsFinUpdate;
	private JComboBox<Short> comboBoxZoneUpdate;
	private JTextField textFieldUserUpdate;
	private JButton btnMaj;
	private JButton btnSuppr;

	/**
	 * Create the panel.
	 */
	public RespZoneGestion(InterfaceRespZoneSwing w) {
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
				window.setPane(new RespZoneLogin(window));
			}
		});
		btnDeconnexion.setBounds(290, 19, 150, 23);
		add(btnDeconnexion);
		
		JLabel lblUtilisateurs = new JLabel("Zones");
		lblUtilisateurs.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblUtilisateurs.setBounds(20, 59, 420, 14);
		add(lblUtilisateurs);
		
		JComboBox<Short> comboBoxZone = new JComboBox<Short>(new ZoneCBModel());
		comboBoxZone.setSelectedIndex(0);
		comboBoxZone.setBounds(10, 84, 86, 20);
		comboBoxZone.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(comboBoxZone.getSelectedItem() != null) {
					short[] currentZone = {(short) comboBoxZone.getSelectedItem()};
					tableGrants.setModel(new AutorisationModel(currentZone));
				}
			}
		});
		add(comboBoxZone);


		short[] currentZone = {comboBoxZone.getItemAt(0)};
		tableGrants = new JTable(new AutorisationModel(currentZone));
		ListSelectionModel listSelectionModel = tableGrants.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
		            return;
				
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        if (!lsm.isSelectionEmpty()) {
		            currentSelected = ((AutorisationModel) tableGrants.getModel()).getAutorisationAt(lsm.getMinSelectionIndex());
		            updateFormFields();
		        	btnMaj.setEnabled(true);
		        	btnSuppr.setEnabled(true);
		        } else {
		        	currentSelected = null;
		        	btnMaj.setEnabled(false);
		        	btnSuppr.setEnabled(false);
		        }
			}
		});
		
		JLabel lblAutorisations = new JLabel("Autorisations");
		lblAutorisations.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAutorisations.setBounds(20, 115, 420, 14);
		add(lblAutorisations);
		
		JScrollPane scrollPaneListGrant = new JScrollPane();
		scrollPaneListGrant.setBounds(10, 140, 430, 109);
		add(scrollPaneListGrant);
		scrollPaneListGrant.setViewportView(tableGrants);
		
		this.addCreatePanel();
		this.addUpdatePanel();
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 532, 430, 54);
		add(lblError);
	}
	
	private void addCreatePanel() {
		JPanel panelAddGrant = new JPanel();
		panelAddGrant.setBounds(10, 260, 200, 261);
		add(panelAddGrant);
		panelAddGrant.setLayout(null);
		
		JLabel lblAjouterUneAutorisation = new JLabel("Ajouter une autorisation");
		lblAjouterUneAutorisation.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAjouterUneAutorisation.setBounds(10, 11, 180, 14);
		panelAddGrant.add(lblAjouterUneAutorisation);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 34, 180, 2);
		panelAddGrant.add(separator_1);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblUser.setBounds(10, 47, 84, 14);
		panelAddGrant.add(lblUser);
		
		comboBoxPersonneCreate = new JComboBox<PersonneComboBox>(new PersonneCBModel());
		comboBoxPersonneCreate.setEditable(true);
		comboBoxPersonneCreate.setBounds(104, 43, 86, 20);
		panelAddGrant.add(comboBoxPersonneCreate);
		
		JLabel lblZone = new JLabel("Zone");
		lblZone.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblZone.setBounds(10, 79, 84, 14);
		panelAddGrant.add(lblZone);
		
		comboBoxZoneCreate = new JComboBox<Short>(new ZoneCBModel());
		comboBoxZoneCreate.setEditable(true);
		comboBoxZoneCreate.setSelectedIndex(0);
		lblZone.setLabelFor(comboBoxZoneCreate);
		comboBoxZoneCreate.setBounds(104, 75, 86, 20);
		panelAddGrant.add(comboBoxZoneCreate);
		
		JLabel labelJourDebut = new JLabel("Jour debut");
		labelJourDebut.setLabelFor(textFieldJourDebutCreate);
		labelJourDebut.setFont(new Font("Calibri", Font.PLAIN, 11));
		labelJourDebut.setBounds(10, 109, 84, 14);
		panelAddGrant.add(labelJourDebut);
		
		textFieldJourDebutCreate = new JTextField();
		textFieldJourDebutCreate.setBounds(104, 106, 86, 20);
		panelAddGrant.add(textFieldJourDebutCreate);
		textFieldJourDebutCreate.setColumns(10);
		
		JLabel lblJourFin = new JLabel("Jour fin");
		lblJourFin.setLabelFor(textFieldJourFinCreate);
		lblJourFin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblJourFin.setBounds(10, 140, 84, 14);
		panelAddGrant.add(lblJourFin);
		
		textFieldJourFinCreate = new JTextField();
		textFieldJourFinCreate.setColumns(10);
		textFieldJourFinCreate.setBounds(104, 137, 86, 20);
		panelAddGrant.add(textFieldJourFinCreate);
		
		JLabel lblHeureDebut = new JLabel("Heure debut");
		lblHeureDebut.setLabelFor(textFieldTempsDebutCreate);
		lblHeureDebut.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHeureDebut.setBounds(10, 171, 84, 14);
		panelAddGrant.add(lblHeureDebut);
		
		textFieldTempsDebutCreate = new JTextField();
		textFieldTempsDebutCreate.setColumns(10);
		textFieldTempsDebutCreate.setBounds(104, 168, 86, 20);
		panelAddGrant.add(textFieldTempsDebutCreate);
		
		JLabel lblHeureFin = new JLabel("Heure fin");
		lblHeureFin.setLabelFor(textFieldTempsFinCreate);
		lblHeureFin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHeureFin.setBounds(10, 202, 84, 14);
		panelAddGrant.add(lblHeureFin);
		
		textFieldTempsFinCreate = new JTextField();
		textFieldTempsFinCreate.setColumns(10);
		textFieldTempsFinCreate.setBounds(104, 199, 86, 20);
		panelAddGrant.add(textFieldTempsFinCreate);
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(textFieldJourDebutCreate.getText().length() != 0 || textFieldJourFinCreate.getText().length() != 0) {
						if(!MatcherDate.testDateIsValid(textFieldJourDebutCreate.getText()) ||
							!MatcherDate.testDateIsValid(textFieldJourFinCreate.getText())) {
							lblError.setText("Le(s) jour(s) renseignes doivent etre au format 'dd/mm/yy'.");
							return;
						}
					}
					
					lblError.setText("");
					PersonneComboBox selected = (PersonneComboBox) comboBoxPersonneCreate.getSelectedItem();
					Float heureDebut = (textFieldTempsDebutCreate.getText().length() == 0) ? 0 : Float.valueOf(textFieldTempsDebutCreate.getText());
					Float heureFin = (textFieldTempsFinCreate.getText().length() == 0) ? 0 : Float.valueOf(textFieldTempsFinCreate.getText());
					
					structPlage plage = new structPlage(
							textFieldJourDebutCreate.getText(),
							textFieldJourFinCreate.getText(),
							heureDebut,
							heureFin
							);
					
					try {
						window.getInterfaceRespZone().ajouterAutorisation(
								(short) selected.getIdPersonne(),
								(short) comboBoxZoneCreate.getSelectedItem(),
								plage);
						
						lblError.setText(window.getInterfaceRespZone().getMessage());
						short[] currentZone = {(short) comboBoxZoneCreate.getSelectedItem()};
						tableGrants.setModel(new AutorisationModel(currentZone));
					} catch (droitsInsuffisants e) {
						lblError.setText(e.raison);
					} catch (personneInexistante e) {
						lblError.setText(e.getMessage());
					}
				} catch (MalformedParametersException e) {
					lblError.setText(e.getMessage());
				}
			}
		});
		btnCreer.setBounds(101, 230, 89, 23);
		panelAddGrant.add(btnCreer);
	}
	
	private void addUpdatePanel() {
		JPanel panelUpdateGrant = new JPanel();
		panelUpdateGrant.setBounds(240, 260, 200, 261);
		add(panelUpdateGrant);
		panelUpdateGrant.setLayout(null);
		
		JLabel lblModifierUneAutorisation = new JLabel("Modifier une autorisation");
		lblModifierUneAutorisation.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblModifierUneAutorisation.setBounds(10, 11, 180, 14);
		panelUpdateGrant.add(lblModifierUneAutorisation);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 31, 180, 2);
		panelUpdateGrant.add(separator_2);
		
		JLabel label = new JLabel("User");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setBounds(10, 44, 84, 14);
		panelUpdateGrant.add(label);
		
		textFieldUserUpdate = new JTextField();
		textFieldUserUpdate.setEnabled(false);
		textFieldUserUpdate.setEditable(false);
		textFieldUserUpdate.setBounds(104, 40, 86, 20);
		panelUpdateGrant.add(textFieldUserUpdate);
		textFieldUserUpdate.setColumns(10);
		
		JLabel label_1 = new JLabel("Zone");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_1.setBounds(10, 76, 84, 14);
		panelUpdateGrant.add(label_1);
		
		comboBoxZoneUpdate = new JComboBox<Short>(new ZoneCBModel());
		comboBoxZoneUpdate.setEnabled(false);
		comboBoxZoneUpdate.setSelectedIndex(0);
		comboBoxZoneUpdate.setBounds(104, 72, 86, 20);
		panelUpdateGrant.add(comboBoxZoneUpdate);
		
		JLabel label_2 = new JLabel("Jour debut");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setBounds(10, 106, 84, 14);
		panelUpdateGrant.add(label_2);
		
		textFieldJourDebutUpdate = new JTextField();
		textFieldJourDebutUpdate.setColumns(10);
		textFieldJourDebutUpdate.setBounds(104, 103, 86, 20);
		panelUpdateGrant.add(textFieldJourDebutUpdate);
		
		JLabel label_3 = new JLabel("Jour fin");
		label_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_3.setBounds(10, 137, 84, 14);
		panelUpdateGrant.add(label_3);
		
		textFieldJourFinUpdate = new JTextField();
		textFieldJourFinUpdate.setColumns(10);
		textFieldJourFinUpdate.setBounds(104, 134, 86, 20);
		panelUpdateGrant.add(textFieldJourFinUpdate);
		
		JLabel label_4 = new JLabel("Heure debut");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setBounds(10, 168, 84, 14);
		panelUpdateGrant.add(label_4);
		
		textFieldTempsDebutUpdate = new JTextField();
		textFieldTempsDebutUpdate.setColumns(10);
		textFieldTempsDebutUpdate.setBounds(104, 165, 86, 20);
		panelUpdateGrant.add(textFieldTempsDebutUpdate);
		
		JLabel label_5 = new JLabel("Heure fin");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_5.setBounds(10, 199, 84, 14);
		panelUpdateGrant.add(label_5);
		
		textFieldTempsFinUpdate = new JTextField();
		textFieldTempsFinUpdate.setColumns(10);
		textFieldTempsFinUpdate.setBounds(104, 196, 86, 20);
		panelUpdateGrant.add(textFieldTempsFinUpdate);
		
		btnMaj = new JButton("MaJ");
		btnMaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldJourDebutUpdate.getText().length() != 0 || textFieldJourFinUpdate.getText().length() != 0) {
					if(!MatcherDate.testDateIsValid(textFieldJourDebutUpdate.getText()) ||
						!MatcherDate.testDateIsValid(textFieldJourFinUpdate.getText())) {
						lblError.setText("Le(s) jour(s) renseignes doivent etre au format 'dd/mm/yy'.");
						return;
					}
				}
				
				lblError.setText("");
				Float heureDebut = (textFieldTempsDebutUpdate.getText().length() == 0) ? 0 : Float.valueOf(textFieldTempsDebutUpdate.getText());
				Float heureFin = (textFieldTempsFinUpdate.getText().length() == 0) ? 0 : Float.valueOf(textFieldTempsFinUpdate.getText());
				
				structPlage plage = new structPlage(
						textFieldJourDebutUpdate.getText(),
						textFieldJourFinUpdate.getText(),
						heureDebut,
						heureFin
						);
				
				try {
					try {
						window.getInterfaceRespZone().modifierAutorisation(
								(short) currentSelected.getNumAuto(),
								plage);
						lblError.setText("Autorisation modifiee.");
					} catch (plageIncoherente e1) {
						lblError.setText(e1.raison);
					}
					
					lblError.setText(window.getInterfaceRespZone().getMessage());
					short[] currentZone = {(short) comboBoxZoneCreate.getSelectedItem()};
					tableGrants.setModel(new AutorisationModel(currentZone));
				} catch (droitsInsuffisants e1) {
					lblError.setText(e1.raison);
				}
			}
		});
		btnMaj.setBounds(101, 227, 89, 23);
		btnMaj.setEnabled(false);
		panelUpdateGrant.add(btnMaj);
		
		btnSuppr = new JButton("Suppr");
		btnSuppr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					window.getInterfaceRespZone().supprimerAutorisation((short) currentSelected.getNumAuto());
					lblError.setText("Autorisation supprime.");
					short[] currentZone = {(short) comboBoxZoneCreate.getSelectedItem()};
					tableGrants.setModel(new AutorisationModel(currentZone));
				} catch (droitsInsuffisants e) {
					lblError.setText(e.raison);
				}
			}
		});
		btnSuppr.setEnabled(false);
		btnSuppr.setBounds(10, 227, 69, 23);
		panelUpdateGrant.add(btnSuppr);
	}
	
	public void updateFormFields() {
		textFieldUserUpdate.setText(String.valueOf(currentSelected.getRefPersonne()));
		comboBoxZoneUpdate.setSelectedItem((short) currentSelected.getRefZone());
		textFieldJourDebutUpdate.setText(currentSelected.getJourDebut());
		textFieldJourFinUpdate.setText(currentSelected.getJourFin());
		textFieldTempsDebutUpdate.setText(String.valueOf(currentSelected.getHeureDebut()));
		textFieldTempsFinUpdate.setText(String.valueOf(currentSelected.getHeureFin()));
	}
	
	public class ZoneCBModel extends AbstractListModel<Short> implements ComboBoxModel<Short> {
		private static final long serialVersionUID = 2215403230482150481L;
		
		short[] zoneIds;
		short selection;
		
		public ZoneCBModel() {
			this.zoneIds = window.getInterfaceRespZone().recupZoneAutorisation();
		}
		
		@Override
		public Short getElementAt(int index) {
			return this.zoneIds[index];
		}

		@Override
		public int getSize() {
			return this.zoneIds.length;
		}

		@Override
		public Short getSelectedItem() {
			return selection;
		}

		@Override
		public void setSelectedItem(Object anItem) {
			selection = (short) anItem;
		}
		
	}
	
	public static class PersonneComboBox extends Personne {
		public static ArrayList<Personne> listPersonnes = null;
		
		public PersonneComboBox(Personne p) {
			super(p.getIdPersonne(), p.getNomPersonne(), p.getPrenomPersonne(), p.getPhotoPersonne(), p.getStatutPersonne(), p.getRolePersonne());
		}
		
		public String toString() {
			return this.getNomPersonne() + " " + this.getPrenomPersonne();
		}
		
		public static Personne getByNomPrenom(String nomPrenom) {
			for(Personne p : listPersonnes) {
				if((p.getNomPersonne() + " " + p.getPrenomPersonne()).equals(nomPrenom))
					return p;
			}
			return null;
		}
		
		public static Object[] getInstances(personne[] list) {
			if(list == null)
				return null;
			
			listPersonnes = new ArrayList<Personne>();
			ArrayList<PersonneComboBox> p = new ArrayList<PersonneComboBox>();
			
			for(personne pers : list) {
				Personne current = new Personne(pers.idPers, pers.nom, pers.prenom, pers.ph, pers.statut.toString(), pers.role.toString());
				listPersonnes.add(current);
				p.add(new PersonneComboBox(current));
			}
			
			return (Object[]) p.toArray();
		}
		
		public static Object[] getInstances(ArrayList<Personne> list) {
			listPersonnes = new ArrayList<Personne>(list);
			ArrayList<PersonneComboBox> p = new ArrayList<PersonneComboBox>();
			
			for(Personne pers : list) {
				p.add(new PersonneComboBox(pers));
			}
			
			return (Object[]) p.toArray();
		}
	}
	
	public class PersonneCBModel extends AbstractListModel<PersonneComboBox> implements ComboBoxModel<PersonneComboBox> {
		private static final long serialVersionUID = 2215403230482150481L;
		
		PersonneComboBox[] listePersonne;
		PersonneComboBox selection;
		
		public PersonneCBModel() {
			ArrayList<Personne> list = new ArrayList<Personne>();
			personne[] liste = null;
			try {
				liste = window.getInterfaceRespZone().chercherPersonnes(new String(), new String());
			} catch (droitsInsuffisants e) {
				e.printStackTrace();
			}
			for(personne pers : liste) {
				list.add(
						new PersonneComboBox(new Personne(pers.idPers, pers.nom, pers.prenom, pers.ph, pers.statut.toString(), pers.role.toString()))
						);
			}
			listePersonne = new PersonneComboBox[list.size()];
			listePersonne = list.toArray(listePersonne);
		}
		
		@Override
		public PersonneComboBox getElementAt(int index) {
			return this.listePersonne[index];
		}

		@Override
		public int getSize() {
			return this.listePersonne.length;
		}

		@Override
		public Personne getSelectedItem() {
			return selection;
		}

		@Override
		public void setSelectedItem(Object anItem) {
			selection = (PersonneComboBox) anItem;
		}
		
	}
	
	public class AutorisationModel extends AbstractTableModel {
		private static final long serialVersionUID = -3240581296997491550L;
		
		private Autorisation[] listeAutorisations;
		private final String[] listeColonnes = {"Id", "Ref Pers", "Jour D", "Jour F", "Heure D", "Heure F"};
		
		public Autorisation getAutorisationAt(int minSelectionIndex) {
			if(listeAutorisations.length < minSelectionIndex)
				return listeAutorisations[listeAutorisations.length-1];
			return listeAutorisations[minSelectionIndex];
		}

		public AutorisationModel(short[] z) {
			super();
			ArrayList<Autorisation> list = new ArrayList<Autorisation>();
			autorisation[] liste = window.getInterfaceRespZone().getMonAutorisation().getMonAutorisation().getAutorisationsResp(z);
			for(autorisation a : liste) {
				Autorisation current = new Autorisation(a.refPers, a.sP.heureDeb, a.sP.heureFin, a.sP.jourDeb, a.sP.jourFin, a.refZone);
				current.setNumAuto(a.numAuto);
				list.add(current);
			}
			listeAutorisations = new Autorisation[list.size()];
			listeAutorisations = list.toArray(listeAutorisations);
		}

		@Override
		public void fireTableDataChanged() {
			super.fireTableDataChanged();
		};
		
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
			return listeAutorisations.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
				case 0:
					return listeAutorisations[row].getNumAuto();
				case 1:
					return listeAutorisations[row].getRefPersonne();
				case 2:
					return listeAutorisations[row].getJourDebut();
				case 3:
					return listeAutorisations[row].getJourFin();
				case 4:
					return listeAutorisations[row].getHeureDebut();
				case 5:
					return listeAutorisations[row].getHeureFin();
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
					return Integer.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				case 4:
					return Float.class;
				case 5:
					return Float.class;
				default:
					return Object.class;
			}
		}
	}
}
