package DAO;

import Model.*;



public interface HumanDAO {

	Connection getConnection();

	/**
	 * 
	 * @param username
	 * @param password
	 */
	boolean login(String username, String password);

	/**
	 * 
	 * @param id
	 */
	Human getInfo(int id);

	/**
	 * 
	 * @param dbName
	 * @param username
	 * @param password
	 */
	Connection getConnection(String dbName, String username, String password);

}