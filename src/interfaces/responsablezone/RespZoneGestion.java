package interfaces.responsablezone;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import model.Personne;
import Gestion_acces.personne;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;

public class RespZoneGestion extends JPanel {
	private static final long serialVersionUID = -1241512579162739300L;
	private InterfaceRespZoneSwing window;
	private Personne currentSelected;
	
	private JTable tablePersonnes;
	private PersonneModel personneModel;
	private JTable tableGrants;
	private JTextField textFieldUser;
	private JTextField textFieldJourDebut;
	private JTextField textFieldJourFin;
	private JTextField textFieldTempsDebut;
	private JTextField textFieldTempsFin;

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
		
		JScrollPane scrollPaneListPersonne = new JScrollPane();
		scrollPaneListPersonne.setBounds(10, 78, 430, 109);
		add(scrollPaneListPersonne);
		
		personneModel = new PersonneModel();
		tablePersonnes = new JTable(personneModel);
		scrollPaneListPersonne.setViewportView(tablePersonnes);
		tablePersonnes.setFont(new Font("Calibri", Font.PLAIN, 11));
		tablePersonnes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePersonnes.setAutoCreateRowSorter(true);
		tablePersonnes.getRowSorter().toggleSortOrder(0);
		scrollPaneListPersonne.setViewportView(tablePersonnes);
		
		ListSelectionModel listSelectionModel = tablePersonnes.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting())
		            return;
				
		        ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		        if (!lsm.isSelectionEmpty()) {
		            currentSelected = personneModel.getPersonneAt(lsm.getMinSelectionIndex());
		    		// updateAccountPanel.setVisible(true);
		        } else {
		    		// updateAccountPanel.setVisible(false);
		        }
			}
		});
		
		JScrollPane scrollPaneListGrant = new JScrollPane();
		scrollPaneListGrant.setBounds(10, 223, 430, 109);
		add(scrollPaneListGrant);
		
		tableGrants = new JTable();
		scrollPaneListGrant.setViewportView(tableGrants);
		
		JLabel lblUtilisateurs = new JLabel("Utilisateurs");
		lblUtilisateurs.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblUtilisateurs.setBounds(20, 59, 420, 14);
		add(lblUtilisateurs);
		
		JLabel lblAutorisations = new JLabel("Autorisations");
		lblAutorisations.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAutorisations.setBounds(20, 198, 420, 14);
		add(lblAutorisations);
		
		JPanel panelAddGrant = new JPanel();
		panelAddGrant.setBounds(10, 343, 200, 261);
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
		
		JComboBox<?> comboBoxZone = new JComboBox();
		lblZone.setLabelFor(comboBoxZone);
		comboBoxZone.setBounds(104, 75, 86, 20);
		panelAddGrant.add(comboBoxZone);
		
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
		btnCreer.setBounds(101, 230, 89, 23);
		panelAddGrant.add(btnCreer);
		
		JPanel panelUpdateGrant = new JPanel();
		panelUpdateGrant.setBounds(240, 343, 200, 261);
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
	
	public class PersonneModel extends AbstractTableModel {
		private static final long serialVersionUID = -3240581296997491550L;
		
		private Personne[] listePersonne;
		private final String[] listeColonnes = {"Id", "Nom", "Prenom", "Photo", "Statut", "Role"};

		public PersonneModel(Personne[] liste) {
			super();
			listePersonne = liste;
		}
		
		public Personne getPersonneAt(int minSelectionIndex) {
			if(listePersonne.length < minSelectionIndex)
				return listePersonne[listePersonne.length-1];
			return listePersonne[minSelectionIndex];
		}
		
		public PersonneModel() {
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
}
