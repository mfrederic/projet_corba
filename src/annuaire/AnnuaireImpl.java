package annuaire;

import Gestion_acces.AnnuairePOA;
import Gestion_acces.personne;
import Gestion_acces.personneInexistante;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;

public class AnnuaireImpl extends AnnuairePOA{

	@Override
	public personne identifier(short id) throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-identifier");
		personne p = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		// p = select personne where idPers = id
		p.idPers = 1;
		if (p.idPers == 0)
			throw new personneInexistante(id);
		
		System.out.println(p.prenom + " " + p.nom);
		return p;
	}

	@Override
	public personne demanderIdentite(String ph) throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-identifier");
		personne p = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		// p = select personne where photo = ph
		p.idPers = 1;
		
		if (p.idPers == 0)
			throw new personneInexistante((short)0);
		
		System.out.println(p.prenom + " " + p.nom);
		return p;
	}

	@Override
	public short creerPersonne(String nom, String prenom,
			statutPersonne statut, rolePersonne role) {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-creerPersonne");
		
		short id = 0;
		
		// id = Insert into personne values(nom,prenom,statut,role)
		id = 1;
		
		return id;
	}

	@Override
	public void ajouterPhoto(short idPersonne, String ph)
			throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-ajouterPhoto");
		short idPers = 0;
		
		// idPers = select idPers where idPers = idPersonne
		if (idPers == 0)
			throw new personneInexistante(idPersonne);
		else
			System.out.println("Photo changée");
	}

	@Override
	public void modifierInfos(short idPersonne, String nom, String prenom,
			statutPersonne statut, rolePersonne role)
			throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-modifierInfos");
		
		short id = 0;
		// id = select idPers where idPers = idPersonne
		if (id == 0)
			throw new personneInexistante(idPersonne);
		else
			// set nom = nom where idPersonne = idPersonne, set prenom = prenom where idPersonne = idPersonne...
			System.out.println("Infos modifiées");
	}

	@Override
	public personne[] chercherPersonnes(String nom, String prenom) {
		// TODO Auto-generated method stub
		personne[] listePersonnes = null;
		// listePersonnes [] = select * from Personne where nom = nom and prenom = prenom;
		
		return listePersonnes;
	}

}
