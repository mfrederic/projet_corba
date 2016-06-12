package interfaces;

import Gestion_acces.personne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import Gestion_acces.ServeurAuthentificationPackage.empreinteDejaExistante;
import authentification.ClientServeurAuthentification;

public class InterfaceEmpreinte {
	
	private static final String cleServeur = "stp";
	
	private ClientServeurAuthentification monAuthentification;
	
	private personne persConnectee;
	private String userConnecte;
	private String message;
	
	public static void main(String args[]) {
		/*
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
      */  
	}
	
	public InterfaceEmpreinte() {
		monAuthentification = new ClientServeurAuthentification();
		userConnecte = null;
		persConnectee = null;
		message = "";
	}
	
	public boolean authentifier(String user, String password) {
		boolean authReussie = false;
		try {
			persConnectee = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			
			if (persConnectee.idPers == 0)
				authReussie = false;
			else {
				authReussie = true;
				userConnecte = user;
			}
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
		return authReussie;
	}
	
	public void ajouterEmpreinte(String user, String empreinte){
		try {
			monAuthentification.getMonAuthentification().ajouterEmpreinte(user, empreinte, cleServeur);
			message = "Empreinte ajoutée avec succès";
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			message = "Accès refusé : " + e.raison;
		} catch (compteInexistant e) {
			// TODO Auto-generated catch block
			message = "Compte inexistant : (user: " + e.user + ")";
		} catch (empreinteDejaExistante e) {
			// TODO Auto-generated catch block
			message = "Empreinte déjà existante : (" + e.emp + ")";
		}
	}
	
	public void modifierEmpreinte(String user, String empreinte) throws droitsInsuffisants {
		if (persConnectee.statut == statutPersonne.temporaire)
			throw new droitsInsuffisants("Vous n'avez pas le droit de modifier votre empreinte");

		try {
			monAuthentification.getMonAuthentification().modifierEmpreinte(user, empreinte, cleServeur);
			message = "Empreinte modifiée avec succès";
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			message = "Accès refusé : " + e.raison;
		} catch (compteInexistant e) {
			// TODO Auto-generated catch block
			message = "Compte inexistant : (user: " + e.user + ")";
		}
	}

	public personne getPersConnectee() {
		return persConnectee;
	}

	public void setPersConnectee(personne persConnectee) {
		this.persConnectee = persConnectee;
	}

	public String getUserConnecte() {
		return userConnecte;
	}

	public void setUserConnecte(String userConnecte) {
		this.userConnecte = userConnecte;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
