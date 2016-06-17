package annuaire;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;

import Gestion_acces.Annuaire;
import Gestion_acces.AnnuaireHelper;
import config.Corbaloc;

public class ClientAnnuaire {
	public static Annuaire monAnnuaire;

	public ClientAnnuaire() {

		try {

			// Intialisation de l'orb
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null,null);
	
	        String idObj = "Annuaire";
	
	        // Recuperation du naming service
	        /*org.omg.CosNaming.NamingContext nameRoot =
	        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
	*/
	        NamingContext nameRoot=NamingContextHelper.narrow(orb.string_to_object(Corbaloc.getCorbaRef()));
	        
	        // Construction du nom a rechercher
	        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
	         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");
	
	        // Recherche aupres du naming service
	        org.omg.CORBA.Object distantAnnuaire = nameRoot.resolve(nameToFind);
	        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
	        System.out.println(orb.object_to_string(distantAnnuaire));
	
	        
	        // Casting de l'objet CORBA
	        monAnnuaire = AnnuaireHelper.narrow(distantAnnuaire);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} // main
	
	public Annuaire getMonAnnuaire(){
		return monAnnuaire;
	}
}
