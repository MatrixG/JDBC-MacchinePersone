package model;

public class Macchina {
	
	private String modello;
	private String targa;
	
	public Macchina(){
		
	}
	
	public Macchina(String modello, String targa) {
	
		this.modello = modello;
		this.targa = targa;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}
}
