package authentification;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import autorisation.ServeurAutorisationImpl;
import config.Corbaloc;

public class SrvServeurAuthentification {
	
	public static void main(String[] args) {
		try {		
	        // Creation du servant
	        //*********************
	        ServeurAuthentificationImpl monServeurAuthentification = new ServeurAuthentificationImpl();

	        String nomObj = "Autorisation";
	        ORB orb = Corbaloc.registerServant(args, monServeurAuthentification , nomObj);        
	        // Lancement de l'ORB et mise en attente de requete
	        //**************************************************
	        orb.run();
		    }
			catch (Exception e) {
				e.printStackTrace();
			}
		} // main

}
