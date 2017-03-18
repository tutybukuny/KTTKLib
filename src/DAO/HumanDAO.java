package DAO;

import Model.*;
import java.sql.Connection;

public interface HumanDAO {

    Connection getConnection();
    
    Connection getConnection(String dbName, String username, String password);

    boolean login(String username, String password);

    Human getInfo(int id);
}
