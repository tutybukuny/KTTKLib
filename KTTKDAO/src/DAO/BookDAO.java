package DAO;

import Model.*;
import java.util.ArrayList;

public interface BookDAO {

    void openConnection();

    void openConnection(String dbName, String username, String password);
    
    void closeConnection();

    void insertBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    Book getBookById(int id);

    ArrayList<Book> getBookByName(String name);

    ArrayList<Book> getBooks();
    
    Publisher getPublisherById(int id);
    
    BookType getBookTypeById(int id);

    Author getAuthorById(int id);
    
    ArrayList<Publisher> getPublishers();
    
    void insertPublisher(Publisher publisher);
    
    void updatePublisher(Publisher publisher);
    
    void deletePublisher(Publisher publisher);
    
    ArrayList<BookType> getBookTypes();
    
    void insertBookType(BookType bookType);
    
    void updateBookType(BookType bookType);
    
    void deleteBookType(BookType bookType);
    
    ArrayList<Author> getAuthors();
    
    void insertAuthor(Author author);
    
    void updateAuthor(Author author);
    
    void deleteAuthor(Author author);
}
