package interfaces;

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
			// TODO Auto-generated catch block
			message = "Compte inexistant : (user: " + e.user + ")";
		} catch (droitsInsuffisants e) {
			// TODO Auto-generated catch block
			message = "Droits insuffisants : " + e.raison;
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			message = "Acces refuse : " + e.raison;
		}
		
		return authReussie;
	}
	
	public void creerCompte(String nom, String prenom, statutPersonne statut, rolePersonne role, String user, String password) throws droitsInsuffisants {
		short idPers = 0;
		
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)){

			idPers = monAnnuaire.getMonAnnuaire().creerPersonne(nom, prenom, statut, role);
			if (idPers == 0)
				message = "Personne impossible a creer --> compte non cree";
			else {

				try {
					monAuthentification.getMonAuthentification().creerCompte(idPers, user, password, cleServeur);
					message = "La personne et le compte associes ont ete crees avec succes";
				} catch (compteDejaCree e) {
					// TODO Auto-generated catch block
					message = "Le compte existe deja (user: " + e.user + " )";
				} catch (accesRefuse e) {
					// TODO Auto-generated catch block
					message = "Acces refuse : " + e.raison;
				}
			}
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH ou Accueil");
		}
	}
	
	public void supprimerCompte(String user) throws droitsInsuffisants {
		short idPers = 0;
		
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)){

			try {
				idPers = monAuthentification.getMonAuthentification().supprimerCompte(user, cleServeur);
				
				if (idPers > 0)
					try {
						monAnnuaire.getMonAnnuaire().supprimerPersonne(idPers);
						message = "Personne et compte associes supprimes avec succes";
					} catch (personneInexistante e) {
						// TODO Auto-generated catch block
						message = "Personne inexistante dans la base (id = " + e.id + ")";
					}
			} catch (accesRefuse e1) {
				// TODO Auto-generated catch block
				message = e1.raison;
			} catch (compteInexistant e1) {
				// TODO Auto-generated catch block
				message = "Compte inexistant : (user: " + e1.user + ")";
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
				// TODO Auto-generated catch block
				message = "Personne inexistante dans la base (id = " + e.id + ")";
			}
		
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH");
		}
	}
	
	public void supprimerEmpreinte(String user) throws droitsInsuffisants {
		if ((persConnectee.role == rolePersonne.accueil) || (persConnectee.role == rolePersonne.RH)){

			try {
				monAuthentification.getMonAuthentification().supprimerEmpreinte(user, cleServeur);
				message = "Empreinte supprimee avec succes";
			} catch (accesRefuse e) {
				// TODO Auto-generated catch block
				message = "Acces refuse : " + e.raison;
			} catch (compteInexistant e) {
				// TODO Auto-generated catch block
				message = "Compte inexistant : (user: " + e.user + ")";
			} catch (suppressionInterdite e) {
				// TODO Auto-generated catch block
				message = "Vous n'avez pas le droit de supprimer l'empreinte (role = " + e.role + ")";
			}

		
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH ou Accueil");
		}
	}
	
	public void modifierInfos(short idPers, String nom, String prenom, statutPersonne statut, rolePersonne role) throws droitsInsuffisants {
		if (persConnectee.role == rolePersonne.RH) {

			try {
				monAnnuaire.getMonAnnuaire().modifierInfos(idPers, nom, prenom, statut, role);
				message = "Infos mises a jour";
			} catch (personneInexistante e) {
				// TODO Auto-generated catch block
				message = "Personne inexistante dans la base (id = " + e.id + ")";
			}
		
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH");
		}
	}
	
	public personne[] chercherPersonnes(String nom, String prenom) throws droitsInsuffisants {
		// TODO Auto-generated method stub
		personne[] retour = new personne[0];
		if (persConnectee.role == rolePersonne.RH) {
			retour = monAnnuaire.getMonAnnuaire().chercherPersonnes(nom, prenom);
			
		} else {
			throw new droitsInsuffisants("Acces interdit : role doit etre RH");
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
