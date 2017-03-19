package DAO;

import Model.Const;

public class BookDAOFactory extends AbstractFactory {

    public BookDAOFactory() {

    }

    @Override
    HumanDAO getHumanDAO(int which) {
        return null;
    }

    @Override
    BookDAO getBookDAO(int which) {
        if (which == Const.MYSQL) {
            return new MySQLBookDAO();
        } else if (which == Const.SQLSEVER) {
            return new SQLServerBookDAO();
        }

        return null;
    }

    @Override
    BillDAO getBillDAO(int which) {
        return null;
    }

}
