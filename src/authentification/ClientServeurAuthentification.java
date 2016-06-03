package authentification;

import Gestion_acces.ServeurAuthentification;
import Gestion_acces.ServeurAuthentificationHelper;

public class ClientServeurAuthentification {
	public static ServeurAuthentification monServeurAuthentification;

	public ClientServeurAuthentification() {

		try {

			// Intialisation de l'orb
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null,null);
	
	        String idObj = "Authentification";
	
	        // Recuperation du naming service
	        org.omg.CosNaming.NamingContext nameRoot =
	        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
	
	        // Construction du nom a rechercher
	        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
	         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");
	
	        // Recherche aupres du naming service
	        org.omg.CORBA.Object distantServeurAuthentification = nameRoot.resolve(nameToFind);
	        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
	        System.out.println(orb.object_to_string(distantServeurAuthentification));
	
	        
	        // Casting de l'objet CORBA
	        monServeurAuthentification = ServeurAuthentificationHelper.narrow(distantServeurAuthentification);
        
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ServeurAuthentification getMonAuthentification() {
		return monServeurAuthentification;
	}
}
