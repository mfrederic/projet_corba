package model;

public class Compte {
	
	private String user;
	private String password;
	private String empreinte;
	private int refPersonne;

	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmpreinte() {
		return empreinte;
	}
	public void setEmpreinte(String empreinte) {
		this.empreinte = empreinte;
	}
	public int getRefPersonne() {
		return refPersonne;
	}
	public void setRefPersonne(int refPersonne) {
		this.refPersonne = refPersonne;
	}
	
	public Compte (String user, String password, String empreinte, int refPersonne){
		setUser(user);
		setPassword(password);
		setEmpreinte(empreinte);
		setRefPersonne(refPersonne);
	}
	public Compte(){
		
	}
	@Override
	public String toString(){
		return 	"idref: " + getRefPersonne()+ "\n" 
		+		"user: " + getUser()+ "\n" 
		+		"password: " + getPassword() + "\n" 
		+		"empreinte: " + getEmpreinte()+ "\n" ;
		
	}

}

