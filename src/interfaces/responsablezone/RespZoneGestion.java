package interfaces.responsablezone;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import model.Autorisation;
import model.Personne;
import model.Zone;
import Gestion_acces.autorisation;
import Gestion_acces.personne;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

import java.awt.Color;

import javax.swing.SwingConstants;

public class RespZoneGestion extends JPanel {
	private static final long serialVersionUID = -1241512579162739300L;
	private InterfaceRespZoneSwing window;
	private Personne currentSelected;
	private JTable tableGrants;
	private JTextField textFieldUser;
	private JTextField textFieldJourDebut;
	private JTextField textFieldJourFin;
	private JTextField textFieldTempsDebut;
	private JTextField textFieldTempsFin;
	private JComboBox<Short> comboBoxZoneCreate;
	private JLabel lblError;

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
		
		/*
		personneModel = new PersonneModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
		            return;
				
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        if (!lsm.isSelectionEmpty()) {
		            currentSelected = personneModel.getPersonneAt(lsm.getMinSelectionIndex());
		        	textFieldUser.setText(String.valueOf(currentSelected.getIdPersonne()));
		        } else {
		        	currentSelected = null;
		        	textFieldUser.setText("");
		        }
			}
		});
		*/
		
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
		
		JLabel lblAutorisations = new JLabel("Autorisations");
		lblAutorisations.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAutorisations.setBounds(20, 115, 420, 14);
		add(lblAutorisations);
		
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
		
		JScrollPane scrollPaneListGrant = new JScrollPane();
		scrollPaneListGrant.setBounds(10, 140, 430, 109);
		add(scrollPaneListGrant);
		
		tableGrants = new JTable();
		scrollPaneListGrant.setViewportView(tableGrants);
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
		
		textFieldUser = new JTextField();
		lblUser.setLabelFor(textFieldUser);
		textFieldUser.setEnabled(false);
		textFieldUser.setEditable(false);
		textFieldUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		textFieldUser.setBounds(104, 44, 86, 20);
		panelAddGrant.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		JLabel lblZone = new JLabel("Zone");
		lblZone.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblZone.setBounds(10, 79, 84, 14);
		panelAddGrant.add(lblZone);
		
		comboBoxZoneCreate = new JComboBox<Short>(new ZoneCBModel());
		comboBoxZoneCreate.setSelectedIndex(0);
		comboBoxZoneCreate.setSelectedItem(0);
		lblZone.setLabelFor(comboBoxZoneCreate);
		comboBoxZoneCreate.setBounds(104, 75, 86, 20);
		panelAddGrant.add(comboBoxZoneCreate);
		
		JLabel labelJourDebut = new JLabel("Jour debut");
		labelJourDebut.setLabelFor(textFieldJourDebut);
		labelJourDebut.setFont(new Font("Calibri", Font.PLAIN, 11));
		labelJourDebut.setBounds(10, 109, 84, 14);
		panelAddGrant.add(labelJourDebut);
		
		textFieldJourDebut = new JTextField();
		textFieldJourDebut.setBounds(104, 106, 86, 20);
		panelAddGrant.add(textFieldJourDebut);
		textFieldJourDebut.setColumns(10);
		
		JLabel lblJourFin = new JLabel("Jour fin");
		lblJourFin.setLabelFor(textFieldJourFin);
		lblJourFin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblJourFin.setBounds(10, 140, 84, 14);
		panelAddGrant.add(lblJourFin);
		
		textFieldJourFin = new JTextField();
		textFieldJourFin.setColumns(10);
		textFieldJourFin.setBounds(104, 137, 86, 20);
		panelAddGrant.add(textFieldJourFin);
		
		JLabel lblHeureDebut = new JLabel("Heure debut");
		lblHeureDebut.setLabelFor(textFieldTempsDebut);
		lblHeureDebut.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHeureDebut.setBounds(10, 171, 84, 14);
		panelAddGrant.add(lblHeureDebut);
		
