package autorisation;

import helpers.MaPlageDate;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import model.Autorisation;
import model.Zone;
import Gestion_acces.ServeurAutorisationPOA;
import Gestion_acces.personne;
import Gestion_acces.structPlage;
import Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import bdd.objetsdao.AutorisationDAO;
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
		
		// BD
		Zone z = null;
		z = repoZone.find(zone);

		if (z == null )
			throw new zoneInconnue(zone);
		else {
			
			// BD
			Autorisation autor = new Autorisation();
			autor.setJourDebut(plage.jourDeb);
			autor.setJourFin(plage.jourFin);
			autor.setHeureDebut(plage.heureDeb);
			autor.setHeureFin(plage.heureFin);
			autor.setRefPersonne(p.idPers);
			autor.setRefZone(zone);
			
			repoAutorisation.create(autor);
		}
	}

	@Override
	public void modifierAutorisation(personne p, short oldZone,
			structPlage oldPlage, short newZone, structPlage newPlage)
			throws zoneInconnue, autorisationInexistante {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-modifierAutorisation");
		
		// BD
		Zone zOld, zNew = null;
		
		zOld = repoZone.find(oldZone);
		zNew = repoZone.find(newZone);

		if (zOld == null)
			throw new zoneInconnue(oldZone);
		else if (zNew == null) {
			throw new zoneInconnue(newZone);
		} else {
			
			Autorisation autor = repoAutorisation.findAllByPersonneZonePlage((int)(p.idPers), (int)oldZone, oldPlage.heureDeb, oldPlage.heureFin, oldPlage.jourDeb, oldPlage.jourFin);
			
			if (autor == null)
				throw new autorisationInexistante((short)0);
			else {
				
				// BD
				autor.setHeureDebut(newPlage.heureDeb);
				autor.setHeureFin(newPlage.heureFin);
				autor.setJourDebut(newPlage.jourDeb);
				autor.setJourFin(newPlage.jourFin);
				autor.setRefZone(oldZone);				
				repoAutorisation.update(autor);
				
			}	
		}
	}

	@Override
	public void supprimerAutorisation(personne p, short zone, structPlage plage)
			throws autorisationInexistante, zoneInconnue {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-supprimerAutorisation");
		// BD
		Zone z = null;
		
		z = repoZone.find(zone);

		if (z == null)
			throw new zoneInconnue(zone);
		else {
			Autorisation autor = repoAutorisation.findAllByPersonneZonePlage((int)(p.idPers), (int)zone, plage.heureDeb, plage.heureFin, plage.jourDeb, plage.jourFin);
			
			if (autor == null)
				throw new autorisationInexistante((short)0);
			else {
				
				// BD
				repoAutorisation.delete(autor);
			}
		}		
	}

	@Override
	public short[] getZonesResp(personne resp) {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-getZonesResp");
		
		short[] listeZones = null;
		ArrayList<Integer> zonesBD = null;
		
		// BD
		zonesBD = repoZone.getZoneByRespZone(resp.idPers);
		listeZones = new short[zonesBD.size()];
		
		Iterator<Integer> it = zonesBD.iterator();
		int i = 0;
		while (it.hasNext()) {
			listeZones[i] = (it.next()).shortValue();
			i++;
		}			
		
		return listeZones;
	}
	
	
}
