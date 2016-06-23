package annuaire;

import java.util.ArrayList;

import model.Personne;
import Gestion_acces.AnnuairePOA;
import Gestion_acces.personne;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.AnnuairePackage.personneInexistante;
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
		Personne persBD;
		personne persORB;
        persORB = new personne((short)0,"nom","prenom","photo", statutPersonne.permanent, rolePersonne.basique);

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
		personne persORB;
        persORB = new personne((short)0,"nom","prenom","photo", statutPersonne.permanent, rolePersonne.basique);

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
		Personne pers = new Personne();
		
		// BD
		pers.setNomPersonne(nom);
		pers.setPrenomPersonne(prenom);
		pers.setStatutPersonne(statut.toString());
		pers.setRolePersonne(role.toString());
		pers.setPhotoPersonne(new String());

		pers = repoPersonne.create(pers);

		return (short) pers.getIdPersonne();
	}

	@Override
	public void ajouterPhoto(short idPersonne, String ph)
			throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-ajouterPhoto");
		Personne pers = new Personne();
		
		pers = repoPersonne.find(idPersonne);

		if (pers == null)
			throw new personneInexistante(idPersonne);
		else {
			
			// BD
			pers.setNomPersonne(pers.getNomPersonne());
			pers.setPrenomPersonne(pers.getPrenomPersonne());
			pers.setStatutPersonne(pers.getStatutPersonne().toString());
			pers.setRolePersonne(pers.getRolePersonne().toString());
			pers.setPhotoPersonne(ph);			
			repoPersonne.update(pers);
			System.out.println("Photo changée");
		}
		
	}

	@Override
	public void modifierInfos(short idPersonne, String nom, String prenom,
			statutPersonne statut, rolePersonne role)
			throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-modifierInfos");
		Personne pers = new Personne();
		
		pers = repoPersonne.find(idPersonne);

		if (pers == null)
			throw new personneInexistante(idPersonne);
		else {
			// BD
			pers.setNomPersonne(nom);
			pers.setPrenomPersonne(prenom);
			pers.setStatutPersonne(statut.toString());
			pers.setRolePersonne(role.toString());
			if (pers.getPhotoPersonne() == null)
				pers.setPhotoPersonne(new String());
			else
				pers.setPhotoPersonne(pers.getPhotoPersonne());
			repoPersonne.update(pers);
			System.out.println("Infos modifiées");
		}
	}

	@Override
	public void supprimerPersonne(short idPersonne) throws personneInexistante {
		// TODO Auto-generated method stub
		System.out.println("Annuaire-supprimerPersonne");
		Personne pers = new Personne();
		
		pers = repoPersonne.find(idPersonne);

		if (pers == null)
			throw new personneInexistante(idPersonne);
		else {
			
			// BD
			repoPersonne.delete(pers);
		}
	}

	@Override
	public personne[] chercherPersonnes(String nom, String prenom) {
		ArrayList<Personne> listPersons;
		personne[] listePersORB;
		listePersORB = null;
		if (nom.isEmpty() && prenom.isEmpty())
			listPersons = repoPersonne.getInstances();
		else
			listPersons = repoPersonne.getByNomPrenom(nom, prenom);

		if (listPersons == null)
			listePersORB = new personne[0];		
		else {
			Personne[] listePersBD = new Personne[listPersons.size()];
			listePersBD = (Personne[]) listPersons.toArray(listePersBD);
			listePersORB = new personne[listePersBD.length];
			for (int i=0; i<listePersBD.length; i++) {
			   listePersORB[i] = personneBDtoORB((Personne) listePersBD[i]);
			}
		}
		return listePersORB;
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
