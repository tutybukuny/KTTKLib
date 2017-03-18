package DAO;

import Model.Book;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class MySQLBookDAO implements BookDAO {

    private Connection conn;

    public MySQLBookDAO() {

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
    public void insert(Book book) {
        String sql = "INSERT INTO book(Name, Description, Cost, CartID, PublisherID, AuthorID, BookTypeID) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getDescription());
            ps.setFloat(3, book.getCost());
            

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void delete(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Book getBookById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Book> getBookByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Book> getBooks() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
