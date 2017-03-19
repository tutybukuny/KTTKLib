package DAO;

import Model.*;
import java.util.ArrayList;

public interface BillDAO {

    void openConnection();
    
    void openConnection(String dbName,String username,String password);
    
    void closeConnection();

    void insert(Bill bill);

    void delete(Bill bill);

    void update(Bill bill);

    Bill getBill(int id);

    ArrayList<Bill> getBills();

}
