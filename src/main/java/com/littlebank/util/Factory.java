package com.littlebank.util;

import com.littlebank.DAO.BankAccountsDAO;
import com.littlebank.DAO.BankAccountsDAOImpl;

/**
 * Created by sbt-velichko-aa on 20.03.17.
 */
public class Factory {

    private static Factory instance = null;
    private static BankAccountsDAO bankAccountsDAO = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public BankAccountsDAO getSessionsDAO() {
        if (bankAccountsDAO == null) {
            bankAccountsDAO = new BankAccountsDAOImpl();
        }
        return bankAccountsDAO;
    }


}
