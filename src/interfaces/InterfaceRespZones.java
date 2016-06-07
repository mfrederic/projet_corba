package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gestion_acces.accesRefuse;
import Gestion_acces.droitsInsuffisants;
import Gestion_acces.personne;
import Gestion_acces.personneInexistante;
import Gestion_acces.statutPersonne;
import Gestion_acces.structPlage;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;

public class InterfaceRespZones {

	private static final String cleServeur = "stp";
	
	private static ClientServeurAutorisation monAutorisation;
	private static ClientServeurAuthentification monAuthentification;
	
	private static boolean authReussie;
	private static short[] listeZoneResp;
	private static personne responsable;
	
	public static void main(String args[]) {
		
		monAutorisation = new ClientServeurAutorisation();
		monAuthentification = new ClientServeurAuthentification();
		authReussie = false;
		listeZoneResp = null;
		listeZoneResp[0] = 0;
		responsable = null;
		
		while (!authReussie) {
			try {
				System.out.println("user;password");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String s[] = in.readLine().split(";");
				
			if (s.length > 1) {				
				authentifier(s[0], s[1]);
			}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
        
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String choix = in.readLine();
			
	        boolean ok = false;
    		String s[] = null;
    		
    		structPlage plage = new structPlage();
	        
	        System.out.println("Liste des zones dont vous êtes responsable :\n" + listeZoneResp.toString());
			
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1. Ajouter autorisation");
			System.out.println("2. Modifier autorisation");
			System.out.println("3. Supprimer autorisation");
			
			switch (choix) {
				
	        	case "1": 
	        		
	        		while (!ok) {
	        			System.out.println("idZone;jDeb(jj-mm-aa);jFin(jj-mm-aa);hDeb;hFin");
		        		s = in.readLine().split(";");
	        			if (s.length > 4)
	        				ok = verifierStructPlage(s[1], s[2], s[3], s[4], plage);
	        		}
					try {
						ajouterAutorisation(Short.parseShort(s[0]), plage);
					} catch (NumberFormatException | droitsInsuffisants e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;
	        		
	        	case "2": 
	        		modifierAutorisation();
	        		break;
	        		
	        	case "3": 
	        		while (!ok) {
	        			System.out.println("idZone;jDeb(jj-mm-aa);jFin(jj-mm-aa);hDeb;hFin");
		        		s = in.readLine().split(";");
	        			if (s.length > 4)
	        				ok = verifierStructPlage(s[1], s[2], s[3], s[4], plage);
	        		}
					try {
						supprimerAutorisation(Short.parseShort(s[0]), plage);
					} catch (NumberFormatException | droitsInsuffisants e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		break;
	        	default:
	        		break;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}        
	}
	
	private static void authentifier(String user, String password) {
		try {
			responsable = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			if (responsable.idPers == 0) {
				authReussie = false;
				System.out.println("Erreur identification personne");
			}
			else {
				listeZoneResp = monAutorisation.getMonAutorisation().getZonesResp(responsable);
				
				if (listeZoneResp[0] == 0) {
					authReussie = false;
					throw new droitsInsuffisants("Vous n'êtes responsable d'aucune zone");
				}
				else {
					authReussie = true;
					
				}
			}
				
				
		} catch (compteInexistant | personneInexistante | droitsInsuffisants | accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
	}
	
	private static void ajouterAutorisation(short idZone, structPlage plage) throws droitsInsuffisants {
		boolean ok = false;
		for (int i=0; i<listeZoneResp.length; i++) {
			if (listeZoneResp[i] == idZone)
				ok = true;
		}
		if (!ok)
			throw new droitsInsuffisants("Vous n'avez pas le droit de gérer les droits de cette zone");
		else {
			try {
				monAutorisation.getMonAutorisation().ajouterAutorisation(responsable, idZone, plage);
			} catch (personneInexistante | zoneInconnue e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	private static void modifierAutorisation() {
		
	}

	private static void supprimerAutorisation(short idZone, structPlage plage) throws droitsInsuffisants {
		boolean ok = false;
		for (int i=0; i<listeZoneResp.length; i++) {
			if (listeZoneResp[i] == idZone)
				ok = true;
		}
		if (!ok)
			throw new droitsInsuffisants("Vous n'avez pas le droit de gérer les droits de cette zone");
		else {
			try {
				monAutorisation.getMonAutorisation().supprimerAutorisation(responsable, idZone, plage);
			} catch (autorisationInexistante e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	private static boolean verifierStructPlage(String jDeb, String jFin, String hDeb, String hFin, structPlage out) {
		boolean structOk = true;
		
		out = new structPlage(jDeb, jFin, Float.parseFloat(hDeb), Float.parseFloat(hDeb));
		return structOk;
	}
	
}
