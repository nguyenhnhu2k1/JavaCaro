
package server.dao;

import java.sql.Connection;
import java.sql.*;

public class Dao {
    protected Connection con;
    public Dao() {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=AccountLogin";
        String user = "sa";
        String password = "10102001";
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
