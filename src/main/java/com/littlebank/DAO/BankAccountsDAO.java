package com.littlebank.DAO;

import com.littlebank.data.BankAccount;

import java.util.Collection;

/**
 * Created by sbt-velichko-aa on 17.03.17.
 */
public interface BankAccountsDAO {

    public void addAccount(BankAccount bankAccount);

    public void updateBankAccount(Long sessions_id, BankAccount bankAccount);

    public BankAccount getBankAccountById(Long sessions_id);

    public Collection getAllBankAccount();

    public void deleteBankAccount(BankAccount bankAccount);
}
