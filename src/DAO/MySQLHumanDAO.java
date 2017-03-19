package DAO;

import Model.Book;
import Model.Human;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySQLHumanDAO implements HumanDAO {

    private Connection conn;

    public MySQLHumanDAO() {

    }

    @Override
    public Connection getConnection() {
        return getConnection("kttk", "root", "");
    }

    @Override
    public Connection getConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
        String dbClass = "com.mysql.jdbc.Driver";
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
        String sql = "SELECT * FROM human WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                Human human = new Human() {

                    @Override
                    public String getDiscriminator() {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }
                };
                

                return human;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

}
