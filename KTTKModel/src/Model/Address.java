package Model;

import java.io.Serializable;

public class Address implements Serializable {

    private String country;
    private String city;
    private String street;
    private String number;
    private int ID;

    public Address() {

    }

    public String getCountry() {
        return this.country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return this.city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return this.street;
    }

    /**
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return this.number;
    }

    /**
     *
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    public int getID() {
        return ID;
    }

    /**
     *
     * @param id
     */
    public void setID(int id) {
        this.ID = id;
    }

}
