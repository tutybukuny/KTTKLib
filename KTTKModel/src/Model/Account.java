package Model;

import java.io.Serializable;

public class Account implements Serializable{

    private String username;
    private String password;
    private int ID;

    public Account() {

    }

    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public int getID() {
        return this.ID;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {

    }

}
