package interfaces;

import Gestion_acces.autorisation;
import Gestion_acces.personne;
import Gestion_acces.rolePersonne;
import Gestion_acces.structPlage;
import Gestion_acces.AnnuairePackage.personneInexistante;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import annuaire.ClientAnnuaire;
import authentification.ClientServeurAuthentification;
import autorisation.ClientServeurAutorisation;

public class InterfaceRespZones {

	private static final String cleServeur = "stp";
	
	private ClientServeurAutorisation monAutorisation;
	private ClientServeurAuthentification monAuthentification;
	private ClientAnnuaire monAnnuaire;
	
	private short[] listeZonesResp;
	private autorisation[] listeAutorisationsResp;
	private personne responsable;
	private String message;
	
	public InterfaceRespZones() {
		setMonAutorisation(new ClientServeurAutorisation());
		setMonAuthentification(new ClientServeurAuthentification());
		setMonAnnuaire(new ClientAnnuaire());
		responsable = null;
	}

	public short[] recupZoneAutorisation() {
		return getMonAutorisation().getMonAutorisation().getZonesResp(this.responsable);
	}
	
	public boolean authentifier(String user, String password) {
		boolean authReussie = false;

		try {
			responsable = getMonAuthentification().getMonAuthentification().authentifier(user, password, cleServeur);
			if (responsable.idPers == 0) {
				authReussie = false;
				message = "Erreur identification personne";
			} else {
				listeZonesResp = getMonAutorisation().getMonAutorisation().getZonesResp(responsable);
				
				if (listeZonesResp.length == 0) {
					authReussie = false;
					throw new droitsInsuffisants("Vous n'etes responsable d'aucune zone");
				} else {
					listeAutorisationsResp = getMonAutorisation().getMonAutorisation().getAutorisationsResp(listeZonesResp);
					authReussie = true;
				}
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
	
	public void ajouterAutorisation(short idPersonne, short idZone, structPlage plage) throws droitsInsuffisants, personneInexistante {
		boolean ok = false;
		int i = 0;
		while (i<listeZonesResp.length && !ok) {
			ok = (listeZonesResp[i] == idZone);
			i++;
		}
		if (!ok)
			throw new droitsInsuffisants("Vous n'avez pas le droit de gerer les droits de cette zone");
		else {
			try {
				// Cherche personne
				personne p = null;
				p = getMonAnnuaire().getMonAnnuaire().identifier(idPersonne);
				if (p == null)
					throw new personneInexistante(idPersonne);
				else
					getMonAutorisation().getMonAutorisation().ajouterAutorisation(p, idZone, plage);				
				
			} catch (zoneInconnue e) {
				message = "Zone inconnue (id = " + e.zone + ")";
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
			throw new droitsInsuffisants("Vous n'avez pas le droit de gerer les droits de cette zone");
		else {
			try {
				getMonAutorisation().getMonAutorisation().modifierAutorisation(numAutor, plage);
			} catch (autorisationInexistante e) {
				message = "Aucune autorisation correspondante trouvee (id = " + e.idAutorisation + ")";
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
			throw new droitsInsuffisants("Vous n'avez pas le droit de gerer les droits de cette zone");
		else {
			try {
				getMonAutorisation().getMonAutorisation().supprimerAutorisation(numAutor);
			} catch (autorisationInexistante e) {
				// TODO Auto-generated catch block
				message = "Aucune autorisation correspondante trouvee (id = " + e.idAutorisation + ")";
			}
		}
	}
	
	public personne[] chercherPersonnes(String nom, String prenom) throws droitsInsuffisants {
		return getMonAnnuaire().getMonAnnuaire().chercherPersonnes(nom, prenom);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ClientServeurAutorisation getMonAutorisation() {
		return monAutorisation;
	}

	private void setMonAutorisation(ClientServeurAutorisation monAutorisation) {
		this.monAutorisation = monAutorisation;
	}

	public ClientServeurAuthentification getMonAuthentification() {
		return monAuthentification;
	}

	private void setMonAuthentification(ClientServeurAuthentification monAuthentification) {
		this.monAuthentification = monAuthentification;
	}

	public ClientAnnuaire getMonAnnuaire() {
		return monAnnuaire;
	}

	private void setMonAnnuaire(ClientAnnuaire monAnnuaire) {
		this.monAnnuaire = monAnnuaire;
	}
	
}
