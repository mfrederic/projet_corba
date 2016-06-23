package interfaces;

import Gestion_acces.compte;
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
import authentification.ClientAuthentification;

public class InterfaceGestionPersonnel {
	private ClientAuthentification monAuthentification;
	private ClientAnnuaire monAnnuaire;
	
	private static final String cleServeur = "stp";
	
	private personne persConnectee;
	private String message;
	
	public InterfaceGestionPersonnel() {
		monAuthentification = new ClientAuthentification();
		monAnnuaire = new ClientAnnuaire();
		persConnectee = null;
	}
	
	public boolean authentifier(String user, String password) throws droitsInsuffisants {
		boolean authReussie = false;
		
		try {
			persConnectee = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			
			if (persConnectee.idPers == 0)
				authReussie = false;
			else if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)) {
				authReussie = true;
			} else {
				throw new droitsInsuffisants("Acces interdit : role doit etre RH ou Accueil");
			}
		} catch (compteInexistant e) {
			message = "Compte inexistant : (user: " + e.user + ")";
		} catch (droitsInsuffisants e) {
			message = "Droits insuffisants : " + e.raison;
		} catch (accesRefuse e) {
			message = "Acces refuse : " + e.raison;
		}
		
		return authReussie;
	}
	
	public void creerPersonne(String nom, String prenom, statutPersonne statut, rolePersonne role, String user, String password) throws droitsInsuffisants {
		short idPers = 0;
		
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)){

			idPers = monAnnuaire.getMonAnnuaire().creerPersonne(nom, prenom, statut, role);
			if (idPers == 0)
				message = "Personne impossible a creer --> compte n'a pas pu être créé";
			else {

				try {
					monAuthentification.getMonAuthentification().creerCompte(idPers, user, password, cleServeur);
					message = "La personne et le compte associes ont ete crees avec succes";
				} catch (compteDejaCree e) {
					message = "Le compte existe deja (user: " + e.user + " )";
				} catch (accesRefuse e) {
					message = "Acces refuse : " + e.raison;
				}
			}
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH ou Accueil");
		}
	}
	
	public void supprimerPersonne(short idPersonne) throws droitsInsuffisants {
		
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)){

			try {
				monAuthentification.getMonAuthentification().supprimerCompte(idPersonne, cleServeur);
				
				monAnnuaire.getMonAnnuaire().supprimerPersonne(idPersonne);
				message = "Personne et compte associes supprimes avec succes";

			} catch (accesRefuse e) {
				message = e.raison;
			} catch (compteInexistant e) {
				message = "Compte inexistant : (user: " + e.user + ")";
			} catch (personneInexistante e) {
				message = "Personne inexistante dans la base (id = " + e.id + ")";
			}
			
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH ou Accueil");
		}
	}
	
	public void ajouterPhoto(short idPers, String ph) throws droitsInsuffisants {
		if (persConnectee.role == rolePersonne.RH) {

			try {
				monAnnuaire.getMonAnnuaire().ajouterPhoto(idPers, ph);
				message = "Photo ajoutee avec succes";
			} catch (personneInexistante e) {
				message = "Personne inexistante dans la base (id = " + e.id + ")";
			}
		
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH");
		}
	}
	
	public void supprimerEmpreinte(short idPersonne) throws droitsInsuffisants {
		if (persConnectee.role == rolePersonne.accueil) {

			try {
				monAuthentification.getMonAuthentification().supprimerEmpreinte(idPersonne, cleServeur);
				message = "Empreinte supprimee avec succes";
			} catch (accesRefuse e) {
				message = "Acces refuse : " + e.raison;
			} catch (compteInexistant e) {
				message = "Compte inexistant : (user: " + e.user + ")";
			} catch (suppressionInterdite e) {
				message = "Vous n'avez pas le droit de supprimer l'empreinte (statut = " + e.statut + ")";
			}

		
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre Accueil");
		}
	}
	
	public void modifierInfos(short idPers, String nom, String prenom, statutPersonne statut, rolePersonne role) throws droitsInsuffisants {
		if (persConnectee.role == rolePersonne.RH) {

			try {
				monAnnuaire.getMonAnnuaire().modifierInfos(idPers, nom, prenom, statut, role);
				message = "Infos mises a jour";
			} catch (personneInexistante e) {
				message = "Personne inexistante dans la base (id = " + e.id + ")";
			}
		
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH");
		}
	}
	
	public personne[] chercherPersonnes(String nom, String prenom) throws droitsInsuffisants {
		personne[] retour = new personne[0];
		if ((persConnectee.role == rolePersonne.RH) || (persConnectee.role == rolePersonne.accueil)) {
			retour = monAnnuaire.getMonAnnuaire().chercherPersonnes(nom, prenom);
			
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH ou accueil");
		}
		return retour;
	}
	public compte[] chercherComptes(String nom, String prenom) throws droitsInsuffisants {
		compte[] retour = new compte[0];
		if ((persConnectee.role == rolePersonne.RH) || (persConnectee.role == rolePersonne.accueil)) {
			retour = monAuthentification.getMonAuthentification().getComptes();
			
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH ou accueil");
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
