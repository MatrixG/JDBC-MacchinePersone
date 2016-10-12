package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MacchinaPersonaDAO extends ConnesioneDAO {
	
	private Connection con = ConnesioneDAO.connetti();
	private MacchinaDAO mDAO = new MacchinaDAO();
	private PersonaDAO pDAO = new PersonaDAO();

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
}
