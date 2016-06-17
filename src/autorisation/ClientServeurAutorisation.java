package autorisation;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import Gestion_acces.ServeurAutorisation;
import Gestion_acces.ServeurAutorisationHelper;
import config.Corbaloc;

public class ClientServeurAutorisation {
	public static ServeurAutorisation monServeurAutorisation;

	public ClientServeurAutorisation() {

		try {

			// Intialisation de l'orb
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null,null);
	
	        String idObj = "Autorisation";
	
	        // Recuperation du naming service
	        /*org.omg.CosNaming.NamingContext nameRoot =
	        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
	*/
	        NamingContext nameRoot=NamingContextHelper.narrow(orb.string_to_object(Corbaloc.getCorbaRef()));
	        
	        // Construction du nom a rechercher
	        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
	         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");
	
	        // Recherche aupres du naming service
	        org.omg.CORBA.Object distantServeurAutorisation = nameRoot.resolve(nameToFind);
	        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
	        System.out.println(orb.object_to_string(distantServeurAutorisation));
	
	        
	        // Casting de l'objet CORBA
	        monServeurAutorisation = ServeurAutorisationHelper.narrow(distantServeurAutorisation);
        
			}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ServeurAutorisation getMonAutorisation() {
		return monServeurAutorisation;
	}
}