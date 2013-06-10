package ausleihe;

import java.sql.*;

public class Main {

    /**
     * @param args
     */
	public static void main(String[] args) throws SQLException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			//Ich hasse euch alle, gez. Keks
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:jtds:sqlserver://127.0.0.1/ProjectAusleihe","","");
			stmt = con.createStatement( ) ;
			rs = stmt.executeQuery("Select * From adressen;");
			while(rs.next( )){
				System.out.println(rs.getString("Nachname") + rs.getString("Vorname"));
			}
		} catch ( Exception e ) {
			System.out.println("fehler"); 
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close ( ) ;
			if (stmt != null) stmt.close ( ) ;
			if (con != null) con.close ( );
			System.out.println("Fertig");
			
		}
		
		
	}	

}
