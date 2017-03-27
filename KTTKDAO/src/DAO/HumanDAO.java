package DAO;

import Model.*;

public interface HumanDAO {

    void openConnection();
    
    void openConnection(String dbName, String username, String password);

    Account getAccount(String username, String password);
    
    void closeConnection();

    Human getHumanById(int id);
    
    Human getHumanByAccount(Account acc);
    
    void insertHuman(Human human);
    
    void updateHuman(Human human);
    
    int getHumanId();
    
    Account getAccountByHumanId(int humanID);
    
    void insertAccount(Human human);
    
    void updateAccount(Account acc);
    
    boolean checkUsername(String username);
    
    Address getAddressByHumanId(int humanID);
    
    void insertAddress(Human human);
    
    void updateAddress(Address add);
    
    Name getNameByHumanId(int humanID);
    
    void insertName(Human human);
    
    void updateName(Name name);
    
    Birthday getBirthDayByHumanId(int humanID);
    
    void insertBirthday(Human human);
    
    void updateBirthday(Birthday birthday);
}
