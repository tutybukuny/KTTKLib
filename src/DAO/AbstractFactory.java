package DAO;

public abstract class AbstractFactory {

    abstract HumanDAO getHumanDAO(int which);

    abstract BookDAO getBookDAO(int which);

    abstract BillDAO getBillDAO(int which);

}
