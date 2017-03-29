package Model;

import java.io.Serializable;

public class Payment implements Serializable {

    private String cardName;
    private String cardNumber;
    private int ID;

    public Payment() {

    }

    public String getCardName() {
        return this.cardName;
    }

    /**
     *
     * @param cardName
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    /**
     *
     * @param cardNumber
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
