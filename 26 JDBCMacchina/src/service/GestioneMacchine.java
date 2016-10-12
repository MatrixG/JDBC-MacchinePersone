package service;

import dao.MacchinaDAO;
import dao.MacchinaPersonaDAO;
import dao.PersonaDAO;
import model.Macchina;
import model.Persona;

public class GestioneMacchine {
	
	MacchinaDAO mDAO = new MacchinaDAO();
	PersonaDAO pDAO = new PersonaDAO();
	MacchinaPersonaDAO mpDAO = new MacchinaPersonaDAO();

	public Macchina aggiungiMacchina(String modello, String targa) {
		
		if (modello != null && targa != null){
			
			return mDAO.aggiungiMacchina(modello, targa);
		}
		return null;
	}

	public boolean cancellaMacchina(String targa) {
		
		if (targa != null){
			
			return mDAO.cancellaMacchina(targa);
		}
		
		return false;
	}

	public Macchina cercaMacchina(String targa) {

		if (targa != null){
		
			return mDAO.cercaMacchina(targa);
		}
		return null;
	}

	public Macchina modificaMacchina(String targa, String nomeNew) {
		
		if (targa != null && nomeNew!= null){
			
			return mDAO.modificaMacchina(targa, nomeNew);
		}
		
		return null;
	}

	public Persona aggiungiPersona(String nome, String cognome, String codF) {

		if (nome != null && cognome!= null && codF != null){
			
			return pDAO.aggiungiPersona(nome, cognome, codF);
		}
		
		return null;
	}

	public Persona cercaPersona(String codF) {
		
		if (codF != null){
			
			return pDAO.cercaPersona(codF);
		}
		return null;
	}

	public boolean cancellaPersona(String codF) {
		
		if (codF != null){
			
			return pDAO.cancellaPersona(codF);
		}
		
		return false;
	}

	public Persona modificaPersona(String nomeNew, String cognomeNew, String codF) {
		
		if (nomeNew != null && cognomeNew != null && codF != null){
			
			return pDAO.modificaPersona(nomeNew, cognomeNew, codF);
		}
		return null;
	}

	public boolean aggiungiMacchinaPersona(String targa, String codF) {
		
		if ((targa != null) && (codF != null)){
		
			return mpDAO.aggiungiMacchinaPersona(targa, codF);
			
		}
		return false;
	}

	public boolean cancellaMacchinaPersona(String targa, String codF) {
		
		if ((targa != null) && (codF != null)){
			
			return mpDAO.cancellaMacchinaPersona(targa, codF);
			
		}
		return false;
	}

}
