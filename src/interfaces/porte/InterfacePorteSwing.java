package interfaces.porte;

import interfaces.InterfaceEmpreinte;
import interfaces.empreintes.EmpreinteMenu;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;

import Gestion_acces.personne;
import Gestion_acces.ServeurAutorisationPackage.porteInconnue;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;
import bdd.objetsdao.PorteDAO;

import javax.swing.JLabel;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

import log.ClientJournal;
import model.Porte;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSeparator;
import javax.swing.JCheckBox;

import java.awt.Color;

import javax.swing.SwingConstants;

public class InterfacePorteSwing {
	private static final String _cleServeur = "stp";
	
	private InterfaceEmpreinte cltEmpreintes;
	private ClientServeurAuthentification monAuthentification;
	private ClientServeurAutorisation monAutorisation;
	private log.ClientJournal monJournal;
	
	private ArrayList<Porte> listePorte;
	private Porte currentPorte;
	
	private String message;
	private short idPorte;
	
	private JFrame frame;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;

	private JComboBox<Porte> comboBox;
	private JLabel lblZone;
	private JTextField textFieldTimestamp;

	private JLabel lblError;
	private JButton btnQuitter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfacePorteSwing window = new InterfacePorteSwing();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfacePorteSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		listePorte = new PorteDAO().getInstances();
		
