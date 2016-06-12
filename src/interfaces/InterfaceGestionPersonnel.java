package interfaces;

import java.util.ArrayList;

import model.Personne;
import Gestion_acces.personne;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.AnnuairePackage.personneInexistante;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteDejaCree;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite;
import annuaire.ClientAnnuaire;
import authentification.ClientServeurAuthentification;

public class InterfaceGestionPersonnel {
	private ClientServeurAuthentification monAuthentification;
	private ClientAnnuaire monAnnuaire;
	
	private static final String cleServeur = "stp";
	
	private personne persConnectee;
	private String message;
	
	public InterfaceGestionPersonnel() {
		monAuthentification = new ClientServeurAuthentification();
		monAnnuaire = new ClientAnnuaire();
		persConnectee = null;
	}
	
	public static void main(String args[]) {
/*
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
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (droitsInsuffisants e1) {
						// TODO Auto-generated catch block
						System.out.println("Droits insuffisants : " + e1.raison);
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
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (droitsInsuffisants e1) {
						// TODO Auto-generated catch block
						System.out.println("Droits insuffisants : " + e1.raison);
					}
	        		break;
					
	        	case "3":

	        			System.out.println("user");
					try {
						supprimerEmpreinte(in.readLine());
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

	public boolean authentifier(String user, String password) throws droitsInsuffisants {
		boolean authReussie = false;
		
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)) {
			try {
				persConnectee = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
				
				if (persConnectee.idPers == 0)
					authReussie = false;
				else
					authReussie = true;
			} catch (compteInexistant e) {
				// TODO Auto-generated catch block
				message = "Compte inexistant : (user: " + e.user + ")";
			} catch (droitsInsuffisants e) {
				// TODO Auto-generated catch block
				message = "Droits insuffisants : " + e.raison;
			} catch (accesRefuse e) {
				// TODO Auto-generated catch block
				message = "Accès refusé : " + e.raison;
			}
		
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH ou Accueil");
		}
		return authReussie;
	}
	
	public void creerCompte(String nom, String prenom, statutPersonne statut, rolePersonne role, String user, String password) throws droitsInsuffisants {
		short idPers = 0;
		
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)){

			idPers = monAnnuaire.getMonAnnuaire().creerPersonne(nom, prenom, statut, role);
			if (idPers == 0)
				message = "Personne impossible à créer --> compte non créé";
			else {

				try {
					monAuthentification.getMonAuthentification().creerCompte(idPers, user, password, cleServeur);
				} catch (compteDejaCree e) {
					// TODO Auto-generated catch block
					message = "Le compte existe déjà (user: " + e.user + " )";
				} catch (accesRefuse e) {
					// TODO Auto-generated catch block
					message = "Accès refusé : " + e.raison;
				}
			}
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH ou Accueil");
		}
	}
	
	public void ajouterPhoto(short idPers, String ph) throws droitsInsuffisants {
		if (persConnectee.role == rolePersonne.RH) {

			try {
				monAnnuaire.getMonAnnuaire().ajouterPhoto(idPers, ph);
			} catch (personneInexistante e) {
				// TODO Auto-generated catch block
				message = "Personne inexistante dans la base (id = " + e.id + ")";
			}
		
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH");
		}
	}
	
	public void supprimerEmpreinte(String user) throws droitsInsuffisants {
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)){

			try {
				monAuthentification.getMonAuthentification().supprimerEmpreinte(user, cleServeur);
			} catch (accesRefuse e) {
				// TODO Auto-generated catch block
				message = "Accès refusé : " + e.raison;
			} catch (compteInexistant e) {
				// TODO Auto-generated catch block
				message = "Compte inexistant : (user: " + e.user + ")";
			} catch (suppressionInterdite e) {
				// TODO Auto-generated catch block
				message = "Vous n'avez pas le droit de supprimer l'empreinte (role = " + e.role + ")";
			}

		
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH ou Accueil");
		}
	}
	
	public void modifierInfos(short idPers, String nom, String prenom, statutPersonne statut, rolePersonne role) throws droitsInsuffisants {
		if (persConnectee.role == rolePersonne.RH) {

			try {
				monAnnuaire.getMonAnnuaire().modifierInfos(idPers, nom, prenom, statut, role);
			} catch (personneInexistante e) {
				// TODO Auto-generated catch block
				message = "Personne inexistante dans la base (id = " + e.id + ")";
			}
		
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH");
		}
	}
	
	public personne[] chercherPersonnes(String nom, String prenom) throws droitsInsuffisants {
		// TODO Auto-generated method stub
		personne[] retour = new personne[0];
		if (persConnectee.role == rolePersonne.RH) {
			retour = monAnnuaire.getMonAnnuaire().chercherPersonnes(nom, prenom);
		
		} else {
			throw new droitsInsuffisants("Accès interdit : rôle doit être RH");
		}
		return retour;
	}

	public personne getPersConnectee() {
		return persConnectee;
	}

	public void setPersConnectee(personne persConnectee) {
		this.persConnectee = persConnectee;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
