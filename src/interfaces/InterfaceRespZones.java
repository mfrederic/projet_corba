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
	
	public void ajouterAutorisation(short idPersonne, short idZone, structPlage plage) throws droitsInsuffisants, personneInexistante {
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
				// Cherche personne
				personne p = null;
				p = monAnnuaire.getMonAnnuaire().identifier(idPersonne);
				if (p == null)
					throw new personneInexistante(idPersonne);
				else
					monAutorisation.getMonAutorisation().ajouterAutorisation(p, idZone, plage);				
				
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
	
}
