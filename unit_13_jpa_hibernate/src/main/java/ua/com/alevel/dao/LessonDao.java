package ua.com.alevel.dao;

import ua.com.alevel.dto.LessonDto;
import ua.com.alevel.entity.Lesson;

import java.sql.SQLException;

public interface LessonDao {

    void addLesson(Lesson lesson) throws SQLException;

    LessonDto getNearestStudentsLessonByStudentId(Integer idStudent);
}
