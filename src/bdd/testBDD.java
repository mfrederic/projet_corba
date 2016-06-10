package bdd;

import java.util.GregorianCalendar;

import bdd.objetsdao.JournalDAO;
import model.Journal;


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
*/
		JournalDAO repoJournal = new JournalDAO();
		
		GregorianCalendar gc = new GregorianCalendar();
		String ts = String.valueOf(gc.YEAR) + "-" + String.valueOf(gc.MONTH) + "-" + String.valueOf(gc.DAY_OF_MONTH) + " " + String.valueOf(gc.HOUR_OF_DAY) + ":" + String.valueOf(gc.MINUTE) + ":" + String.valueOf(gc.SECOND); 
		
		Journal j = new Journal();
		j.setCommentaire("test");
		j.setRefPersonne(2);
		j.setResultat(true);
		j.setTypeAcces("Entrée");
		j.setTimestamp(ts);
		
		j = repoJournal.create(j);
		
	}
}