package annuaire;

import model.Personne;
import Gestion_acces.AnnuairePOA;
import Gestion_acces.personne;
import Gestion_acces.personneInexistante;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import bdd.objetsdao.PersonneDAO;

public class AnnuaireImpl extends AnnuairePOA{

	private PersonneDAO repoPersonne;
	
	public AnnuaireImpl() {
		repoPersonne = new PersonneDAO();
	}
	
	@Override
	public personne identifier(short id) throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-identifier");
		Personne persBD = null;
		personne persORB = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		persBD = repoPersonne.find(id);

		if (persBD == null)
			throw new personneInexistante(id);
		else
			persORB = personneBDtoORB(persBD);
		
		return persORB;
	}

	@Override
	public personne demanderIdentite(String ph) throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-identifier");
		Personne persBD = null;
		personne persORB = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		persBD = repoPersonne.findByPicture(ph);

		if (persBD == null)
			throw new personneInexistante((short)0);
		else
			persORB = personneBDtoORB(persBD);
		
		return persORB;
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
	
	private personne personneBDtoORB(Personne p) {
		statutPersonne statut;
		rolePersonne role;
		
		switch (p.getStatutPersonne()) {
			case "temporaire":
				statut = statutPersonne.temporaire;
				break;
			case "permanent" :
				statut = statutPersonne.permanent;
				break;
			default :
				statut = statutPersonne.permanent;
				break;
		}
		switch (p.getRolePersonne()) {
		case "RH":
			role = rolePersonne.RH;
			break;
		case "accueil" :
			role = rolePersonne.accueil;
			break;
		case "basique" :
			role = rolePersonne.basique;
			break;
		default :
			role = rolePersonne.basique;
			break;
	}
		return new personne((short) p.getIdPersonne(), p.getNomPersonne(), p.getPrenomPersonne(), p.getPhotoPersonne(), statut, role);	
	}

}
