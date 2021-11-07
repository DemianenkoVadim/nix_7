package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;
import ua.com.alevel.util.HibernateUtil;

import java.util.Optional;

import static ua.com.alevel.util.SessionsUtil.*;

@Log4j2
public class UserDaoImpl implements UserDao {

    @Override
    public void addNewUser(User user) {
        saveInputInformationSession(user);
    }

    @Override
    public Optional<User> findUserByPhoneNumberBothEmailBothPassword(String phoneNumber, String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var queryUser = session.createQuery("FROM User u where u.userEmail =: userEmail " +
                            "AND u.userTelephoneNumber =: userTelephoneNumber AND u.userPassword =: userPassword", User.class)
                    .setParameter("userEmail", email)
                    .setParameter("userTelephoneNumber", phoneNumber)
                    .setParameter("userPassword", password);
            return queryUser.getResultList().stream().findFirst();
        } catch (Exception e) {
            log.error("Can not find user with input parameters " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            user = session.load(User.class, id);
        } catch (Exception e) {
            log.error("Can not get user by id from database " + e.getMessage());
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        deleteInformationFromDatabaseSession(id);
    }

    @Override
    public void updateUser(User user) {
        updateInformationInDatabaseSession(user);
    }
}
