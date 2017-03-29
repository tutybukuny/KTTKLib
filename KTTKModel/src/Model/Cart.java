package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {

    private ArrayList<Book> books;
    private float totalCost;
    private int ID;

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

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
