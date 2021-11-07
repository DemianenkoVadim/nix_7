package ua.com.alevel.util;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;

@Log4j2
public class SessionsUtil {

    public static void saveInputInformationSession(Object object) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error(String.format("Can not save input information to database %s %s",
                    object.getClass().getSimpleName(), e.getMessage()), e);
        }
    }

    public static void deleteInformationFromDatabaseSession(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.delete(id);
            session.getTransaction().commit();
        } catch (Exception e) {
            log.error("Can not delete information from database " + e.getMessage());
        }
    }

    public static void updateInformationInDatabaseSession(Object object) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.getTransaction();
            session.update(object);
            session.beginTransaction().commit();
        } catch (Exception e) {
            log.error(String.format("Can not update information in database %s %s",
                    object.getClass().getSimpleName(), e.getMessage()));
        }
    }
}
