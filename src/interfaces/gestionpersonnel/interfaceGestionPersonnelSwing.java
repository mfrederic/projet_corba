package interfaces.gestionpersonnel;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

import Gestion_acces.personne;
import annuaire.ClientAnnuaire;
import authentification.ClientServeurAuthentification;

public class interfaceGestionPersonnelSwing {
	private final String _cleServeur = "stp";
	
	private ClientServeurAuthentification monAuthentification;
	private ClientAnnuaire monAnnuaire;

	private String userLogin;
	private JFrame frame;
	private personne persTemp;
	private boolean authReussie;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfaceGestionPersonnelSwing window = new interfaceGestionPersonnelSwing();
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
	public interfaceGestionPersonnelSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(new GPLogin(this));
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public void setPane(Container c) {
		frame.setContentPane(c);
		frame.revalidate();
	}
	
	public void setUserLogin(String userlogin) {
		this.userLogin = userlogin;
	}
	
	public String getUserLogin() {
		return this.userLogin;
	}

	public personne getPersTemp() {
		return persTemp;
	}

	public void setPersTemp(personne persTemp) {
		this.persTemp = persTemp;
	}

	public boolean isAuthReussie() {
		return authReussie;
	}

	public void setAuthReussie(boolean authReussie) {
		this.authReussie = authReussie;
	}

	public ClientServeurAuthentification getMonAuthentification() {
		return monAuthentification;
	}

	public void setMonAuthentification(ClientServeurAuthentification monAuthentification) {
		this.monAuthentification = monAuthentification;
	}

	public String getCleserveur() {
		return _cleServeur;
	}

	public ClientAnnuaire getMonAnnuaire() {
		return monAnnuaire;
	}

	public void setMonAnnuaire(ClientAnnuaire monAnnuaire) {
		this.monAnnuaire = monAnnuaire;
	}

}
