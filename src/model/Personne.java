package model;

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
	
	@Override
	public String toString(){
		return 	"id: " + getIdPersonne()+ "\n" 
		+		"nom: " + getNomPersonne()+ "\n" 
		+		"prenom: " + getPrenomPersonne()+ "\n" 
		+		"photo: " + getPhotoPersonne()+ "\n" 
		+		"statut: " + getStatutPersonne()+ "\n"
		+		"role: " + getRolePersonne()+ "\n"; 
		
	}
}




