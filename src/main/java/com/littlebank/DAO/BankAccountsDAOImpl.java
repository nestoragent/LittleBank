package com.littlebank.DAO;

import com.littlebank.data.BankAccount;
import com.littlebank.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sbt-velichko-aa on 17.03.17.
 */
@Slf4j
public class BankAccountsDAOImpl implements BankAccountsDAO {

    @Override
    public void addAccount(BankAccount bankAccount) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(bankAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error when try to add bank account: " + bankAccount, e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void updateBankAccount(Long sessions_id, BankAccount bankAccount) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(bankAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error when try to update bank account. Account: " + bankAccount + ", id is: " + sessions_id, e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public BankAccount getBankAccountById(Long sessions_id) {
        Session session = null;
        BankAccount bankAccount = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            bankAccount = (BankAccount) session.load(BankAccount.class, sessions_id);
        } catch (Exception e) {
            log.error("Error when try to get bank account by id. Account id is: " + sessions_id, e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return bankAccount;
    }

    @Override
    public Collection getAllBankAccount() {
        Session session = null;
        List sessions = new ArrayList<BankAccount>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            sessions = session.createCriteria(BankAccount.class).list();
        } catch (Exception e) {
            log.error("Error when try to get all bank accounts.", e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return sessions;
    }

    @Override
    public void deleteBankAccount(BankAccount bankAccount) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(bankAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Error when try to delete bank account: " + bankAccount, e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
