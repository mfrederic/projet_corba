package autorisation;

import helpers.MaPlageDate;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import model.Autorisation;
import model.Personne;
import model.Porte;
import model.Zone;
import Gestion_acces.ServeurAutorisationPOA;
import Gestion_acces.autorisation;
import Gestion_acces.personne;
import Gestion_acces.porte;
import Gestion_acces.statutPersonne;
import Gestion_acces.structPlage;
import Gestion_acces.ServeurAutorisationPackage.autorisationInexistante;
import Gestion_acces.ServeurAutorisationPackage.porteInconnue;
import Gestion_acces.ServeurAutorisationPackage.zoneInconnue;
import bdd.objetsdao.AutorisationDAO;
import bdd.objetsdao.PorteDAO;
import bdd.objetsdao.ZoneDAO;

public class ServeurAutorisationImpl extends ServeurAutorisationPOA{
	
	private PorteDAO repoPorte;
	private ZoneDAO repoZone;
	private AutorisationDAO repoAutorisation;
	
	public ServeurAutorisationImpl() {
		repoPorte = new PorteDAO();
		repoZone = new ZoneDAO();
		repoAutorisation = new AutorisationDAO();
	}

	@Override
	public boolean demanderAutor(personne p, short porte, String date)
			throws porteInconnue {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-demanderAutor");
		
		Porte porteBD = null;
		boolean autorise = false;
		List<MaPlageDate> listePlages = new ArrayList<MaPlageDate>();
		GregorianCalendar dateGc = new GregorianCalendar();
		try {
			dateGc = MaPlageDate.stringToDateTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dateGc.toString());
		// BD
		porteBD = repoPorte.find(porte);
		
		if (porteBD == null)
			throw new porteInconnue(porte);
		else {
			listePlages = repoAutorisation.findAllByPersonneZone(p.idPers, porteBD.getRefZone());
			if (listePlages != null) {
				if (p.statut == statutPersonne.permanent) { // Personne permanente
					if (listePlages != null)
						autorise = true;
					
				} else { // Personne temporaire
					Iterator<MaPlageDate> it = listePlages.iterator();
					while (!autorise && it.hasNext()) {
						autorise = it.next().contient(dateGc);					
					}
				}
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
	public void modifierAutorisation(short numAutor, structPlage newPlage) throws autorisationInexistante {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-modifierAutorisation");

		// BD
		Autorisation autor = repoAutorisation.find((int) numAutor);
		
		if (autor == null)
			throw new autorisationInexistante((short)0);
		else {
			
			// BD
			autor.setHeureDebut(newPlage.heureDeb);
			autor.setHeureFin(newPlage.heureFin);
			autor.setJourDebut(newPlage.jourDeb);
			autor.setJourFin(newPlage.jourFin);
			repoAutorisation.update(autor);
		}	
	}

	@Override
	public void supprimerAutorisation(short numAutor) throws autorisationInexistante {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-supprimerAutorisation");
		
		// BD
		Autorisation autor = repoAutorisation.find((int) numAutor);
		
		if (autor == null)
			throw new autorisationInexistante((short)0);
		else {
			// BD
			repoAutorisation.delete(autor);
		}
	}

	@Override
	public autorisation[] getAutorisationsResp(short[] zones) {
		// TODO Auto-generated method stub
		System.out.println("Autorisation-getZonesResp");
		
		ArrayList<Integer> zonesBD = new ArrayList<Integer>();
		ArrayList<Autorisation> listeAutorBD = null;
		autorisation[] retour;
		
		for (short z : zones)
			zonesBD.add((int) z);
		
		// BD
		if (zonesBD.size() > 0) {
			listeAutorBD = repoAutorisation.findAllByZones(zonesBD);
			if (listeAutorBD == null)
				retour = new autorisation[0];
			else
				retour = listeAutorisationsBDtoORB(listeAutorBD);
		}
		else
			retour = new autorisation[0];
		
		return retour;
	}
	
	@Override
	public short[] getZonesResp(personne resp) {
		// TODO Auto-generated method stub
		ArrayList<Short> zonesBD = new ArrayList<Short>();
		short[] listeIdZones = new short[0];
		int i = 0;

		// BD
		zonesBD = repoZone.getZoneByRespZone(resp.idPers);

		if (zonesBD.size() > 0) {
			listeIdZones = new short[zonesBD.size()];
			for (short idZ : zonesBD)				
				listeIdZones[i++] = idZ;
		}

		return listeIdZones;
	}
	
	private autorisation[] listeAutorisationsBDtoORB(ArrayList<Autorisation> listeAutor) {
		autorisation[] retour = new autorisation[listeAutor.size()];
		Autorisation autorBD = null;
		structPlage sP = new structPlage();
		
		Iterator<Autorisation> it = listeAutor.iterator();
		int i = 0;
				
		while (it.hasNext()) {
			autorBD = it.next();
			sP.jourDeb = autorBD.getJourDebut();
			sP.jourFin = autorBD.getJourFin();
			sP.heureDeb = autorBD.getHeureDebut();
			sP.heureFin = autorBD.getHeureFin();
			retour[i] = new autorisation((short)autorBD.getNumAuto(), (short)autorBD.getRefPersonne(), sP, (short)autorBD.getRefZone());
			i++;
		}
		
		return retour;
	}

	@Override
	public porte[] getPortes() {
		// TODO Auto-generated method stub
		ArrayList<Porte> listePortes = repoPorte.getInstances();
		porte[] listePortesORB = null;
		
		if (listePortes == null)
			listePortesORB = new porte[0];		
		else {
			Porte[] listePortesBD = (Porte[]) listePortes.toArray();
			listePortesORB = new porte[listePortesBD.length];
			for (int i=0; i<listePortesBD.length; i++) {
				listePortesORB[i] = new porte((short) listePortesBD[i].getIdPorte(), listePortesBD[i].getLibellePorte(), (short) listePortesBD[i].getRefZone());
			}
		}
		return listePortesORB;
	}
	
	

}
