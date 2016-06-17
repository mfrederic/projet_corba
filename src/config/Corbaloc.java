package config;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.Servant;

public class Corbaloc {

	private final static String corbaRef = "corbaloc:iiop:1.2@192.168.43.104:2001/NameService";
	
	public static String getCorbaRef() {
		return corbaRef;
	}
	
	public static ORB registerServant(String[] args, Servant inst,String nomObj) throws Exception{
		
        // Intialisation de l'ORB
        //************************
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);

        // Gestion du POA
        //****************
        // Recuperation du POA
        POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        
        byte[] monServeurId = rootPOA.activate_object(inst);
        
     // Activer le POA manager
        rootPOA.the_POAManager().activate();
        
        // Enregistrement dans le service de nommage
        //*******************************************
        // Recuperation du naming service
        //NamingContext nameRoot=org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));
        NamingContext nameRoot=NamingContextHelper.narrow(orb.string_to_object(Corbaloc.getCorbaRef()));
        
        // Construction du nom a enregistrer
        org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
        nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomObj,"");
        

        // Enregistrement de l'objet CORBA dans le service de noms
        nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(inst));
        System.out.println("==> Nom '"+ nomObj + "' est enregistre dans le service de noms.");
        
        return orb;
		
	}
}
