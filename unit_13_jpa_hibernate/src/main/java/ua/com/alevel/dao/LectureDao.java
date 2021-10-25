package ua.com.alevel.dao;

import ua.com.alevel.entity.Lecturer;

import java.sql.SQLException;

public interface LectureDao {

    void addLecture(Lecturer lecturer) throws SQLException;
}
