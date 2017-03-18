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

}