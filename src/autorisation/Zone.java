package autorisation;

public class Zone {
	
	private int idZone, respZone;
	private String libelleZone;
	
	public Zone() {
		idZone = 0;
		respZone = 0;
		libelleZone= "";
	}
	
	public Zone(int idZone, String libelleZone, int respZone) {
		this.idZone = idZone;
		this.respZone = respZone;
		this.libelleZone = libelleZone;
	}

	public int getIdZone() {
		return idZone;
	}

	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}

	public int getRespZone() {
		return respZone;
	}

	public void setRespZone(int respZone) {
		this.respZone = respZone;
	}

	public String getLibelleZone() {
		return libelleZone;
	}

	public void setLibelleZone(String libelleZone) {
		this.libelleZone = libelleZone;
	}

	
	
}
