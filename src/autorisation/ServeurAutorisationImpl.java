package autorisation;

import helpers.MaPlageDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import model.Personne;
import model.Zone;
import Gestion_acces.ServeurAutorisationPOA;
import Gestion_acces.personne;
import Gestion_acces.structPlage;
import Gestion_acces.AnnuairePackage.personneInexistante;
import Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import bdd.objetsdao.AutorisationDAO;
import bdd.objetsdao.PersonneDAO;
import bdd.objetsdao.ZoneDAO;

public class ServeurAutorisationImpl extends ServeurAutorisationPOA{
	
	private ZoneDAO repoZone;
	private AutorisationDAO repoAutorisation;
	
	public ServeurAutorisationImpl() {
		repoZone = new ZoneDAO();
		repoAutorisation = new AutorisationDAO();
	}

	@Override
	public boolean demanderAutor(personne p, short zone)
			throws zoneInconnue {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-demanderAutor");
		
		Zone z = null;
		boolean autorise = false;
		List<MaPlageDate> listePlages = new ArrayList<MaPlageDate>();
			
		// BD
		z = repoZone.find(zone);
		
		if (z == null)
			throw new zoneInconnue(zone);
		else {
			listePlages = repoAutorisation.findAllByPersonneZone(p.idPers, zone);
			Iterator<MaPlageDate> it = listePlages.iterator();
			while (!autorise && it.hasNext()) {
				autorise = it.next().contient(new GregorianCalendar());
			}
		}
		
		return autorise;
	}

	@Override
	public void ajouterAutorisation(personne p, short zone, structPlage plage)
			throws zoneInconnue {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-ajouterAutorisation");
		
		short idZone = 0;
		// idZone = select idZone from zone where idZone = zone
		if (idZone == 0 )
			throw new zoneInconnue(zone);
		else {
			// insert into Autorisation values (id, plage.heureDebut, plage.heureFin, plage.jourDebut, jourFin, zone)
		}
	}

	@Override
	public void modifierAutorisation(personne p, short oldZone,
			structPlage oldPlage, short newZone, structPlage newPlage)
			throws zoneInconnue, autorisationInexistante {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-modifierAutorisation");
		
		short idZone = 0;
		short idAutor = 0;

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
	public short[] getZonesResp(personne resp) {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-getZonesResp");
		
		short[] listeZones = null;
		listeZones[0] = 0;
		
		// listeZones = select idZone from Zone where respZone = resp
		System.out.println(listeZones.toString());
		
		return listeZones;
	}
	
	
}
