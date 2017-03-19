package DAO;

import Model.*;
import java.sql.Connection;
import java.util.ArrayList;

public interface BookDAO {

    Connection getConnection();

    Connection getConnection(String dbName, String username, String password);

    void insert(Book book);

    void delete(Book book);

    void update(Book book);

    Book getBookById(int id);

    ArrayList<Book> getBookByName(String name);

    ArrayList<Book> getBooks();

}
