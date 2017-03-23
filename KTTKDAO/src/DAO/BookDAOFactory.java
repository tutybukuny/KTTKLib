package DAO;

import Model.Const;

public class BookDAOFactory extends AbstractFactory {

    public BookDAOFactory() {

    }

    @Override
    public HumanDAO getHumanDAO(int which) {
        return null;
    }

    @Override
    public BookDAO getBookDAO(int which) {
        if (which == Const.MYSQL) {
            return new MySQLBookDAO();
        } else if (which == Const.SQLSEVER) {
            return new SQLServerBookDAO();
        }

        return null;
    }

    @Override
    public BillDAO getBillDAO(int which) {
        return null;
    }

}
