package DAO;

import Model.*;

public interface HumanDAO {

    void openConnection();
    
    void openConnection(String dbName, String username, String password);

    boolean login(String username, String password);
    
    void closeConnection();

    Human getInfo(int id);
}
