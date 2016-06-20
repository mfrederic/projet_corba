package model;

import Gestion_acces.personne;
import Gestion_acces.rolePersonne;

public class Personne {
	public final String[] STATUT = {"temporaire", "permanent"};
	public final String[] ROLE = {"RH", "accueil","basique"};
	
	private int idPersonne;
	private String nomPersonne;
	private String prenomPersonne;
	private String photoPersonne;
	private String statutPersonne;
	private String rolePersonne;

	public int getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(int idPersonne) {
		this.idPersonne = idPersonne;
	}
	public String getNomPersonne() {
		return nomPersonne;
	}
	public void setNomPersonne(String nomPersonne) {
		this.nomPersonne = nomPersonne;
	}
	public String getPrenomPersonne() {
		return prenomPersonne;
	}
	public void setPrenomPersonne(String prenomPersonne) {
		this.prenomPersonne = prenomPersonne;
	}
	public String getPhotoPersonne() {
		return photoPersonne;
	}
	public void setPhotoPersonne(String photoPersonne) {
		this.photoPersonne = photoPersonne;
	}
	public String getStatutPersonne() {
		return statutPersonne;
	}
	public void setStatutPersonne(String statutPersonne) {
		for(String value : STATUT){
			if(value.equals(statutPersonne))
				this.statutPersonne = statutPersonne;
		}
	}
	public String getRolePersonne() {
		return rolePersonne;
	}
	public void setRolePersonne(String rolePersonne) {
		for(String value : ROLE){
			if(value.equals(rolePersonne))
				this.rolePersonne = rolePersonne;
		}
		
	}

		
	public Personne(String nom, String prenom, String photo, String statut, String role)
	{
		setNomPersonne(nom);
		setPrenomPersonne(prenom);
		setPhotoPersonne(photo);
		setStatutPersonne(statut);
		setRolePersonne(role);

	}
	
	public Personne(int id,String nom, String prenom, String photo, String statut, String role)
	{
		setIdPersonne(id);
		setNomPersonne(nom);
		setPrenomPersonne(prenom);
		setPhotoPersonne(photo);
		setStatutPersonne(statut);
		setRolePersonne(role);

	}
	public Personne(){
		
	}
	
	public Personne(personne p) {
		setIdPersonne(p.idPers);
		setNomPersonne(p.nom);
		setPrenomPersonne(p.prenom);
		setPhotoPersonne(p.ph);
		setStatutPersonne(p.statut.toString());
		setRolePersonne(p.role.toString());
	}
	
	public static personne nonNullPersonne(personne p) {
		personne pers = p;
		if(pers.nom == null)
			pers.nom = new String();
		if(pers.prenom == null)
			pers.prenom = new String();
		if(pers.ph == null)
			pers.ph = new String();
		return pers;
	}
	
	@Override
	public String toString(){
		return 	"id: " + getIdPersonne()+ "\n" 
		+		"nom: " + getNomPersonne()+ "\n" 
		+		"prenom: " + getPrenomPersonne()+ "\n" 
		+		"photo: " + getPhotoPersonne()+ "\n" 
		+		"statut: " + getStatutPersonne()+ "\n"
		+		"role: " + getRolePersonne()+ "\n"; 
		
	}
	@Override
	public  boolean equals(Object o) {  
		if(!(o instanceof Personne))
		{
			return false;
		}
		
		Personne personne = (Personne)o;
		
		if(personne.getIdPersonne()!=0 && this.getIdPersonne()!=0){
			if(this.getNomPersonne().equals(personne.getNomPersonne()))
				if(this.getPrenomPersonne().equals(personne.getPrenomPersonne()))
					return true;
		}
		else
			return personne.getIdPersonne()==this.getIdPersonne();
		return false;
	}
}




