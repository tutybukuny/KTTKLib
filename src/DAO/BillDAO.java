package DAO;

import Model.*;



public interface BillDAO {

	Connection getConnection();

	/**
	 * 
	 * @param bill
	 */
	void insert(Bill bill);

	/**
	 * 
	 * @param bill
	 */
	void delete(Bill bill);

	/**
	 * 
	 * @param bill
	 */
	void update(Bill bill);

	/**
	 * 
	 * @param id
	 */
	Bill getBill(int id);

	ArrayList<Bill> getBills();

}