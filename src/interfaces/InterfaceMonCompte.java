package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gestion_acces.personne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.accesRefuse;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import Gestion_acces.ServeurAuthentificationPackage.droitsInsuffisants;
import authentification.ClientServeurAuthentification;

public class InterfaceMonCompte {

	private ClientServeurAuthentification monAuthentification;
	
	private static final String cleServeur = "stp";
	
	private String message;
	private String userConnecte;
	
	public InterfaceMonCompte() {
		message = "";
		userConnecte = "";
	}
	
	public static void main(String args[]) {
/*
		monAuthentification = new ClientServeurAuthentification();
		
		authReussie = false;
		
		try {
			String[] s = null;
			
			while (!authReussie) {
				System.out.println("user;password");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				s = in.readLine().split(";");
				
				if (s.length > 1)
					try {
						authentifier(s[0], s[1]);
					} catch (droitsInsuffisants e) {
						// TODO Auto-generated catch block
						System.out.println("Droits insuffisants : " + e.raison);
					}
			}
			
			System.out.println("Nouveau mot de passe :");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			modifierMdp(s[0], in.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/	
	}
	
	public boolean authentifier(String user, String password) throws droitsInsuffisants {
		boolean authReussie = false;
		try {
			personne p = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			if (p.idPers == 0) {
				authReussie = false;
				message = "Erreur identification personne";
			}
			else {
				if (p.statut == statutPersonne.permanent) {
					authReussie = true;
					userConnecte = user;
				}
				else {
					authReussie = false;
					throw new droitsInsuffisants("Rôle doit être permanent");
				}
			}
						
		} catch (compteInexistant e) {
			// TODO Auto-generated catch block
			System.out.println("Compte inexistant : (user: " + e.user + ")");
		} catch (droitsInsuffisants e) {
			// TODO Auto-generated catch block
			System.out.println("Droits insuffisants : " + e.raison);
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println("Accès refusé : " + e.raison);
		}
		return authReussie;
	}
	
	public void modifierMdp(String user, String newMdp) {

		try {
			monAuthentification.getMonAuthentification().modifierMdp(user, newMdp, cleServeur);
		} catch (compteInexistant e) {
			// TODO Auto-generated catch block
			System.out.println("Compte inexistant : (user: " + e.user + ")");
		} catch (accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println("Accès refusé : " + e.raison);
		}

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserConnecte() {
		return userConnecte;
	}

	public void setUserConnecte(String userConnecte) {
		this.userConnecte = userConnecte;
	}
	
	
}
