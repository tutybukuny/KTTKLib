/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.BookDAO;
import DAO.BookDAOFactory;
import Model.Author;
import Model.Book;
import Model.BookType;
import Model.Const;
import Model.Publisher;
import java.util.ArrayList;

/**
 *
 * @author huutien
 */
public class BookControl {

    private BookDAO dao = null;

    public BookControl() {
        createDAO();
    }

    private void createDAO() {
        if (dao != null) {
            closeDAO();
        }

        dao = new BookDAOFactory().getBookDAO(Const.USED_DATABASE);
    }

    public void closeDAO() {
        if (dao != null) {
            dao.closeConnection();
        }
    }

    public void addBook(Book book) {
        dao.insertBook(book);
    }

    public void deleteBook(Book book) {
        dao.deleteBook(book);
    }

    public void updateBook(Book book) {
        dao.updateBook(book);
    }

    public Book getBookById(int id) {
        return dao.getBookById(id);
    }

    public ArrayList<Book> getBookByName(String name) {
        return dao.getBookByName(name);
    }

    public ArrayList<Book> getBooks() {
        return dao.getBooks();
    }

    public Publisher getPublisherById(int id) {
        return dao.getPublisherById(id);
    }

    public BookType getBookTypeById(int id) {
        return dao.getBookTypeById(id);
    }

    public Author getAuthorById(int id) {
        return dao.getAuthorById(id);
    }

    public ArrayList<Publisher> getPublishers() {
        return dao.getPublishers();
    }
    
    public void insertPublisher(Publisher publisher){
        dao.insertPublisher(publisher);
    }
    
    public void updatePublisher(Publisher publisher){
        dao.updatePublisher(publisher);
    }
    
    public void deletePublisher(Publisher publisher){
        dao.deletePublisher(publisher);
    }

    public ArrayList<BookType> getBookTypes() {
        return dao.getBookTypes();
    }
    
    public void insertBookType(BookType bookType){
        dao.insertBookType(bookType);
    }
    
    public void updateBookType(BookType bookType){
        dao.updateBookType(bookType);
    }
    
    public void deleteBookType(BookType bookType){
        dao.deleteBookType(bookType);
    }

    public ArrayList<Author> getAuthors() {
        return dao.getAuthors();
    }
    
    public void insertAuthor(Author author){
        dao.insertAuthor(author);
    }
    
    public void updateAuthor(Author author){
        dao.updateAuthor(author);
    }
    
    public void deleteAuthor(Author author){
        dao.deleteAuthor(author);
    }
}
