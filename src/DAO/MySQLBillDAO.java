package DAO;

import Model.Bill;
import Model.Cart;
import Model.Payment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MySQLBillDAO implements BillDAO {

    private Connection conn;

    public MySQLBillDAO() {

    }
    
    //chua xong
    
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
        String sql = "SELECT * FROM bill WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                Bill bill = new Bill();
                bill.setID(id);
                bill.setPayment(null);
                bill.setCart(null);
                bill.setCustomer(null);
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
    public ArrayList<Bill> getBills() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Payment getPaymentById(int id){
        String sql = "SELECT * FROM payment WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                Payment payment = new Payment();
                payment.setCardName(res.getString("CardName"));
                payment.setCardNumber(res.getString("CardNumber"));
                return payment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    private Cart getCartById(int id){
        String sql = "SELECT * FROM cart WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                Cart cart = new Cart();
                cart.setID(id);
                cart.setTotalCost(res.getFloat("TotalCost"));
                cart.setBooks(null);
                
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
