package DAO;

import Model.*;
import java.sql.Connection;
import java.util.ArrayList;

public interface BillDAO {

    Connection getConnection();
    
    Connection getConnection(String dbName,String username,String password);

    void insert(Bill bill);

    void delete(Bill bill);

    void update(Bill bill);

    Bill getBill(int id);

    ArrayList<Bill> getBills();

}
