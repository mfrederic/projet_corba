package autorisation;

public class Porte {

	private int idPorte, refZone;
	private String libellePorte;

	public Porte() {
		idPorte = 0;
		refZone = 0;
		libellePorte= "";
	}
	
	public Porte(int idPorte, String libellePorte, int refZone) {
		this.idPorte = idPorte;
		this.refZone = refZone;
		this.libellePorte = libellePorte;
	}

	public int getIdPorte() {
		return idPorte;
	}

	public void setIdPorte(int idPorte) {
		this.idPorte = idPorte;
	}

	public int getRefZone() {
		return refZone;
	}

	public void setRefZone(int refZone) {
		this.refZone = refZone;
	}

	public String getLibellePorte() {
		return libellePorte;
	}

	public void setLibellePorte(String libellePorte) {
		this.libellePorte = libellePorte;
	}
	
}
