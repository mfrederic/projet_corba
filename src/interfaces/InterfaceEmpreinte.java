package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gestion_acces.personne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import authentification.ClientServeurAuthentification;

public class InterfaceEmpreinte {
	
	private static final String cleServeur = "stp";
	
	private static ClientServeurAuthentification monAuthentification;
	
	private static personne persTemp;
	private static String userTemp;
	private static String pswdTemp;
	private static boolean authReussie;
	
	public static void main(String args[]) {
		
		monAuthentification = new ClientServeurAuthentification();
		persTemp = null;
		authReussie = false;
		
		while (!authReussie) {
			try {
				System.out.println("user;password");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String s[] = in.readLine().split(";");
				
			if (s.length > 1) {		
				userTemp = s[0];
				pswdTemp = s[1];
				authentifier(userTemp, pswdTemp);
			}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
		
		System.out.println("Que voulez-vous faire ?");
		System.out.println("1. Ajouter empreinte");
		System.out.println("2. Modifier empreinte");
        
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String choix = in.readLine();
			
			switch (choix) {
	        	case "1": 
        			System.out.println("empreinte");
        			choix = in.readLine();
	        		ajouterEmpreinte(userTemp, choix);
	        		break;
	        	case "2": try {
	        		System.out.println("empreinte");
        			choix = in.readLine();
	        		modifierEmpreinte(userTemp, choix);
	        		break;
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
        
	}
	
	private static void authentifier(String user, String password) {

		try {
			persTemp = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			
			if (persTemp.idPers == 0)
				authReussie = false;
			else
				authReussie = true;
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
	}
	
	private static void ajouterEmpreinte(String user, String empreinte){
		try {
			monAuthentification.getMonAuthentification().ajouterEmpreinte(user, empreinte, cleServeur);
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println("Accès refusé : " + e.raison);
		} catch (compteInexistant e) {
			// TODO Auto-generated catch block
			System.out.println("Compte inexistant : (user: " + e.user + ")");
		}
	}
	
	private static void modifierEmpreinte(String user, String empreinte) throws droitsInsuffisants {
		if (persTemp.statut == statutPersonne.temporaire)
			throw new droitsInsuffisants("Vous n'avez pas le droit de modifier votre empreinte");

		try {
			monAuthentification.getMonAuthentification().ajouterEmpreinte(user, empreinte, cleServeur);
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println("Accès refusé : " + e.raison);
		} catch (compteInexistant e) {
			// TODO Auto-generated catch block
			System.out.println("Compte inexistant : (user: " + e.user + ")");
		}
	}

}
