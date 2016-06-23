package authentification;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import autorisation.ServeurAutorisationImpl;
import config.Corbaloc;

public class ServeurAuthentification {
	
	public static void main(String[] args) {
		try {		
	        // Creation du servant de la classe souhaitée
	        //*********************
	        ServeurAuthentificationImpl monServeurAuthentification = new ServeurAuthentificationImpl();
	        //Appel à la méthode générique de lancement de serveur
	        ORB orb = Corbaloc.registerServant(args, monServeurAuthentification);        
	        // Lancement de l'ORB et mise en attente de requete
	        //**************************************************
	        orb.run();
		    }
			catch (Exception e) {
				e.printStackTrace();
			}
		} 

}
