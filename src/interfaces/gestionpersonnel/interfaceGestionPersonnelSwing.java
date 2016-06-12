package interfaces.gestionpersonnel;

import interfaces.InterfaceGestionPersonnel;

import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;

import Gestion_acces.personne;
import annuaire.ClientAnnuaire;
import authentification.ClientServeurAuthentification;

public class interfaceGestionPersonnelSwing {

	private InterfaceGestionPersonnel cltGestPers;
	
	private JFrame frame;

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
		cltGestPers = new InterfaceGestionPersonnel();
		
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
	
	public InterfaceGestionPersonnel getCltGestPers() {
		return this.cltGestPers;
	}

}
