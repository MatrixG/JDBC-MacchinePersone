package model;

public class Persona {
	
	private String nome;
	private String cognome;
	private String CodF;
	
	public Persona() {
	}

	public Persona(String nome, String cognome, String codF) {
		
		this.nome = nome;
		this.cognome = cognome;
		CodF = codF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCodF() {
		return CodF;
	}

	public void setCodF(String codF) {
		CodF = codF;
	}
}
