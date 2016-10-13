package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import model.Macchina;

public class MacchinaPersonaDAO extends ConnesioneDAO {
	
	private Connection con = ConnesioneDAO.connetti();
	private MacchinaDAO mDAO = new MacchinaDAO();
	private PersonaDAO pDAO = new PersonaDAO();

	// Aggiunge una tupla nella tabella MACCHINA_PERSONA per
	// collegare una macchina ad una persona
	public boolean aggiungiMacchinaPersona(String targa, String codF) {
		
		if ((mDAO.cercaMacchina(targa) != null) && (pDAO.cercaPersona(codF)) != null){
			
			PreparedStatement ps = null;
			try {
				String sql = "INSERT INTO MACCHINA_PERSONA(Id_p, id_car) " +
							 "SELECT  id_p, id_car " +
							 "FROM MACCHINA, PERSONA " +
							 "WHERE TARGA = ? AND CODF = ?";
				
				 ps = con.prepareStatement(sql);
				 ps.setString(1, targa);
				 ps.setString(2, codF);
				 if (ps.executeUpdate() == 1){
					 return true;
				 }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return false;
	}

	public boolean cancellaMacchinaPersona(String targa, String codF) {
		
		if ((mDAO.cercaMacchina(targa) != null) && (pDAO.cercaPersona(codF)) != null){
			
			PreparedStatement ps = null;
			try {
								
				String sql = "DELETE FROM MACCHINA_PERSONA " +
						 	 "WHERE ID_CAR IN (SELECT ID_CAR " +
						 					  "FROM MACCHINA " +
						 					  "WHERE TARGA = ?) " +
						 	 "AND   ID_P IN (SELECT ID_P " +
						 					"FROM PERSONA " +
						 					"WHERE CODF = ?)";
			
				 ps = con.prepareStatement(sql);
				 ps.setString(1, targa);
				 ps.setString(2, codF);
				 if (ps.executeUpdate() == 1){
					 return true;
				 }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				if (ps != null)
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			}
		}
		return false;
	}

	public Map<String, Macchina> getTutteMacchinePerPersona(String codF) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map <String, Macchina> tempMap;
		try {
			
			String sql = "SELECT Targa, Modello " +
					     "FROM MACCHINA " +
					     "WHERE Id_Car IN (SELECT Id_Car " +
					     				  "FROM MACCHINA_PERSONA " +
					 				      "WHERE ID_P IN (SELECT Id_P " +
					 						    	     "FROM PERSONA " +
						 						         "WHERE CodF = ?)) " +
					"ORDER BY Targa";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, codF);
			
			rs = ps.executeQuery();
			
			tempMap = new TreeMap<String, Macchina>();
			while (rs.next()){
				
				tempMap.put(rs.getString(1), new Macchina(rs.getString(2), 
														rs.getString(1)));
			}
			if (!tempMap.isEmpty()){
				return tempMap;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
}
