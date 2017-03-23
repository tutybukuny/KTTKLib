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
        dao.insert(book);
    }

    public void deleteBook(Book book) {
        dao.delete(book);
    }

    public void updateBook(Book book) {
        dao.update(book);
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

    public ArrayList<BookType> getBookTypes() {
        return dao.getBookeTypes();
    }

    public ArrayList<Author> getAuthors() {
        return dao.getAuthors();
    }
}
