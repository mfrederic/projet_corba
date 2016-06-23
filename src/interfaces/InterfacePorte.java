package interfaces;

import java.util.Calendar;
import java.util.GregorianCalendar;

import log.ClientJournal;
import model.Porte;
import Gestion_acces.personne;
import Gestion_acces.porte;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAutorisationPackage.porteInconnue;
import authentification.ClientAuthentification;
import autorisation.ClientAutorisation;

public class InterfacePorte {
	
	private static final String cleServeur = "stp";
	
	private ClientAuthentification monAuthentification;
	private ClientAutorisation monAutorisation;
	private ClientJournal monJournal;
	
	private personne persIdentifiee;
	private String message;
	private Porte porte;
	private GregorianCalendar date;
	
	public InterfacePorte() {
		monAuthentification = new ClientAuthentification();
		monAutorisation = new ClientAutorisation();
		monJournal = new ClientJournal();
		
		persIdentifiee = null;
		message = "";
		porte = null;
		date = new GregorianCalendar();
	}
	
	public void accesPorte(String emp, String ph, int typeAcces, String date) { //1: Entrée, 2 = Sortie
		boolean autorisation = false;		

		try {
			persIdentifiee = monAuthentification.getMonAuthentification().demanderAuth(emp, ph, cleServeur);
			
			if (persIdentifiee.idPers == 0)
				message = "Accès refusé : personne inconnue";
			else {
				try {
					autorisation = monAutorisation.getMonAutorisation().demanderAutor(persIdentifiee, (short)porte.getIdPorte(), date);
				} catch (porteInconnue e) {
					// TODO Auto-generated catch block
					message = "Porte inconnue (id = " + e.idPorte + ")";
				}
				
				if (autorisation)
					message = "Bienvenue " + persIdentifiee.prenom + " " + persIdentifiee.nom;
				else
					message = "Accès refusé : personne non autorisée à entrer";
			}
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			message = "Accès refusé : " + e.raison;
		}
		
		if (typeAcces == 1)
			journaliser("Entrée", persIdentifiee, autorisation, message + "(Porte " + this.porte.getIdPorte());
		else if (typeAcces == 2)
			journaliser("Sortie", persIdentifiee, autorisation, message + "(Porte " + this.porte.getIdPorte());
	}
	
	public void journaliser(String typeAcces, personne p, boolean res, String commentaire) {
		Calendar gc = new GregorianCalendar();
		String ts = String.valueOf(gc.get(Calendar.YEAR)) + "-" + String.valueOf(gc.get(Calendar.MONTH)+1) + "-" + String.valueOf(gc.get(Calendar.DAY_OF_MONTH)) + " " + String.valueOf(gc.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(gc.get(Calendar.MINUTE)) + ":" + String.valueOf(gc.get(Calendar.SECOND)); 
		
		monJournal.getMonJournal().journaliser(ts, typeAcces, p, res, commentaire);
	}
	
	public porte[] getPortes() {
		return monAutorisation.getMonAutorisation().getPortes();
	}

	public personne getPersIdentifiee() {
		return persIdentifiee;
	}

	public void setPersIdentifiee(personne persIdentifiee) {
		this.persIdentifiee = persIdentifiee;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Porte getPorte() {
		return this.porte;
	}

	public void setPorte(Porte porte) {
		this.porte = porte;
	}
	
	
}
