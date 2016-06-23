package annuaire;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import config.Corbaloc;

public class ServeurAnnuaire {
	public static void main(String[] args) {
		try {
		        // Creation du servant
		        //*********************
		        AnnuaireImpl monAnnuaire = new AnnuaireImpl();
		        //Initialisation du POA et enregistrement dans le namespace
		        ORB orb = Corbaloc.registerServant(args, monAnnuaire);
		        
		        // Lancement de l'ORB et mise en attente de requete
		        //**************************************************
		        orb.run();

		    }
			catch (Exception e) {
				e.printStackTrace();
			}
		} // main
}
