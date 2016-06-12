package model;

public class Porte {

	private int idPorte;
	private String libellePorte;
	private int refZone;
	
	public Porte(int p_idPorte, String p_libellePorte, int p_refZone) {
		this.idPorte = p_idPorte;
		this.libellePorte = p_libellePorte;
		this.refZone = p_refZone;
	}
	
	public Porte() {
	}


	public int getIdPorte() {
		return idPorte;
	}
	public void setIdPorte(int idPorte) {
		this.idPorte = idPorte;
	}
	public String getLibellePorte() {
		return libellePorte;
	}
	public void setLibellePorte(String libellePorte) {
		this.libellePorte = libellePorte;
	}
	public int getRefZone() {
		return refZone;
	}
	public void setRefZone(int refZone) {
		this.refZone = refZone;
	}

	@Override
	public String toString(){
		return getLibellePorte();
		
	}
	
}
