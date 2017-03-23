package DAO;

import Model.Const;

public class HumanDAOFactory extends AbstractFactory {

    public HumanDAOFactory() {

    }

    @Override
    public HumanDAO getHumanDAO(int which) {
        if (which == Const.MYSQL) {
            return new MySQLHumanDAO();
        } else if (which == Const.SQLSEVER) {
            return new SQLServerHumanDAO();
        }

        return null;
    }

    @Override
    public BookDAO getBookDAO(int which) {
        return null;
    }

    @Override
    public BillDAO getBillDAO(int which) {
        return null;
    }

}
