package log;

import Gestion_acces.SrvJournal;
import Gestion_acces.SrvJournalHelper;


public class ClientJournal {
	public static SrvJournal monJournal;

	public ClientJournal() {

		try {

			// Intialisation de l'orb
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init((String[])null,null);
	
	        String idObj = "Journal";
	
	        // Recuperation du naming service
	        org.omg.CosNaming.NamingContext nameRoot =
	        		org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
	
	        // Construction du nom a rechercher
	        org.omg.CosNaming.NameComponent[] nameToFind = new org.omg.CosNaming.NameComponent[1];
	         nameToFind[0] = new org.omg.CosNaming.NameComponent(idObj,"");
	
	        // Recherche aupres du naming service
	        org.omg.CORBA.Object distantJournal = nameRoot.resolve(nameToFind);
	        System.out.println("Objet '" + idObj + "' trouve aupres du service de noms. IOR de l'objet :");
	        System.out.println(orb.object_to_string(distantJournal));
	
	        
	        // Casting de l'objet CORBA
	        monJournal = SrvJournalHelper.narrow(distantJournal);

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	} // main
	
	public SrvJournal getMonJournal(){
		return monJournal;
	}
}
