package ua.com.alevel.dao;

import ua.com.alevel.entity.Course;

import java.sql.SQLException;

public interface CourseDao {

    void addCourse(Course course) throws SQLException;
}
