package interfaces;

import helpers.MaPlageDate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Gestion_acces.autorisation;
import Gestion_acces.personne;
import Gestion_acces.structPlage;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;

public class InterfaceRespZones {

	private static final String cleServeur = "stp";
	
	private ClientServeurAutorisation monAutorisation;
	private ClientServeurAuthentification monAuthentification;
	
	private short[] listeZonesResp;
	private autorisation[] listeAutorisationsResp;
	private personne responsable;
	
	public static void main(String args[]) {
/*		
		monAutorisation = new ClientServeurAutorisation();
		monAuthentification = new ClientServeurAuthentification();
		listeAutorisationsResp = null;
		responsable = null;
		
		boolean authReussie = false;
		
		while (!authReussie) {
			try {
				System.out.println("user;password");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String s[] = in.readLine().split(";");
				
			if (s.length > 1) {				
				authReussie = authentifier(s[0], s[1]);
			}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
        
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String choix = in.readLine();
			
    		String s[] = null;
    		
    		structPlage plage = null;
	        
	        System.out.println("Liste des autorisations dont vous êtes responsable (n°;zone;pers;plage) :");
	        for (autorisation au : listeAutorisationsResp) {
	        	MaPlageDate pl = new MaPlageDate(au.sP);
	        	System.out.println(
	        			au.numAuto + ";" + 
	        			au.refZone + ";" + 
	        			au.refPers + ";" +
	        			pl.toString()
	        	);
	        }
			
			System.out.println("Que voulez-vous faire ?");
			System.out.println("1. Ajouter autorisation");
			System.out.println("2. Modifier autorisation");
			System.out.println("3. Supprimer autorisation");
			
			switch (choix) {
				
	        	case "1": 
	        		
	        		while (plage == null) {
	        			System.out.println("idZone;jDeb(jj-mm-aa);jFin(jj-mm-aa);hDeb;hFin");
		        		s = in.readLine().split(";");
	        			if (s.length > 4)
	        				plage = verifierStructPlage(s[1], s[2], s[3], s[4]);
	        		}
	
					try {
						ajouterAutorisation(Short.parseShort(s[0]), plage);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (droitsInsuffisants e1) {
						// TODO Auto-generated catch block
						System.out.println("Droits insuffisants : " + e1.raison);
					}
	
	        		break;
	        		
	        	case "2": 
	        		while (plage == null) {
	        			System.out.println("idAutor;jDeb(jj-mm-aa);jFin(jj-mm-aa);hDeb;hFin");
		        		s = in.readLine().split(";");
	        			if (s.length > 4)
	        				plage = verifierStructPlage(s[1], s[2], s[3], s[4]);
	        		}
	
					try {
						modifierAutorisation(Short.parseShort(s[0]), plage);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (droitsInsuffisants e1) {
						// TODO Auto-generated catch block
						System.out.println("Droits insuffisants : " + e1.raison);
					}
	        		break;
	        		
	        	case "3": 
	        		boolean ok = false;
	        		while (!ok) {
	        			System.out.println("idAutor");
		        		s = in.readLine().split(";");
		        		ok = (s.length > 0);
	        		}
	        		
	        		try {
						supprimerAutorisation(Short.parseShort(s[0]));
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (droitsInsuffisants e) {
						// TODO Auto-generated catch block
						System.out.println("Droits insuffisants : " + e.raison);
					}
	        		break;
	        		
	        	default:
	        		break;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/        
	}
	
	public boolean authentifier(String user, String password) {
		boolean authReussie = false;

		try {
			responsable = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			if (responsable.idPers == 0) {
				authReussie = false;
				System.out.println("Erreur identification personne");
			}
			else {
				System.out.println(responsable);
				listeZonesResp = monAutorisation.getMonAutorisation().getZonesResp(responsable);
				
				if (listeZonesResp.length == 0) {
					authReussie = false;
					throw new droitsInsuffisants("Vous n'êtes responsable d'aucune zone");
				}
				else {
					listeAutorisationsResp = monAutorisation.getMonAutorisation().getAutorisationsResp(listeZonesResp);
					authReussie = true;
					
				}
			}
				
				
		} catch (compteInexistant e) {
			// TODO Auto-generated catch block
			System.out.println("Compte inexistant : (user: " + e.user + ")");
		} catch (droitsInsuffisants e) {
			// TODO Auto-generated catch block
			System.out.println("Droits insuffisants : " + e.raison);
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println("Accès refusé : " + e.raison);
		}
		
		return authReussie;
		
	}
	
	public void ajouterAutorisation(short idZone, structPlage plage) throws droitsInsuffisants {
		boolean ok = false;
		int i = 0;
		while (i<listeZonesResp.length && !ok) {
			ok = (listeZonesResp[i] == idZone);
			i++;
		}
		if (!ok)
			throw new droitsInsuffisants("Vous n'avez pas le droit de gérer les droits de cette zone");
		else {
			try {
				monAutorisation.getMonAutorisation().ajouterAutorisation(responsable, idZone, plage);
			} catch (zoneInconnue e) {
				// TODO Auto-generated catch block
				System.out.println("Zone inconnue (id = " + e.zone + ")");
			}	
		}
	}
	
	public void modifierAutorisation(short numAutor, structPlage plage) throws droitsInsuffisants {
		boolean ok = false;
		int i = 0;
		while (i<listeAutorisationsResp.length && !ok) {
			ok = (listeAutorisationsResp[i].numAuto == numAutor);
			i++;
		}
		if (!ok)
			throw new droitsInsuffisants("Vous n'avez pas le droit de gérer les droits de cette zone");
		else {
			try {
				monAutorisation.getMonAutorisation().modifierAutorisation(numAutor, plage);
			} catch (autorisationInexistante e) {
				// TODO Auto-generated catch block
				System.out.println("Aucune autorisation correspondante trouvée (id = " + e.idAutorisation + ")");
			}
		}
	}

	public void supprimerAutorisation(short numAutor) throws droitsInsuffisants {
		boolean ok = false;
		int i = 0;
		while (i<listeAutorisationsResp.length && !ok) {
			ok = (listeAutorisationsResp[i].numAuto == numAutor);
			i++;
		}
		if (!ok)
			throw new droitsInsuffisants("Vous n'avez pas le droit de gérer les droits de cette zone");
		else {
			try {
				monAutorisation.getMonAutorisation().supprimerAutorisation(numAutor);
			} catch (autorisationInexistante e) {
				// TODO Auto-generated catch block
				System.out.println("Aucune autorisation correspondante trouvée (id = " + e.idAutorisation + ")");
			}
		}
	}
	
	private structPlage verifierStructPlage(String jDeb, String jFin, String hDeb, String hFin) {

		structPlage retour = null;
		boolean structOk = true;
		Float heureDeb = (float)0; 
		Float heureFin = (float)0;
		
		// Tests heures
		try {
			heureDeb = Float.parseFloat(hDeb);
			heureFin = Float.parseFloat(hFin);
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
			structOk = false;
		}
		
		// Tests jours
		Date dateDeb = null;
		Date dateFin = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
            dateDeb = sdf.parse(jDeb);
            dateFin = sdf.parse(jFin);
            if (!jDeb.equals(sdf.format(dateDeb)) || !jFin.equals(sdf.format(dateFin))) {
                structOk = false;
            }
        } catch (ParseException ex) {
            System.out.println(ex.toString());
            structOk = false;
        }
		
        if (structOk)
        	retour = new structPlage(jDeb, jFin, heureDeb, heureFin);

		return retour;
	}
	
}
