package bdd;

import helpers.MaPlageDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Gestion_acces.structPlage;


public class testBDD {
	
	public static void main(String[] args) {
/*
		Personne pers = new Personne("Bisault","Marie","photooo","temporaire","basique");
		PersonneDAO repoPersonne = new PersonneDAO();
		pers = repoPersonne.create(pers);
		pers = new Personne("Toto","Tata","phototo","temporaire","RH");
		repoPersonne = new PersonneDAO();
		pers = repoPersonne.create(pers);
		pers = new Personne("Titi","tii","phototi","permanent","Accueil");
		repoPersonne = new PersonneDAO();
		pers = repoPersonne.create(pers);
		
		Compte cpt = new Compte("SoFonteno","1234","empreinteDeMarie",2);
		CompteDAO accountRepo = new CompteDAO();
		cpt = accountRepo.create(cpt);
		cpt = new Compte("xml","xslt","xmlns",3);
		accountRepo.create(cpt);

		Zone z = new Zone(1, "Zone1", 3);
		ZoneDAO zoneRepo = new ZoneDAO();
		z = zoneRepo.create(z);
		Autorisation aut = new Autorisation(2, 8, 17, "01-01-2015", "30-05-2017", 1);
		AutorisationDAO autoRepo = new AutorisationDAO();
		aut = autoRepo.create(aut);
		//TODO classe métier zone puis porte puis autorisation

		structPlage sP = new structPlage();
		MaPlageDate mP;
		boolean ok;
		
		sP = verifierStructPlage("09-11-16", "09-11-16", "90", "7");
		
		mP = new MaPlageDate(sP);
		System.out.println(sP);
		if (sP==null)
			System.out.println("nOK");
		else
			System.out.println(mP.toString());
*/
	}
/*	
	private static structPlage verifierStructPlage(String jDeb, String jFin, String hDeb, String hFin) {
		structPlage retour = null;
		boolean structOk = true;
		Float heureDeb = (float)0; 
		Float heureFin = (float)0;
		
		// Tests heures
		try {
			heureDeb = Float.parseFloat(hDeb);
			heureFin = Float.parseFloat(hDeb);
		} catch (NumberFormatException e) {
			System.out.println(e.toString());
			structOk = false;
		}
		
		// Tests jours
		Date dateDeb = null;
		Date dateFin = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
            dateDeb = sdf.parse(jDeb);
            dateFin = sdf.parse(jFin);
            if (!jDeb.equals(sdf.format(dateDeb)) || !jFin.equals(sdf.format(dateFin))) {
                structOk = false;
            }
        } catch (ParseException ex) {
            System.out.println(ex.toString());
            structOk = false;
        }
		
        if (structOk)
        	retour = new structPlage(jDeb, jFin, heureDeb, heureFin);
        
		return retour;
	}
*/
}