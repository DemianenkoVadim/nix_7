package ua.com.alevel.dao;

import ua.com.alevel.entity.Student;

import java.sql.SQLException;

public interface StudentDao {

    void addStudent(Student student) throws SQLException;

    void getsAndPrintsAllStudents() throws SQLException;
}
