package Model;

import java.io.Serializable;

public class Birthday implements Serializable {

    private int day;
    private String month;
    private int year;
    private int ID;

    public Birthday() {

    }

    public int getDay() {
        return this.day;
    }

    /**
     *
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return this.month;
    }

    /**
     *
     * @param month
     */
    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    /**
     *
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

}
