package bdd;

import model.Autorisation;
import model.Compte;
import model.Personne;
import model.Zone;
import bdd.objetsdao.AutorisationDAO;
import bdd.objetsdao.CompteDAO;
import bdd.objetsdao.PersonneDAO;
import bdd.objetsdao.ZoneDAO;


public class testBDD {
	
	public static void main(String[] args) {

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
		
	}
}