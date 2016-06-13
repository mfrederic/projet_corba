package authentification;

import model.Compte;
import Gestion_acces.ServeurAuthentificationPOA;
import Gestion_acces.personne;
import Gestion_acces.rolePersonne;
import Gestion_acces.statutPersonne;
import Gestion_acces.AnnuairePackage.personneInexistante;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteDejaCree;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import Gestion_acces.ServeurAuthentificationPackage.empreinteDejaExistante;
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
					System.out.println("Personne inexistante dans la base (id = " + e.id + ")");
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
						System.out.println("Personne inexistante dans la base (id = " + e.id + ")");
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
			throws accesRefuse, compteInexistant, empreinteDejaExistante {
		// TODO Auto-generated method stub
		System.out.println("Auth-ajouterEmpreinte");
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			Compte cmpt = new Compte();
			
			// BD
			cmpt = repoCompte.findByUser(user);
					
			if (cmpt == null) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else if(cmpt.getEmpreinte() == null) { // le compte existe et pas d'empreinte
				
				// BD
				cmpt.setEmpreinte(emp);
				repoCompte.update(cmpt);

				System.out.println("Empreinte ajoutée");
			} else {
				throw new empreinteDejaExistante(cmpt.getEmpreinte());
			}
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
		
	}
	
	@Override
	public void modifierEmpreinte(String user, String emp, String mdp)
			throws droitsInsuffisants, accesRefuse, compteInexistant {
		// TODO Auto-generated method stub
		System.out.println("Auth-ajouterEmpreinte");
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			Compte cmpt = new Compte();
			
			// BD
			cmpt = repoCompte.findByUser(user);
					
			if (cmpt == null) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else { // le compte existe et pas d'empreinte
				
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
		personne p = new personne((short)0,"nom","prenom","photo",statutPersonne.permanent,rolePersonne.basique);
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			Compte cmpt = new Compte();
			
			// BD
			cmpt = repoCompte.findByUser(user);
			
			if (cmpt == null) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else { // le compte existe
				
				try {
					p = monAnnuaire.getMonAnnuaire().identifier((short) cmpt.getRefPersonne());
					
					if (p.statut == statutPersonne.temporaire) {
						System.out.println("Empreinte supprimée");
						
						// BD
						cmpt.setEmpreinte(new String());
						cmpt = repoCompte.update(cmpt);
						
					} else
						throw new suppressionInterdite(p.role);
				} catch (personneInexistante e) {
					// TODO Auto-generated catch block
					System.out.println("Personne inexistante dans la base (id = " + e.id + ")");
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
		
		if (cleServeur.equals(mdp)) { // Clé serveur
			Compte cmpt = null;
			
			// BD
			cmpt = repoCompte.findByUser(user);
					
			if (cmpt != null) // Contrôle de l'inexistance du user dans la base
				throw new compteDejaCree(user);
			
			else { // le compte n'existe pas
				cmpt = new Compte();
				
				// BD
				cmpt.setRefPersonne(idPersonne);
				cmpt.setPassword(password);
				cmpt.setUser(user);
				cmpt.setEmpreinte(new String());
				cmpt = repoCompte.create(cmpt);

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
		
		if (cleServeur.equals(mdpServeur)) { // Clé serveur
			Compte cmpt = new Compte();
			
			// BD
			cmpt = repoCompte.findByUser(user);
					
			if (cmpt == null) // Contrôle de l'existance du user dans la base
				throw new compteInexistant(user);
			else { // le compte existe
				
				// BD
				cmpt.setPassword(newMdp);
				cmpt = repoCompte.update(cmpt);
				System.out.println("Mot de passe modifié");
			}
		} else {
			throw new accesRefuse("Mot de passe serveur faux");
		}
	}

}
