package DAO;

import Model.*;
import java.util.ArrayList;

public interface BookDAO {

    void openConnection();

    void openConnection(String dbName, String username, String password);
    
    void closeConnection();

    void insert(Book book);

    void delete(Book book);

    void update(Book book);

    Book getBookById(int id);

    ArrayList<Book> getBookByName(String name);

    ArrayList<Book> getBooks();
    
    Publisher getPublisherById(int id);
    
    BookType getBookTypeById(int id);

    Author getAuthorById(int id);
    
    ArrayList<Publisher> getPublishers();
    
    ArrayList<BookType> getBookeTypes();
    
    ArrayList<Author> getAuthors();
}
