package DAO;

import Model.Human;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLHumanDAO implements HumanDAO {

    private Connection conn;

    public MySQLHumanDAO() {
        openConnection();
    }

    public MySQLHumanDAO(String dbName, String username, String password) {
        openConnection(dbName, username, password);
    }

    @Override
    public void openConnection() {
        openConnection("kttk", "root", "");
    }

    @Override
    public void openConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName;
        String dbClass = "com.mysql.jdbc.Driver";
        conn = null;
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl,
                    username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                Human human = new Human();

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

    @Override
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
