package DAO;

import Model.Author;
import Model.Book;
import Model.BookType;
import Model.Publisher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SQLServerBookDAO implements BookDAO {

    String hostName = "localhost";
    String sqlInstanceName = "SQLEXPRESS";

    private Connection conn;

    public SQLServerBookDAO() {

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
    public void insert(Book book) {
        String sql = "INSERT INTO book(Name, Description, Cost, PublisherID, AuthorID, BookTypeID) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getDescription());
            ps.setFloat(3, book.getCost());
            ps.setInt(4, book.getPublisher().getID());
            ps.setInt(5, book.getAuthor().getID());
            ps.setInt(6, book.getBookType().getID());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void delete(Book book) {
        String sql = "DELETE book WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, book.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE * FROM book SET AuthorID = ?,BookTypeID = ?, Cost = ?,Description = ?,Name = ?,PublisherID = ? "
                + "WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            conn.prepareStatement(sql);
            ps.setInt(1, book.getAuthor().getID());
            ps.setInt(2, book.getBookType().getID());
            ps.setFloat(3, book.getCost());
            ps.setString(4, book.getDescription());
            ps.setString(5, book.getName());
            ps.setInt(6, book.getPublisher().getID());
            ps.setInt(7, book.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public Book getBookById(int id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                Book book = new Book();
                book.setID(res.getInt("ID"));
                book.setAuthor(getAuthorById(res.getInt("AuthorID")));
                book.setBookType(getBookTypeById(res.getInt("BookTypeID")));
                book.setCost(res.getFloat("Cost"));
                book.setDescription(res.getString("Description"));
                book.setName(res.getString("Name"));
                book.setPublisher(getPubById(res.getInt("PublisherID")));

                return book;
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
    public ArrayList<Book> getBookByName(String name) {
        String sql = "SELECT * FROM book WHERE Name LIKE ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");

            res = ps.executeQuery();
            ArrayList<Book> books = new ArrayList<>();

            while (res.next()) {
                Book book = new Book();
                book.setID(res.getInt("ID"));
                book.setAuthor(getAuthorById(res.getInt("AuthorID")));
                book.setBookType(getBookTypeById(res.getInt("BookTypeID")));
                book.setCost(res.getFloat("Cost"));
                book.setDescription(res.getString("Description"));
                book.setName(res.getString("Name"));
                book.setPublisher(getPubById(res.getInt("PublisherID")));

                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Book> getBooks() {
        String sql = "SELECT * FROM book ";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            res = ps.executeQuery();
            ArrayList<Book> books = new ArrayList<>();

            while (res.next()) {
                Book book = new Book();
                book.setID(res.getInt("ID"));
                book.setAuthor(getAuthorById(res.getInt("AuthorID")));
                book.setBookType(getBookTypeById(res.getInt("BookTypeID")));
                book.setCost(res.getFloat("Cost"));
                book.setDescription(res.getString("Description"));
                book.setName(res.getString("Name"));
                book.setPublisher(getPubById(res.getInt("PublisherID")));

                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Author getAuthorById(int id) {
        String sql = "SELECT * FROM author WHERE ID = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                Author author = new Author();
                author.setID(id);
                author.setName(res.getString("Name"));
                author.setDescription(res.getString("Description"));

                return author;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    private BookType getBookTypeById(int id) {
        String sql = "SELECT * FROM booktype WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                BookType bookType = new BookType();
                bookType.setID(id);
                bookType.setName(res.getString("Name"));
                bookType.setDescription(res.getString("Description"));

                return bookType;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    private Publisher getPubById(int id) {
        String sql = "SELECT * FROM publisher WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            while (res.next()) {
                Publisher publisher = new Publisher();
                publisher.setID(id);
                publisher.setName(res.getString("Name"));
                publisher.setDescription(res.getString("Description"));

                return publisher;
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
