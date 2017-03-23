package DAO;

import Model.Account;
import Model.Address;
import Model.Birthday;
import Model.Human;
import Model.Name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLHumanDAO implements HumanDAO {

    private Connection conn;

    public MySQLHumanDAO() {
        openConnection();
    }

    public MySQLHumanDAO(String dbName, String username, String password) {
        openConnection(dbName, username, password);
    }

    @Override
    public void openConnection() {
        openConnection("kttk", "root", "");
    }

    @Override
    public void openConnection(String dbName, String username, String password) {
        String dbUrl = "jdbc:mysql://localhost:3306/" + dbName + "?useUnicode=true&characterEncoding=utf-8";
        String dbClass = "com.mysql.jdbc.Driver";
        conn = null;
        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(dbUrl,
                    username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Account getAccount(String username, String password) {
        String sql = "SELECT * FROM account WHERE Username = ? AND Password = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            res = ps.executeQuery();
            if (res.next()) {
                Account acc = new Account();
                acc.setID(res.getInt("ID"));
                acc.setUsername(username);
                acc.setPassword(password);

                return acc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    @Override
    public Human getHumanById(int id) {
        String sql = "SELECT * FROM human WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet res = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            res = ps.executeQuery();
            if (res.next()) {
                Human human = new Human();
                human.setDiscriminator(res.getString("Discriminator"));
                human.setAcc(getAccountByHumanId(id));
                human.setAddress(getAddressByHumanId(id));
                human.setBirthday(getBirthDayByHumanId(id));
                human.setID(res.getInt("ID"));
                human.setName(getNameByHumanId(id));

                return human;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeResultSet(res);
        }
        return null;
    }

    @Override
    public Human getHumanByAccount(Account acc) {
        try {
            String sql = "SELECT a.ID as 'HumanID' FROM human as a "
                    + "INNER JOIN account as b ON a.ID=b.HumanID WHERE b.Username=? AND b.Password=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getUsername());
            pre.setString(2, acc.getPassword());
            ResultSet res = pre.executeQuery();

            if (res.next()) {
                int humanID = res.getInt("HumanID");

                return getHumanById(humanID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySQLHumanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void insertHuman(Human human) {
        String sql = "INSERT INTO human (Discriminator) VALUES(?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, human.getDiscriminator());
            pre.executeUpdate();
            int humanID = getHumanId();

            human.setID(humanID);

            insertAccount(human);
            insertAddress(human);
            insertName(human);
            insertBirthday(human);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateHuman(Human human) {
        try {
            String sql = "UPDATE human SET Discriminator=? WHERE ID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, human.getDiscriminator());
            pre.setInt(2, human.getID());
            pre.executeUpdate();

            updateAccount(human.getAcc());
            updateAddress(human.getAddress());
            updateBirthday(human.getBirthday());
            updateName(human.getName());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getHumanId() {
        try {
            int id = 0;
            String sql = "SELECT * FROM human";

            PreparedStatement pre = conn.prepareStatement(sql);
            ResultSet res = pre.executeQuery();

            while (res.next()) {
                id = res.getInt("ID");
            }

            return id;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    @Override
    public void insertAccount(Human human) {
        String sql = "INSERT INTO account (Username, Password, HumanID) VALUES(?, ?, ?)";
        Account acc = human.getAcc();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getUsername());
            pre.setString(2, acc.getPassword());
            pre.setInt(3, human.getID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account acc) {
        try {
            String sql = "UPDATE account SET Password=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, acc.getPassword());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertAddress(Human human) {
        String sql = "INSERT INTO account (Country, City, Street, Number, HumanID) VALUES(?, ?, ?, ?, ?)";
        Address add = human.getAddress();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, add.getCountry());
            pre.setString(2, add.getCity());
            pre.setString(3, add.getStreet());
            pre.setString(4, add.getNumber());
            pre.setInt(5, human.getID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertName(Human human) {
        String sql = "INSERT INTO name (FirstName, LastName, MiddleName, HumanID) VALUES(?, ?, ?, ?)";
        Name name = human.getName();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name.getFirstName());
            pre.setString(2, name.getLastName());
            pre.setString(3, name.getMiddleName());
            pre.setInt(4, human.getID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void insertBirthday(Human human) {
        String sql = "INSERT INTO birthday (Day, Month, Year, HumanID) VALUES(?, ?, ?, ?)";
        Birthday birthday = human.getBirthday();
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, birthday.getDay());
            pre.setString(2, birthday.getMonth());
            pre.setInt(3, birthday.getYear());
            pre.setInt(4, human.getID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Account getAccountByHumanId(int humanID) {
        try {
            String sql = "SELECT * FROM account WHERE HumanID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, humanID);
            ResultSet res = pre.executeQuery();

            if (res.next()) {
                Account acc = new Account();
                acc.setID(res.getInt("ID"));
                acc.setUsername(res.getString("Username"));
                acc.setPassword(res.getString("Password"));

                return acc;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Address getAddressByHumanId(int humanID) {
        try {
            String sql = "SELECT * FROM address WHERE HumanID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, humanID);
            ResultSet res = pre.executeQuery();

            if (res.next()) {
                Address add = new Address();
                add.setID(res.getInt("ID"));
                add.setCity(res.getString("City"));
                add.setCountry(res.getString("Country"));
                add.setNumber(res.getString("Number"));
                add.setStreet(res.getString("Street"));

                return add;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Name getNameByHumanId(int humanID) {
        try {
            String sql = "SELECT * FROM name WHERE HumanID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, humanID);
            ResultSet res = pre.executeQuery();

            if (res.next()) {
                Name name = new Name();
                name.setID(res.getInt("ID"));
                name.setFirstName(res.getString("FirstName"));
                name.setLastName(res.getString("LastName"));
                name.setMiddleName(res.getString("MiddleName"));

                return name;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Birthday getBirthDayByHumanId(int humanID) {
        try {
            String sql = "SELECT * FROM birthday WHERE HumanID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, humanID);
            ResultSet res = pre.executeQuery();

            if (res.next()) {
                Birthday birthday = new Birthday();
                birthday.setID(res.getInt("ID"));
                birthday.setDay(res.getInt("Day"));
                birthday.setMonth(res.getString("Month"));
                birthday.setYear(res.getInt("Year"));

                return birthday;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateAddress(Address add) {
        try {
            String sql = "UPDATE address SET Country=?, City=?, Street=?, Number=? WHERE ID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, add.getCountry());
            pre.setString(2, add.getCity());
            pre.setString(3, add.getStreet());
            pre.setString(4, add.getNumber());
            pre.setInt(5, add.getID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateName(Name name) {
        try {
            String sql = "UPDATE name SET FirstName=?, LastName=?, MiddleName=? WHERE ID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name.getFirstName());
            pre.setString(2, name.getLastName());
            pre.setString(3, name.getMiddleName());
            pre.setInt(4, name.getID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void updateBirthday(Birthday birthday) {
        try {
            String sql = "UPDATE birthday SET Day=?, Month=?, Year=? WHERE ID=?";
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, birthday.getDay());
            pre.setString(2, birthday.getMonth());
            pre.setInt(3, birthday.getYear());
            pre.setInt(4, birthday.getID());
            pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
