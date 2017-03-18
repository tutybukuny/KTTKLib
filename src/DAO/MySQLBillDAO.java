package DAO;

import Model.Bill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MySQLBillDAO implements BillDAO {

    private Connection conn;

    public MySQLBillDAO() {

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
    public void insert(Bill bill) {
        String sql = "INSERT INTO bill(CartID, PaymentID, HumanID) VALUES(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bill.getCart().getID());
            ps.setInt(2, bill.getPayment().getID());
            ps.setInt(3, bill.getCustomer().getID());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void delete(Bill bill) {
        String sql = "DELETE bill WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bill.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void update(Bill bill) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bill getBill(int id) {
        String sql = "SELECT * FROM bill WHERE ID = ?";
        PreparedStatement ps = null;
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                Bill bill = new Bill();
                bill.setID(id);
                bill.setPayment(null);
                bill.setCart(null);
                bill.setCustomer(null);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closePreparedStatement(ps);
        }
        return null;
    }

    @Override
    public ArrayList<Bill> getBills() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
