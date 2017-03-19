package DAO;

import Model.Human;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLServerHumanDAO implements HumanDAO {
    
    String hostName = "localhost";
    String sqlInstanceName = "SQLEXPRESS";

    private Connection conn;

    public SQLServerHumanDAO() {

    }

    @Override
    public Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Connection getConnection(String dbName, String username, String password) {
         String dbUrl = "jdbc:sqlserver://" + hostName + ":1433;instance=" + sqlInstanceName + ";databaseName=" + dbName;
        String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl,
                    username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Override
    public boolean login(String username, String password) {
       String sql = "SELECT * FROM account WHERE Username = ? AND Password = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            res = ps.executeQuery();
            while (res.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return false;
    }

    @Override
    public Human getInfo(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
