package log;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import annuaire.AnnuaireImpl;
import config.Corbaloc;

public class ServeurJournal {
	public static void main(String[] args) {
		try {
		        // Creation du servant
		        //*********************
		        JournalImpl monJournal = new JournalImpl();




		        ORB orb = Corbaloc.registerServant(args, monJournal);
		        
		        // Lancement de l'ORB et mise en attente de requete
		        //**************************************************
		        orb.run();

		    }
			catch (Exception e) {
				e.printStackTrace();
			}
		} // main
}
