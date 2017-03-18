package Model;

import java.util.ArrayList;

public class Cart {

	private ArrayList<Book> books;
	private float totalCost;

	public Cart() {
		
	}

	public ArrayList<Book> getBooks() {
		return this.books;
	}

	/**
	 * 
	 * @param books
	 */
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public float getTotalCost() {
		return this.totalCost;
	}

	/**
	 * 
	 * @param totalCost
	 */
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

}