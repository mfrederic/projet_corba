package config;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.Servant;

public class Corbaloc {

	private final static String corbaRef = "corbaloc:iiop:1.2@127.0.0.1:2001/NameService";

	public static String getCorbaRef() {
		return corbaRef;
	}

	public static ORB registerServant(String[] args, Servant inst) throws Exception{
		String objectName= new String();
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
		// Recuperation du naming service
		NamingContext nameRoot=NamingContextHelper.narrow(orb.string_to_object(Corbaloc.getCorbaRef()));
		// Construction du nom a enregistrer
		org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
		switch(inst.getClass().getSimpleName()){
		case"ServeurAuthentificationImpl" : 
			objectName = new String("Authentification");
			break;
		case "AnnuaireImpl":
			objectName = new String("Annuaire");
			break;
		case "ServeurAutorisationImpl":
			objectName = new String("Autorisation");
			break;
		case "JournalImpl":
			objectName = new String("Journal");
			break;
		default:
			throw new Exception("Invalid Servant");			

		}
		nameToRegister[0] = new org.omg.CosNaming.NameComponent(objectName,"");
		// Enregistrement de l'objet CORBA dans le service de noms
		nameRoot.rebind(nameToRegister,rootPOA.servant_to_reference(inst));
		System.out.println("==> Nom '"+ objectName + "' est enregistre dans le service de noms.");
		return orb;
	}
}
