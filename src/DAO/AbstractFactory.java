package DAO;

public abstract class AbstractFactory {

	public HumanDAO getHumanDAO() {
		
	}

	public OrderDAO getOrderDAO() {
		
	}

	/**
	 * 
	 * @param which
	 */
	public BookDAO getBookDAO(int which) {
		
	}

	public BillDAO getBillDAO() {
		
	}

}