		textFieldTempsDebut = new JTextField();
		textFieldTempsDebut.setColumns(10);
		textFieldTempsDebut.setBounds(104, 168, 86, 20);
		panelAddGrant.add(textFieldTempsDebut);
		
		JLabel lblHeureFin = new JLabel("Heure fin");
		lblHeureFin.setLabelFor(textFieldTempsFin);
		lblHeureFin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHeureFin.setBounds(10, 202, 84, 14);
		panelAddGrant.add(lblHeureFin);
		
		textFieldTempsFin = new JTextField();
		textFieldTempsFin.setColumns(10);
		textFieldTempsFin.setBounds(104, 199, 86, 20);
		panelAddGrant.add(textFieldTempsFin);
		
		JButton btnCreer = new JButton("Creer");
		btnCreer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!checkCreateFields()) {
					lblError.setText("Un champs necessaire a la creation est vide.");
					return;
				} else {
					lblError.setText("");
					return;
				}
			}
		});
		btnCreer.setBounds(101, 230, 89, 23);
		panelAddGrant.add(btnCreer);
	}

	private boolean checkCreateFields() {
		if(textFieldUser.getText().length() == 0)
			return false;
		if(textFieldJourDebut.getText().length() == 0)
			return false;
		if(textFieldJourFin.getText().length() == 0)
			return false;
		if(textFieldTempsDebut.getText().length() == 0)
			return false;
		if(textFieldTempsFin.getText().length() == 0)
			return false;
		return true;
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
		separator_2.setBounds(10, 36, 180, 2);
		panelUpdateGrant.add(separator_2);
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
	
	public class PersonneCBModel extends AbstractTableModel {
		private static final long serialVersionUID = -3240581296997491550L;
		
		private Personne[] listePersonne;
		private final String[] listeColonnes = {"Id", "Nom", "Prenom", "Photo", "Statut", "Role"};

		public PersonneCBModel(Personne[] liste) {
			super();
			listePersonne = liste;
		}
		
		public Personne getPersonneAt(int minSelectionIndex) {
			if(listePersonne.length < minSelectionIndex)
				return listePersonne[listePersonne.length-1];
			return listePersonne[minSelectionIndex];
		}
		
		public PersonneCBModel() {
			super();
			ArrayList<Personne> list = new ArrayList<Personne>();
			personne[] liste = null;
			try {
				liste = window.getInterfaceRespZone().chercherPersonnes(new String(), new String());
			} catch (droitsInsuffisants e) {
				e.printStackTrace();
			}
			for(personne pers : liste) {
				list.add(new Personne(pers.idPers, pers.nom, pers.prenom, pers.ph, pers.statut.toString(), pers.role.toString()));
			}
			listePersonne = new Personne[list.size()];
			listePersonne = list.toArray(listePersonne);
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
	
	public class AutorisationModel extends AbstractTableModel {
		private static final long serialVersionUID = -3240581296997491550L;
		
		private Autorisation[] listeAutorisations;
		private final String[] listeColonnes = {"Id", "Ref Pers", "Jour D", "Jour F", "Heure D", "Heure F"};
		
		public Autorisation getPersonneAt(int minSelectionIndex) {
			if(listeAutorisations.length < minSelectionIndex)
				return listeAutorisations[listeAutorisations.length-1];
			return listeAutorisations[minSelectionIndex];
		}
		
		public AutorisationModel(short[] z) {
			super();
			ArrayList<Autorisation> list = new ArrayList<Autorisation>();
			autorisation[] liste = null;
			liste = window.getInterfaceRespZone().getMonAutorisation().getMonAutorisation().getAutorisationsResp(z);
			for(autorisation a : liste) {
				list.add(new Autorisation(a.refPers, a.sP.heureDeb, a.sP.heureFin, a.sP.jourDeb, a.sP.jourFin, a.refZone));
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
