package hugosql.plsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Victor Hugo Silva
 */
public class Cluster {
    
    public Cluster() {
    }
    public Boolean ComandoSQL(String instruccion) {
        boolean hay=false;
        try {
            Class.forName(Clusterdata.Driver);
            Connection conn = DriverManager.getConnection(Clusterdata.Url,Clusterdata.Usuario,Clusterdata.Clave);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(instruccion);
            stmt.close();conn.close();
            hay=true;
        } catch (Exception e) {
            System.out.println("ComandoSQL: "+e.getLocalizedMessage());
        }
        return hay;
    }

}
