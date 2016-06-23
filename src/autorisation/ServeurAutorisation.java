package autorisation;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.Servant;

import config.Corbaloc;

public class ServeurAutorisation {
	public static void main(String[] args) {
		try {
		        // Creation du servant
		        //*********************
		        ServeurAutorisationImpl monServeurAutorisation = new ServeurAutorisationImpl();

		        ORB orb = Corbaloc.registerServant(args, monServeurAutorisation);
		        
		        // Lancement de l'ORB et mise en attente de requete
		        //**************************************************
		        orb.run();

		    }
			catch (Exception e) {
				e.printStackTrace();
			}
		} // main

}
