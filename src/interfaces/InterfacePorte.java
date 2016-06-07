package interfaces;

import java.util.GregorianCalendar;

import log.ClientJournal;
import Gestion_acces.accesRefuse;
import Gestion_acces.personne;
import Gestion_acces.personneInexistante;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;
import model.Porte;

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
		
		entrer("empreinteDeMarie","photooo");
		
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
				} catch (personneInexistante | zoneInconnue e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
				
				if (autorisation)
					message = "Bienvenue " + persTemp.prenom + " " + persTemp.nom;
				else
					message = "Accès refusé : personne non autorisée à entrer";
			}
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			message = e.toString();
		}
		

		//journaliser("Entrée", persTemp, autorisation, message);
	}
	
	private static void journaliser(String typeAcces, personne p, boolean res, String commentaire) {
		short retour = monJournal.getMonJournal().journaliser(new GregorianCalendar().toString(), typeAcces, p, res, commentaire);
	}
	
}
