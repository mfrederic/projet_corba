package model;

public class Autorisation {
	
	private int numAuto;
	private int refPersonne;
	private float heureDebut;
	private float heureFin;
	private String jourDebut;
	private String jourFin;
	private int refZone;
	
	public Autorisation(int p_refPersonne, float p_heureDebut, float p_heureFin, String p_jourDebut, String p_jourFin, int p_refZone) {

		this.refPersonne = p_refPersonne;
		this.heureDebut = p_heureDebut;
		this.heureFin = p_heureFin;
		this.jourDebut = p_jourDebut;
		this.jourFin = p_jourFin;
		this.refZone = p_refZone;
	}
	
	public Autorisation() {
	}


	
	public int getNumAuto() {
		return numAuto;
	}
	public void setNumAuto(int numAuto) {
		this.numAuto = numAuto;
	}
	public int getRefPersonne() {
		return refPersonne;
	}
	public void setRefPersonne(int refPersonne) {
		this.refPersonne = refPersonne;
	}
	public float getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(float heureDebut) {
		this.heureDebut = heureDebut;
	}
	public float getHeureFin() {
		return heureFin;
	}
	public void setHeureFin(float heureFin) {
		this.heureFin = heureFin;
	}
	public String getJourDebut() {
		return jourDebut;
	}
	public void setJourDebut(String jourDebut) {
		this.jourDebut = jourDebut;
	}
	public String getJourFin() {
		return jourFin;
	}
	public void setJourFin(String jourFin) {
		this.jourFin = jourFin;
	}
	public int getRefZone() {
		return refZone;
	}
	public void setRefZone(int refZone) {
		this.refZone = refZone;
	}

	@Override
	public String toString(){
		return 	"id: " + getNumAuto()+ "\n" 
		+		"ref personne: " + getRefPersonne() + "\n" 
		+		"heure debut: " + getHeureDebut() + "\n" 
		+		"heure fin: " + getHeureFin() + "\n" 
		+		"jour debut: " + getJourDebut() + "\n"
		+		"jour fin: " + getJourFin() + "\n"
		+		"ref zone: " + getRefZone() + "\n"; 
		
	}
	
}
