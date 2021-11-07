package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.dao.BankAccountDao;
import ua.com.alevel.entity.BankAccount;
import ua.com.alevel.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

import static ua.com.alevel.util.SessionsUtil.saveInputInformationSession;

@Log4j2
public class BankAccountDaoImpl implements BankAccountDao {

    @Override
    public void addBankAccount(BankAccount account) {
        saveInputInformationSession(account);
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(bankAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Problem with updating bank account.", e);
        }
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            log.debug("Find bank account by id");
            var bandAccountQuery = session.createQuery("FROM BankAccount ba WHERE ba.id =: id", BankAccount.class)
                    .setParameter("id", id);
            return Optional.ofNullable(bandAccountQuery.getSingleResult());
        } catch (Exception e) {
            log.error("Problem with finding bank account " + e);
        }
        return Optional.empty();
    }

    @Override
    public List<BankAccount> getUsersBankAccounts(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM BankAccount", BankAccount.class)
                    .list();
        } catch (Exception e) {
            log.error("Problem showing all users bank Account " + e.getMessage());
        }
        return List.of();
    }

    @Override
    public void deleteBankAccount(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            var queryBankAccount = session.createQuery("FROM BankAccount ba where ba.id =: id", BankAccount.class)
                    .setParameter("id", id)
                    .getSingleResult();
            session.delete(queryBankAccount);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Problem with removing bank account " + e);
        }
    }
}
