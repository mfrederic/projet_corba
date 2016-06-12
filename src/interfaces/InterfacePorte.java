package interfaces;

import java.util.Calendar;
import java.util.GregorianCalendar;

import Gestion_acces.personne;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAutorisationPackage.porteInconnue;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;

public class InterfacePorte {
	
	private static final String cleServeur = "stp";
	
	private ClientServeurAuthentification monAuthentification;
	private ClientServeurAutorisation monAutorisation;
	private log.ClientJournal monJournal;
	
	private personne persIdentifiee;
	private String message;
	private short idPorte;
	
	public InterfacePorte() {
		persIdentifiee = null;
		message = "";
		idPorte = 0;
	}

	public static void main(String args[]) {
/*
		porte = new Porte(1,"Porte1",1);
		persTemp = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		monAuthentification = new ClientServeurAuthentification();
		monAutorisation = new ClientServeurAutorisation();
		monJournal = new ClientJournal();
		
		entrer("EmpreinteDeMarie","photooo");
		
		System.out.println(message);
*/
	}
	
	public void entrer(String emp, String ph) {
		boolean autorisation = false;		

		try {
			persIdentifiee = monAuthentification.getMonAuthentification().demanderAuth(emp, ph, cleServeur);
			
			if (persIdentifiee.idPers == 0)
				message = "Accès refusé : personne inconnue";
			else {
				try {
					autorisation = monAutorisation.getMonAutorisation().demanderAutor(persIdentifiee, idPorte);;
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
		

		journaliser("Entrée", persIdentifiee, autorisation, message);
	}
	
	public void journaliser(String typeAcces, personne p, boolean res, String commentaire) {
		Calendar gc = new GregorianCalendar();
		String ts = String.valueOf(gc.get(Calendar.YEAR)) + "-" + String.valueOf(gc.get(Calendar.MONTH)+1) + "-" + String.valueOf(gc.get(Calendar.DAY_OF_MONTH)) + " " + String.valueOf(gc.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(gc.get(Calendar.MINUTE)) + ":" + String.valueOf(gc.get(Calendar.SECOND)); 
		
		monJournal.getMonJournal().journaliser(ts, typeAcces, p, res, commentaire);
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

	public short getIdPorte() {
		return idPorte;
	}

	public void setIdPorte(short idPorte) {
		this.idPorte = idPorte;
	}
	
	
}
