package DAO;

public abstract class AbstractFactory {

	/**
	 * 
	 * @param which
	 */
	public abstract HumanDAO getHumanDAO(int which) {
		
	}

	/**
	 * 
	 * @param which
	 */
	public abstract BookDAO getBookDAO(int which) {
		
	}

	/**
	 * 
	 * @param which
	 */
	public abstract BillDAO getBillDAO(int which) {
		
	}

}