package ua.com.alevel.service;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.LessonDao;
import ua.com.alevel.dao.impl.LessonDaoImpl;
import ua.com.alevel.dto.LessonDto;

@Log4j2
public class LessonService {

    LessonDao daoLesson = new LessonDaoImpl();

    public LessonDto getNearestStudentsLessonByStudentId(Integer id) {
        log.info("Shows nearest Student`s Lesson");
        return daoLesson.getNearestStudentsLessonByStudentId(id);
    }
}
