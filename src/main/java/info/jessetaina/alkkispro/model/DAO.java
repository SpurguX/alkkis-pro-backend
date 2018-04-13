package info.jessetaina.alkkispro.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
	
	public void haeJuomat() {
	
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/alkkis-test?user=root&password=lokaalimaria");
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM juomat");
			ResultSet rs = stmt.executeQuery();
			
			if (rs != null) {
				while(rs.next()) {
					String rivi = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5);
					System.out.println(rivi);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	}
}
