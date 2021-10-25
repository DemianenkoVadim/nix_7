package ua.com.alevel.util;

import org.hibernate.Session;

import javax.swing.*;

public class SessionUtil {

    public static void openSaveCloseSession(Object object) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "I/O Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
