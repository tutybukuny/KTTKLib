package DAO;

import Model.Const;

public class BillDAOFactory extends AbstractFactory {

    public BillDAOFactory() {
    }

    @Override
    HumanDAO getHumanDAO(int which) {
        return null;
    }

    @Override
    BookDAO getBookDAO(int which) {
        return null;
    }

    @Override
    BillDAO getBillDAO(int which) {
        if(which == Const.MYSQL){
            return new MySQLBillDAO();
        }else if(which == Const.SQLSEVER){
            return new SQLServeBillDAO();
        }
        
        return null;
    }

}
