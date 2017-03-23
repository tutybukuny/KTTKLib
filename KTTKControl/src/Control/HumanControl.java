/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import DAO.HumanDAO;
import DAO.HumanDAOFactory;
import Model.Account;
import Model.Address;
import Model.Birthday;
import Model.Const;
import Model.Human;
import Model.Name;

/**
 *
 * @author huutien
 */
public class HumanControl {
    
    private HumanDAO dao = null;
    
    public HumanControl() {
        createDAO();
    }
    
    public boolean checkLogin(Account acc) {
        return dao.getAccount(acc.getUsername(), acc.getPassword()) != null;
    }
    
    public Account getAccount(String username, String password) {
        return dao.getAccount(username, password);
    }
    
    public Human getHumanById(int id) {
        return dao.getHumanById(id);
    }
    
    public Human getHumanByAccount(Account acc) {
        return dao.getHumanByAccount(acc);
    }
    
    public void insertHuman(Human human) {
        dao.insertHuman(human);
    }
    
    public void updateHuman(Human human) {
        dao.updateHuman(human);
    }
    
    public int getHumanId() {
        return dao.getHumanId();
    }
    
    public void insertAccount(Human human) {
        dao.insertAccount(human);
    }
    
    public void updateAccount(Account acc) {
        dao.updateAccount(acc);
    }
    
    public void insertAddress(Human human) {
        dao.insertAddress(human);
    }
    
    public void insertName(Human human) {
        dao.insertName(human);
    }
    
    public void insertBirthday(Human human) {
        dao.insertBirthday(human);
    }
    
    public Account getAccountByHumanId(int humanID) {
        return dao.getAccountByHumanId(humanID);
    }
    
    public Address getAddressByHumanId(int humanID) {
        return dao.getAddressByHumanId(humanID);
    }
    
    public Name getNameByHumanId(int humanID) {
        return dao.getNameByHumanId(humanID);
    }
    
    public Birthday getBirthDayByHumanId(int humanID) {
        return dao.getBirthDayByHumanId(humanID);
    }
    
    public void updateAddress(Address add) {
        dao.updateAddress(add);
    }
    
    public void updateName(Name name) {
        dao.updateName(name);
    }
    
    public void updateBirthday(Birthday birthday) {
        dao.updateBirthday(birthday);
    }
    
    public void createDAO() {
        if (dao != null) {
            closeDAO();
        }
        
        dao = new HumanDAOFactory().getHumanDAO(Const.USED_DATABASE);
    }
    
    public void closeDAO() {
        if (dao != null) {
            dao.closeConnection();
        }
    }
}
