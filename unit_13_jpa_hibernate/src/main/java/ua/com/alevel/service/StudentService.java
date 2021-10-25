package ua.com.alevel.service;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.impl.StudentDaoImpl;

import java.sql.SQLException;

@Log4j2
public class StudentService {

    StudentDao daoStudent = new StudentDaoImpl();

    public void getAllStudents() throws SQLException {
        log.info("Start finding all students in DataBase");
        daoStudent.getsAndPrintsAllStudents();
    }
}
