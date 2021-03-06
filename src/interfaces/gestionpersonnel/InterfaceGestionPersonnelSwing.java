package interfaces.gestionpersonnel;

import interfaces.InterfaceGestionPersonnel;

import java.awt.Container;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import model.Personne;
import Gestion_acces.personne;

public class InterfaceGestionPersonnelSwing {

	private InterfaceGestionPersonnel cltGestPers;
	
	private JFrame frmGestionDuPersonnel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfaceGestionPersonnelSwing window = new InterfaceGestionPersonnelSwing();
					window.getFrmGestionDuPersonnel().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfaceGestionPersonnelSwing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		cltGestPers = new InterfaceGestionPersonnel();
		
		setFrmGestionDuPersonnel(new JFrame());
		getFrmGestionDuPersonnel().setTitle("Gestion du Personnel");
		getFrmGestionDuPersonnel().setBounds(100, 100, 292, 215);
		getFrmGestionDuPersonnel().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getFrmGestionDuPersonnel().setContentPane(new GPLogin(this));
	}
	
	public JFrame getFrame() {
		return this.getFrmGestionDuPersonnel();
	}
	
	public void setPane(Container c) {
		getFrmGestionDuPersonnel().setContentPane(c);
		getFrmGestionDuPersonnel().revalidate();
	}
	
	public InterfaceGestionPersonnel getCltGestPers() {
		return this.cltGestPers;
	}
	
	private JFrame getFrmGestionDuPersonnel() {
		return frmGestionDuPersonnel;
	}

	private void setFrmGestionDuPersonnel(JFrame frmGestionDuPersonnel) {
		this.frmGestionDuPersonnel = frmGestionDuPersonnel;
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

}
