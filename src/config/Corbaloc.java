package config;

public class Corbaloc {

	private final static String corbaRef = "corbaloc:iiop:1.2@192.168.43.104:2001/NameService";
	
	public static String getCorbaRef() {
		return corbaRef;
	}
}
