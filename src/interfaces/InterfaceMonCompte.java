package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Gestion_acces.accesRefuse;
import Gestion_acces.droitsInsuffisants;
import Gestion_acces.personne;
import Gestion_acces.statutPersonne;
import Gestion_acces.ServeurAuthentificationPackage.compteInexistant;
import authentification.ClientServeurAuthentification;

public class InterfaceMonCompte {

	private static ClientServeurAuthentification monAuthentification;
	
	private static final String cleServeur = "stp";

	private static boolean authReussie;
	
	public static void main(String args[]) {

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
						System.out.println(e.toString());
					}
			}
			
			System.out.println("Nouveau mot de passe :");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			modifierMdp(s[0], in.readLine());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private static void authentifier(String user, String password) throws droitsInsuffisants {
		try {
			personne p = monAuthentification.getMonAuthentification().authentifier(user, password, cleServeur);
			if (p.idPers == 0) {
				authReussie = false;
				System.out.println("Erreur identification personne");
			}
			else {
				if (p.statut == statutPersonne.permanent)
					authReussie = true;
				else {
					authReussie = false;
					throw new droitsInsuffisants("Rôle doit être permanent");
				}
			}
				
				
		} catch (compteInexistant | droitsInsuffisants | accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
	
	private static void modifierMdp(String user, String newMdp) {
		try {
			monAuthentification.getMonAuthentification().modifierMdp(user, newMdp, cleServeur);
		} catch (compteInexistant | accesRefuse e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
	}
}
