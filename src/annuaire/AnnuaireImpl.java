package annuaire;

import java.util.ArrayList;
import java.util.Iterator;

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
		Personne pers = new Personne();
		short id = 0;
		
		// BD
		pers.setNomPersonne(nom);
		pers.setPrenomPersonne(prenom);
		pers.setStatutPersonne(statut.toString());
		pers.setRolePersonne(role.toString());
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

			System.out.println("Infos modifiées");
		}
	}

	@Override
	public personne[] chercherPersonnes(String nom, String prenom) {
		// TODO Auto-generated method stub
		
		ArrayList<Personne> listePersonnes = repoPersonne.getByNomPrenom(nom, prenom);
		personne[] listePersORB = new personne[0];

		if (listePersonnes == null)
			return new personne[0];
		
		else {
			Personne[] listePersBD = (Personne[]) listePersonnes.toArray();
			listePersORB = new personne[listePersBD.length];
			for (int i=0; i<listePersBD.length; i++) {
			   listePersORB[i] = personneBDtoORB(listePersBD[i]);
			}
		   
		   return listePersORB;
		}
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
