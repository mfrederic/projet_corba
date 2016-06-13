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
