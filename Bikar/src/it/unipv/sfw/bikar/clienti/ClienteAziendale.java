package it.unipv.sfw.bikar.clienti;

public class ClienteAziendale extends Cliente{
	private String ragioneSociale;
	
	public ClienteAziendale(String id, String ragioneSociale) {
		super(id);
		this.ragioneSociale = ragioneSociale;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}
}
