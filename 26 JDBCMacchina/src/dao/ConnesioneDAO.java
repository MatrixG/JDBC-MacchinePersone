package dao;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import utility.*;

public abstract class ConnesioneDAO {

	private static Connection con = null;
	
	
	public static Connection connetti(){
		
		if ( con == null){
			
			try {
				con = DataSource.getInstance().getConnection();
			} catch (SQLException | IOException | PropertyVetoException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
}
