package authentification;

import model.Compte;
import Gestion_acces.ServeurAuthentificationPOA;
import Gestion_acces.accesRefuse;
import Gestion_acces.droitsInsuffisants;
import Gestion_acces.personne;
import Gestion_acces.personneInexistante;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.compteDejaCree;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.suppressionInterdite;
import annuaire.ClientAnnuaire;
import bdd.objetsdao.CompteDAO;

public class ServeurAuthentificationImpl extends ServeurAuthentificationPOA{
	private ClientAnnuaire monAnnuaire;
	
	private CompteDAO repoCompte;
	
	private static final String cleServeur = "stp";
	
	public ServeurAuthentificationImpl() {
		monAnnuaire = new ClientAnnuaire();
		repoCompte = new CompteDAO();
	}
	
	@Override
	public personne demanderAuth(String empr, String ph, String mdp)
			throws accesRefuse {
		// TODO Auto-generated method stub
		System.out.println("Auth-demanderAuth");
		
		personne p = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);

		if (cleServeur.equals(mdp)) {
			int refPersonne = 0;

			//BD
			refPersonne = repoCompte.findByEmpreinte(empr);
			System.out.println(refPersonne);
			
			if (refPersonne == 0)
				throw new accesRefuse("Empreinte inconnue");
			else {
				try {
					p = monAnnuaire.getMonAnnuaire().demanderIdentite(ph);
					if (refPersonne != p.idPers)
						throw new accesRefuse("Empreinte et photo différentes");
				} catch (personneInexistante e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}
			}
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
		
		System.out.println(p.prenom + " " + p.nom);
		
		return p;
	}

	@Override
	public personne authentifier(String user, String password, String mdp)
			throws compteInexistant, droitsInsuffisants, accesRefuse {
		// TODO Auto-generated method stub
		System.out.println("Auth-authentifier");
		
		personne p = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			Compte cmpt = new Compte();
			
			// BD
			cmpt = repoCompte.findByUser(user);
					
			if (cmpt == null) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else { // le compte existe
			
				if (!cmpt.getPassword().equals(password)) // Contrôle du mdp utilisateur
					throw new droitsInsuffisants("Mauvais mot de passe");
				else { // Mot de passe bon
					try {
						p = monAnnuaire.getMonAnnuaire().identifier((short)cmpt.getRefPersonne());
					} catch (personneInexistante e) {
						// TODO Auto-generated catch block
						System.out.println(e.toString());
					}
				}
			}
			
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
		
		System.out.println(p.prenom + " " + p.nom);
		
		return p;
	}

	@Override
	public void ajouterEmpreinte(String user, String emp, String mdp)
			throws accesRefuse, compteInexistant {
		// TODO Auto-generated method stub
		System.out.println("Auth-ajouterEmpreinte");
		short refPers = 0;
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			Compte cmpt = new Compte();
			
			// BD
			cmpt = repoCompte.findByUser(user);
					
			if (cmpt == null) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else { // le compte existe
				
				// BD
				cmpt.setEmpreinte(emp);
				repoCompte.update(cmpt);

				System.out.println("Empreinte ajoutée");
			}
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
	}

	@Override
	public void supprimerEmpreinte(String user, String mdp)
			throws accesRefuse, compteInexistant, suppressionInterdite {
		// TODO Auto-generated method stub
		System.out.println("Auth-supprimerEmpreinte");
		short refPers = 0;
		personne p = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			
			// refPers = select refPersonne where user = this.user
			refPers = 1;
					
			if (refPers == 0) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else { // le compte existe
				
				try {
					p = monAnnuaire.getMonAnnuaire().identifier(refPers);
					
					if (p.statut == statutPersonne.temporaire)
						System.out.println("Empreinte supprimée");
						// set empreinte = null from compte where user = user
					else
						throw new suppressionInterdite(p.role);
				} catch (personneInexistante e) {
					// TODO Auto-generated catch block
					System.out.println(e.toString());
				}			
			}
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
	}

	@Override
	public void creerCompte(short idPersonne, String user, String password,
			String mdp) throws compteDejaCree, accesRefuse {
		// TODO Auto-generated method stub
		System.out.println("Auth-creerCompte");
		short refPers = 0;
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			// refPers = select refPersonne where user = this.user
			refPers = 1;
					
			if (refPers != 0) // Contrôle de l'inexistance du user dans la base
				throw new compteDejaCree(user, password, refPers);
			
			else { // le compte n'existe pas
				// insert into compte values(user, password,idPersonne);
				System.out.println("Compte créé");
			}
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
	}

	@Override
	public void modifierMdp(String user, String newMdp, String mdpServeur)
			throws accesRefuse, compteInexistant {
		// TODO Auto-generated method stub
		System.out.println("Auth-modifierMdp");
		short refPers = 0;
		
		if (cleServeur.equals(mdpServeur)) { // Clé serveur
			// refPers = select refPersonne where user = this.user
			refPers = 1;
					
			if (refPers == 0) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else { // le compte existe
				System.out.println("Mot de passe modifié");
			}
			// Set mdp = newMdp where user = user
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
	}

}
