package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gestion_acces.accesRefuse;
import Gestion_acces.droitsInsuffisants;
import Gestion_acces.personne;
import Gestion_acces.statutPersonne;
import Gestion_acces.structPlage;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import annuaire.ClientAnnuaire;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;

public class InterfaceRespZones {

	private static final String cleServeur = "stp";
	
	private static ClientServeurAutorisation monAutorisation;
	private static ClientServeurAuthentification monAuthentification;
	private static ClientAnnuaire monAnnuaire;
	
	private static boolean authReussie;
	
	public static void main(String args[]) {
		
		monAutorisation = new ClientServeurAutorisation();
		monAuthentification = new ClientServeurAuthentification();
		monAnnuaire = new ClientAnnuaire();
		authReussie = false;
		
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
		
		System.out.println("Que voulez-vous faire ?");
		System.out.println("1. Ajouter autorisation");
		System.out.println("2. Modifier autorisation");
		System.out.println("3. Supprimer autorisation");
        
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String choix = in.readLine();
			
			switch (choix) {
	        	case "1": ajouterAutorisation();
	        		break;
	        	case "2": modifierAutorisation();
	        		break;
	        	case "3": supprimerAutorisation();
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
			personne p = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			if (p.idPers == 0) {
				authReussie = false;
				System.out.println("Erreur identification personne");
			}
			else {// a modifier !!
				if (p.statut == statutPersonne.permanent)
					authReussie = true;// !!!!!!!!!!
				else {
					authReussie = false;
					throw new droitsInsuffisants("Rôle doit être permanent");
				}
			}
			
		} catch (compteInexistant | droitsInsuffisants | accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		
	}
	
	private static void ajouterAutorisation() {
		short zone = 1;
		structPlage plage = InterfaceRespZones.askForPlage();
		personne p = InterfaceRespZones.askToFindPersonne();
		
		try {
			monAutorisation.getMonAutorisation().ajouterAutorisation(p, zone, plage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void modifierAutorisation() {
		
	}

	private static void supprimerAutorisation() {
	
	}
	
	private static personne askToFindPersonne() {
		personne[] p = null;
		boolean findPersonne = false;
		int tryCount = 0;
		
		while (!findPersonne && tryCount < 3) {
			try {
				System.out.println("Quelle personne ?");
				System.out.println("nom;prenom");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String s[] = in.readLine().split(";");
				
				if (s.length > 1) {
					p = monAnnuaire.getMonAnnuaire().chercherPersonnes(s[0], s[1]);
					
					if(p.length > 1)
						findPersonne = true;
					else
						System.err.println("Personne non trouv�e ! " + tryCount + 1 + " essai(s) / 5.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			tryCount++;
		}
		
		if(p.length > 0)
			return p[0];
		else
			return null;
	}
	
	private static structPlage askForPlage() {
		structPlage plage = null;
		
		try {
			System.out.println("Quelle plage (DD-MM;DD-MM;HH;HH) ?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String s[] = in.readLine().split(";");
			
			if (s.length > 1) {
				plage = new Gestion_acces.structPlage(s[0], s[1], Float.parseFloat(s[2]), Float.parseFloat(s[3]));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return plage;
	}
}
