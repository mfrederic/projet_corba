package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gestion_acces.accesRefuse;
import Gestion_acces.droitsInsuffisants;
import Gestion_acces.personne;
import Gestion_acces.personneInexistante;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.compteDejaCree;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite;
import annuaire.ClientAnnuaire;
import authentification.ClientServeurAuthentification;

public class InterfaceGestionPersonnel {
	private static ClientServeurAuthentification monAuthentification;
	private static ClientAnnuaire monAnnuaire;
	
	private static final String cleServeur = "stp";
	
	private static personne persTemp;
	private static boolean authReussie;
	
	
	public static void main(String args[]) {

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
		System.out.println("1. Créer Compte");
		System.out.println("2. Ajouter photo");
		System.out.println("3. Supprimer empreinte");
        
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String choix = in.readLine();
			
	        boolean ok = false;
    		String s[] = null;
    		
			switch (choix) {			
	        	case "1": 
	        		while (!ok) {
	        			System.out.println("nom;prenom;statut;role;user;motdepasse");
		        		s = in.readLine().split(";");
	        			if (s.length > 5)
	        				ok = true;
	        		}
					try {
						creerCompte(s[0], s[1], statutPersonne.from_int(Integer.parseInt(s[2])), rolePersonne.from_int(Integer.parseInt(s[2])), s[4], s[5]);
					} catch (NumberFormatException | droitsInsuffisants e) {
						// TODO Auto-generated catch block
						System.out.println(e.toString());
					}
	        		break;
	        		
	        	case "2":
	        		while (!ok) {
	        			System.out.println("idPersonne;photo");
		        		s = in.readLine().split(";");
	        			if (s.length > 1)
	        				ok = true;
	        		}
					try {
						ajouterPhoto(Short.parseShort(s[0]), s[1]);
					} catch (NumberFormatException | droitsInsuffisants e) {
						// TODO Auto-generated catch block
						System.out.println(e.toString());
					}
	        		break;
					
	        	case "3":

	        			System.out.println("user");
					try {
						supprimerEmpreinte(in.readLine());
					} catch (droitsInsuffisants e) {
						// TODO Auto-generated catch block
						System.out.println(e.toString());
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
		} catch (compteInexistant | droitsInsuffisants | accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
	
	private static void creerCompte(String nom, String prenom, statutPersonne statut, rolePersonne role, String user, String password) throws droitsInsuffisants {
		short idPers = 0;
		
		if ((persTemp.role == rolePersonne.accueil) || (persTemp.role == rolePersonne.RH)){

			idPers = monAnnuaire.getMonAnnuaire().creerPersonne(nom, prenom, statut, role);
			if (idPers == 0)
				System.out.println("Personne impossible à créer --> compte non créé");
			else {
				try {
					monAuthentification.getMonAuthentification().creerCompte(idPers, user, password, cleServeur);
				} catch (compteDejaCree | accesRefuse e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
			}
			
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH ou Accueil");
		}
	}
	
	private static void ajouterPhoto(short idPers, String ph) throws droitsInsuffisants {
		if (persTemp.role == rolePersonne.RH) {

			try {
				monAnnuaire.getMonAnnuaire().ajouterPhoto(idPers, ph);
			} catch (personneInexistante e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH");
		}
	}
	
	private static void supprimerEmpreinte(String user) throws droitsInsuffisants {
		if ((persTemp.role == rolePersonne.accueil) || (persTemp.role == rolePersonne.RH)){

			try {
				monAuthentification.getMonAuthentification().supprimerEmpreinte(user, cleServeur);
			} catch (accesRefuse | compteInexistant | suppressionInterdite e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
		
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH ou Accueil");
		}
	}
}