		cltEmpreintes = new InterfaceEmpreinte();
		monAuthentification = new ClientServeurAuthentification();
		monAutorisation = new ClientServeurAutorisation();
		monJournal = new ClientJournal();
		currentPorte = null;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 445, 324);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblOuvrirLaPorte = new JLabel("Ouvrir la porte");
		lblOuvrirLaPorte.setFont(new Font("Calibri", Font.BOLD, 18));
		lblOuvrirLaPorte.setBounds(10, 11, 136, 29);
		frame.getContentPane().add(lblOuvrirLaPorte);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblLogin.setBounds(10, 51, 100, 14);
		frame.getContentPane().add(lblLogin);
		
		textFieldLogin = new JTextField();
		textFieldLogin.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblLogin.setLabelFor(textFieldLogin);
		textFieldLogin.setBounds(120, 48, 136, 20);
		frame.getContentPane().add(textFieldLogin);
		textFieldLogin.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPassword.setBounds(10, 83, 100, 14);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPassword.setLabelFor(passwordField);
		passwordField.setBounds(120, 80, 136, 20);
		frame.getContentPane().add(passwordField);
		
		JButton btnOuvrir = new JButton("Ouvrir");
		btnOuvrir.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean autorisation = false;
				String login = textFieldLogin.getText();
				String password = new String(passwordField.getPassword());
				
				if(currentPorte == null) {
					lblError.setText("Il faut selectionner une porte.");
					return;
				}
				
				if(login.length() == 0 || password.length() == 0) {
					lblError.setText("Le login et password doivent etre renseignes.");
					return;
				}
			
				else if (getCltEmpreintes().authentifier(login, password)) {
					if (getCltEmpreintes().getPersConnectee().idPers == 0)
						lblError.setText("Acces refuse : personne inconnue");
					else {
						try {
							autorisation = monAutorisation.getMonAutorisation().demanderAutor(
									getCltEmpreintes().getPersConnectee(),
									idPorte);
						} catch (porteInconnue e) {
							lblError.setText("Porte inconnue (id = " + e.idPorte + ")");
						} catch (Exception e) {
							lblError.setText("Une erreur inconnu est survenue.");
							e.printStackTrace();
							return;
						}
						
						if (autorisation)
							lblError.setText("Bienvenue " + getCltEmpreintes().getPersConnectee().prenom + " " + getCltEmpreintes().getPersConnectee().nom);
						else
							lblError.setText("Acces refuse : personne non autorisee a entrer");
					}
					
				} else
					lblError.setText(getCltEmpreintes().getMessage());

				journaliser("Entree", getCltEmpreintes().getPersConnectee(), autorisation, message, textFieldTimestamp.getText());
			}
		});
		btnOuvrir.setBounds(120, 150, 136, 23);
		frame.getContentPane().add(btnOuvrir);
		
		comboBox = new JComboBox<Porte>(setComboBoxModel(listePorte));
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	currentPorte = (Porte)comboBox.getSelectedItem();
		    	lblZone.setText("Zone " + currentPorte.getRefZone());
		    }
		});
		comboBox.setEditable(true);
		comboBox.setBounds(156, 16, 100, 20);
		frame.getContentPane().add(comboBox);
		
		lblZone = new JLabel("");
		lblZone.setBounds(266, 19, 75, 14);
		frame.getContentPane().add(lblZone);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 111, 246, 2);
		frame.getContentPane().add(separator);

		Calendar gc = new GregorianCalendar();
		String ts = String.valueOf(gc.get(Calendar.YEAR)) + "-" + String.valueOf(gc.get(Calendar.MONTH)+1) + "-" + String.valueOf(gc.get(Calendar.DAY_OF_MONTH)) + " " + String.valueOf(gc.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(gc.get(Calendar.MINUTE)) + ":" + String.valueOf(gc.get(Calendar.SECOND)); 
		textFieldTimestamp = new JTextField(ts);
		textFieldTimestamp.setFont(new Font("Calibri", Font.PLAIN, 11));
		textFieldTimestamp.setEditable(false);
		textFieldTimestamp.setBounds(156, 121, 100, 20);
		frame.getContentPane().add(textFieldTimestamp);
		textFieldTimestamp.setColumns(10);
		
		JCheckBox chckbxDateAutomatique = new JCheckBox("date automatique");
		chckbxDateAutomatique.setFont(new Font("Calibri", Font.PLAIN, 11));
		chckbxDateAutomatique.setSelected(true);
		chckbxDateAutomatique.setBounds(6, 120, 140, 23);
		chckbxDateAutomatique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar gc = new GregorianCalendar();
				String ts = String.valueOf(gc.get(Calendar.YEAR)) + "-" + String.valueOf(gc.get(Calendar.MONTH)+1) + "-" + String.valueOf(gc.get(Calendar.DAY_OF_MONTH)) + " " + String.valueOf(gc.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(gc.get(Calendar.MINUTE)) + ":" + String.valueOf(gc.get(Calendar.SECOND)); 
				
				if(chckbxDateAutomatique.isSelected()) {
					textFieldTimestamp.setText(ts);
					textFieldTimestamp.setEditable(false);
				} else {
					textFieldTimestamp.setText(ts);
					textFieldTimestamp.setEditable(true);
				}
			}
		});
		frame.getContentPane().add(chckbxDateAutomatique);
		
		lblError = new JLabel("");
		lblError.setVerticalAlignment(SwingConstants.TOP);
		lblError.setHorizontalAlignment(SwingConstants.LEFT);
		lblError.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblError.setForeground(Color.RED);
		lblError.setBounds(10, 184, 246, 48);
		frame.getContentPane().add(lblError);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnQuitter.setBounds(10, 150, 89, 23);
		frame.getContentPane().add(btnQuitter);
		
		//setPane(new MonCompteLogin(this));
	}
	

	
	public void journaliser(String typeAcces, personne p, boolean res, String commentaire) {
		Calendar gc = new GregorianCalendar();
		String ts = String.valueOf(gc.get(Calendar.YEAR)) + "-" + String.valueOf(gc.get(Calendar.MONTH)+1) + "-" + String.valueOf(gc.get(Calendar.DAY_OF_MONTH)) + " " + String.valueOf(gc.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(gc.get(Calendar.MINUTE)) + ":" + String.valueOf(gc.get(Calendar.SECOND)); 
		journaliser(typeAcces, p, res, commentaire, ts);
	}
	
	public void journaliser(String typeAcces, personne p, boolean res, String commentaire, String timestamp) {
		if(timestamp.length() == 0) {
			journaliser(typeAcces, p, res, commentaire);
			return;
		}
		monJournal.getMonJournal().journaliser(timestamp, typeAcces, p, res, commentaire);
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setPane(Container c) {
		frame.setContentPane(c);
		frame.revalidate();
	}

	public String getCleserveur() {
		return _cleServeur;
	}

	public InterfaceEmpreinte getCltEmpreintes() {
		return cltEmpreintes;
	}

	public void setCltEmpreintes(InterfaceEmpreinte cltEmpreintes) {
		this.cltEmpreintes = cltEmpreintes;
	}

	public ClientServeurAuthentification getMonAuthentification() {
		return monAuthentification;
	}

	public void setMonAuthentification(ClientServeurAuthentification monAuthentification) {
		this.monAuthentification = monAuthentification;
	}

	public ClientServeurAutorisation getMonAutorisation() {
		return monAutorisation;
	}

	public void setMonAutorisation(ClientServeurAutorisation monAutorisation) {
		this.monAutorisation = monAutorisation;
	}

	public log.ClientJournal getMonJournal() {
		return monJournal;
	}

	public void setMonJournal(log.ClientJournal monJournal) {
		this.monJournal = monJournal;
	}
	
	public ComboBoxModel<Porte> setComboBoxModel(ArrayList<Porte> listePorte) {
		
		return new ComboBoxModel<Porte>() {
			public Porte selection = null;
			
			@Override
			public void removeListDataListener(ListDataListener arg0) {}
			
			@Override
			public int getSize() {
				return listePorte.size();
			}
			
			@Override
			public Porte getElementAt(int index) {
				return listePorte.get(index);
			}
			
			@Override
			public void addListDataListener(ListDataListener arg0) {}
			
			@Override
			public void setSelectedItem(Object anItem) {
				selection = (Porte) anItem;
			}
			
			@Override
			public Object getSelectedItem() {
				return selection;
			}
		};
	}
}
