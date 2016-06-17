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
		*/
		
		String date = "14/06/16-06:31:00";
		MaPlageDate mp = new MaPlageDate(new String(), new String(), (float)6.5, (float)20.5);
		try {
			System.out.println(mp.contient(MaPlageDate.stringToDateTime(date)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}