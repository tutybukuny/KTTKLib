package Model;

public class Bill {

	private Cart cart;
	private Customer customer;
	private Payment payment;
	private int ID;

	public Bill() {
		
	}

	public Cart getCart() {
		return this.cart;
	}

	/**
	 * 
	 * @param cart
	 */
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	/**
	 * 
	 * @param customer
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Payment getPayment() {
		return this.payment;
	}

	/**
	 * 
	 * @param payment
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getID() {
		
	}

	/**
	 * 
	 * @param ID
	 */
	public void setID(int ID) {
		
	}

}