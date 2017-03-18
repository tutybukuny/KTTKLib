package DAO;

import Model.*;



public interface BookDAO {

	void getConnection();

	/**
	 * 
	 * @param book
	 */
	void insert(Book book);

	/**
	 * 
	 * @param book
	 */
	void delete(Book book);

	/**
	 * 
	 * @param book
	 */
	void update(Book book);

	/**
	 * 
	 * @param id
	 */
	Book getBookById(int id);

	/**
	 * 
	 * @param name
	 */
	ArrayList<Book> getBookByName(String name);

	ArrayList<Book> getBooks();

}