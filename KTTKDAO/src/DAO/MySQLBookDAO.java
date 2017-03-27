package DAO;

import Model.Author;
import Model.Book;
import Model.BookType;
import Model.Publisher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLBookDAO implements BookDAO {

    private Connection conn;

    public MySQLBookDAO() {
        openConnection();
    }

    public MySQLBookDAO(String dbName, String username, String password) {
        openConnection(dbName, username, password);
    }

    @Override
    public void openConnection() {
        openConnection("kttk", "root", "");
    }

    @Override
    public void openConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
        String dbClass = "com.mysql.jdbc.Driver";
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl,
                    username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertBook(Book book) {
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
    public void deleteBook(Book book) {
        String sql = "DELETE from book WHERE ID = ?";
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
    public void updateBook(Book book) {
        String sql = "UPDATE book SET AuthorID = ?,BookTypeID = ?, Cost = ?,Description = ?,Name = ?,PublisherID = ? "
                + "WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
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
                book.setPublisher(getPublisherById(res.getInt("PublisherID")));

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
                book.setPublisher(getPublisherById(res.getInt("PublisherID")));

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
                book.setPublisher(getPublisherById(res.getInt("PublisherID")));

                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Author getAuthorById(int id) {
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

    @Override
    public BookType getBookTypeById(int id) {
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

    @Override
    public Publisher getPublisherById(int id) {
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

    @Override
    public ArrayList<Publisher> getPublishers() {
        String sql = "SELECT * FROM publisher";
        PreparedStatement ps = null;
        ResultSet res = null;
        ArrayList<Publisher> publishers = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);

            res = ps.executeQuery();
            while (res.next()) {
                Publisher publisher = new Publisher();
                publisher.setID(res.getInt("ID"));
                publisher.setName(res.getString("Name"));
                publisher.setDescription(res.getString("Description"));

                publishers.add(publisher);
            }

            return publishers;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    @Override
    public ArrayList<BookType> getBookTypes() {
        String sql = "SELECT * FROM booktype";
        PreparedStatement ps = null;
        ResultSet res = null;
        ArrayList<BookType> bookTypes = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);

            res = ps.executeQuery();
            while (res.next()) {
                BookType bookType = new BookType();
                bookType.setID(res.getInt("ID"));
                bookType.setName(res.getString("Name"));
                bookType.setDescription(res.getString("Description"));

                bookTypes.add(bookType);
            }
            return bookTypes;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    @Override
    public ArrayList<Author> getAuthors() {
        String sql = "SELECT * FROM author";
        PreparedStatement ps = null;
        ResultSet res = null;
        ArrayList<Author> authors = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);

            res = ps.executeQuery();
            while (res.next()) {
                Author author = new Author();
                author.setID(res.getInt("ID"));
                author.setName(res.getString("Name"));
                author.setDescription(res.getString("Description"));

                authors.add(author);
            }
            return authors;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    @Override
    public void insertAuthor(Author author) {
        String sql = "INSERT INTO author (Description, Name) VALUES(?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, author.getDescription());
            pre.setString(2, author.getName());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertPublisher(Publisher publisher) {
        String sql = "INSERT INTO publisher (Description, Name) VALUES(?, ?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, publisher.getDescription());
            pre.setString(2, publisher.getName());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertBookType(BookType bookType) {
        String sql = "INSERT INTO booktype(Description, Name) VALUES(?, ?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, bookType.getName());
            ps.setString(2, bookType.getDescription());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        String sql = "UPDATE publisher SET Name = ?, Description = ? WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, publisher.getName());
            ps.setString(2, publisher.getDescription());
            ps.setInt(3, publisher.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void deletePublisher(Publisher publisher) {
        String sql = "DELETE FROM publisher WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, publisher.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void updateBookType(BookType bookType) {
        String sql = "UPDATE booktype SET Name = ?, Description = ? WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, bookType.getName());
            ps.setString(2, bookType.getDescription());
            ps.setInt(3, bookType.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void deleteBookType(BookType bookType) {
        String sql = "DELETE FROM booktype WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, bookType.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void updateAuthor(Author author) {
        String sql = "UPDATE author SET Name = ?, Description = ? WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, author.getName());
            ps.setString(2, author.getDescription());
            ps.setInt(3, author.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }

    @Override
    public void deleteAuthor(Author author) {
        String sql = "DELETE FROM author WHERE ID = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, author.getID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
        }
    }
}
