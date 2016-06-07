package autorisation;

import helpers.MaPlageDate;

import java.util.GregorianCalendar;

import Gestion_acces.ServeurAutorisationPOA;
import Gestion_acces.personne;
import Gestion_acces.personneInexistante;
import Gestion_acces.structPlage;
import Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;

public class ServeurAutorisationImpl extends ServeurAutorisationPOA{

	@Override
	public boolean demanderAutor(personne p, short zone)
			throws personneInexistante, zoneInconnue {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-demanderAutor");
		
		short id = 0;
		short idZone = 0;
		boolean autorise = false;
		MaPlageDate[] listePlages = new MaPlageDate[0];
		int i = 0;
		
		// id = select idPersonne from personne where p.idPers = idPersonne
		if (id == 0)
			throw new personneInexistante(p.idPers);
		else {
			// idZone = select idZone from zone where idZone = zone
			if (idZone == 0 )
				throw new zoneInconnue(zone);
			else {
				// listePlages = select structPlage from Autorisation where zone = zone
				while (!autorise && i< listePlages.length) {
					autorise = listePlages[i].contient(new GregorianCalendar());
					i++;
				}
			}
		}
		return autorise;
	}

	@Override
	public void ajouterAutorisation(personne p, short zone, structPlage plage)
			throws personneInexistante, zoneInconnue {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-ajouterAutorisation");
		
		short id = 0;
		short idZone = 0;

		// id = select idPersonne from personne where p.idPers = idPersonne
		if (id == 0)
			throw new personneInexistante(p.idPers);
		else {
			// idZone = select idZone from zone where idZone = zone
			if (idZone == 0 )
				throw new zoneInconnue(zone);
			else {
				// insert into Autorisation values (id, plage.heureDebut, plage.heureFin, plage.jourDebut, jourFin, zone)
			}
		}
	}

	@Override
	public void modifierAutorisation(personne p, short oldZone,
			structPlage oldPlage, short newZone, structPlage newPlage)
			throws personneInexistante, zoneInconnue, autorisationInexistante {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-modifierAutorisation");
		
		short idPers = 0;
		short idZone = 0;
		short idAutor = 0;

		// idPers = select idPersonne from personne where p.idPers = idPersonne
		if (idPers == 0)
			throw new personneInexistante(p.idPers);
		else {
			// idZone = select idZone from zone where idZone = zone
			if (idZone == 0 )
				throw new zoneInconnue(newZone);
			else {
				/* idAutor = select numAuto from Autorisation where where heureDebut = oldPlage.heureDeb
													and heureFin = oldPlage.heureFin
													and jourDebut = oldPlage.jourDeb
													and jourFin = oldPlage.jourFin
													and refZone = oldZone
				*/
				if (idAutor == 0)
					throw new autorisationInexistante(idAutor);
				else {
					// delete from Autorisation where numAuto = idAutor
					
					// insert into Autorisation values (id, newPlage.heureDebut, newPlage.heureFin, newPlage.jourDebut, newPlage.jourFin, zone)
				}	
			}
		}
	}

	@Override
	public void supprimerAutorisation(personne p, short zone, structPlage plage)
			throws autorisationInexistante {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-supprimerAutorisation");

		short idAutor = 0;
		
		/* idAutor = select numAuto from Autorisation where where heureDebut = oldPlage.heureDeb
		and heureFin = oldPlage.heureFin
		and jourDebut = oldPlage.jourDeb
		and jourFin = oldPlage.jourFin
		and refZone = oldZone
		*/
		if (idAutor == 0)
			throw new autorisationInexistante(idAutor);
		else {
		// delete from Autorisation where numAuto = idAutor
		
		// insert into Autorisation values (id, newPlage.heureDebut, newPlage.heureFin, newPlage.jourDebut, newPlage.jourFin, zone)
		}		
	}

	@Override
	public short[] getZonesResp(personne resp) throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-getZonesResp");
		
		short idPers = 0;
		short[] listeZones = null;
		listeZones[0] = 0;
		
		// idPers = select idPersonne from personne where p.idPers = idPersonne
		if (idPers == 0)
			throw new personneInexistante(idPers);
		else {
			// listeZones = select idZone from Zone where respZone = resp
			System.out.println(listeZones.toString());
		}
		return listeZones;
	}
	
	
}
