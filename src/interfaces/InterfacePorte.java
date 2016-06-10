package interfaces;

import java.util.GregorianCalendar;

import log.ClientJournal;
import model.Porte;
import Gestion_acces.personne;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.AnnuairePackage.personneInexistante;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;

public class InterfacePorte {

	private static Porte porte;
	
	private static final String cleServeur = "stp";
	
	private static ClientServeurAuthentification monAuthentification;
	private static ClientServeurAutorisation monAutorisation;
	private static log.ClientJournal monJournal;
	
	private static personne persTemp;
	private static String message = "";

	public static void main(String args[]) {

		porte = new Porte(1,"Porte1",1);
		persTemp = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		monAuthentification = new ClientServeurAuthentification();
		monAutorisation = new ClientServeurAutorisation();
		monJournal = new ClientJournal();
		
		entrer("EmpreinteDeMarie","photooo");
		
		System.out.println(message);
	}
	
	private static void entrer(String emp, String ph) {
		boolean autorisation = false;		

		try {
			persTemp = monAuthentification.getMonAuthentification().demanderAuth(emp, ph, cleServeur);
			
			if (persTemp.idPers == 0)
				message = "Accès refusé : personne inconnue";
			else {
				try {
					autorisation = monAutorisation.getMonAutorisation().demanderAutor(persTemp, (short)porte.getRefZone());
				} catch (zoneInconnue e) {
					// TODO Auto-generated catch block
					System.out.println("Zone inconnue (id = " + e.zone + ")");
				}
				
				if (autorisation)
					message = "Bienvenue " + persTemp.prenom + " " + persTemp.nom;
				else
					message = "Accès refusé : personne non autorisée à entrer";
			}
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			message = "Accès refusé : " + e.raison;
		}
		

		journaliser("Entrée", persTemp, autorisation, message);
	}
	
	private static void journaliser(String typeAcces, personne p, boolean res, String commentaire) {
		GregorianCalendar gc = new GregorianCalendar();
		String ts = String.valueOf(gc.YEAR) + "-" + String.valueOf(gc.MONTH) + "-" + String.valueOf(gc.DAY_OF_MONTH) + " " + String.valueOf(gc.HOUR_OF_DAY) + ":" + String.valueOf(gc.MINUTE) + ":" + String.valueOf(gc.SECOND); 

		monJournal.getMonJournal().journaliser("e", typeAcces, p, res, commentaire);
	}
	
}
