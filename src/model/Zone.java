package model;

public class Zone {
	
	private int idZone;
	private String libelleZone;
	private int respZone;
	
	public Zone(int p_idZone, String p_libelleZone, int p_respZone) {
		this.idZone = p_idZone;
		this.libelleZone = p_libelleZone;
		this.respZone = p_respZone;
	}
	
	public Zone() {
	}

	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	public String getLibelleZone() {
		return libelleZone;
	}
	public void setLibelleZone(String libelleZone) {
		this.libelleZone = libelleZone;
	}
	public int getRespZone() {
		return respZone;
	}
	public void setRespZone(int respZone) {
		this.respZone = respZone;
	}

	@Override
	public String toString(){
		return 	"id: " + getIdZone() + "\n" 
		+		"libelle zone: " + getLibelleZone() + "\n" 
		+		"responsable zone: " + getRespZone() + "\n";
		
	}
}
