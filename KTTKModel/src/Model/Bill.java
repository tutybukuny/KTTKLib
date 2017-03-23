package Model;

import java.io.Serializable;

public class Bill implements Serializable {

    private Cart cart;
    private Human human;
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

    public Human getCustomer() {
        return this.human;
    }

    /**
     *
     * @param human
     */
    public void setCustomer(Human human) {
        this.human = human;
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
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
