package DAO;

public abstract class AbstractFactory {

    public abstract HumanDAO getHumanDAO(int which);

    public abstract BookDAO getBookDAO(int which);

    public abstract BillDAO getBillDAO(int which);

}
