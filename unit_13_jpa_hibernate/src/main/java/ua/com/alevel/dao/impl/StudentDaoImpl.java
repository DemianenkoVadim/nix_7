package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.controller.UIController;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.HibernateUtil;

import javax.swing.*;

import static ua.com.alevel.util.SessionUtil.openSaveCloseSession;

@Log4j2
public class StudentDaoImpl implements StudentDao {

    @Override
    public void addStudent(Student student) {
        log.info("Stars adding and saving Student to base");
        openSaveCloseSession(student);
    }

    @Override
    public void getsAndPrintsAllStudents() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            String sql = "SELECT * FROM A_Level_Students";
            var query = session.createNativeQuery(sql).addEntity(Student.class);
            var studentList = query.list();
            System.out.println(studentList);
            new UIController().startsTheProgram();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error I/O", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
