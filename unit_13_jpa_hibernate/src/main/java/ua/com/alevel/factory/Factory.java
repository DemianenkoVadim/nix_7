package ua.com.alevel.factory;

import ua.com.alevel.dao.*;
import ua.com.alevel.dao.impl.*;

public class Factory {

    private static CourseDao courseDao = null;
    private static GroupDao groupDao = null;
    private static LectureDao lectureDao = null;
    private static LessonDao lessonDao = null;
    private static StudentDao studentDao = null;
    private static TopicDao topicDao = null;

    public static CourseDao getCourseDao() {
        if (courseDao == null) {
            courseDao = new CourseDaoImpl();
        }
        return courseDao;
    }

    public static GroupDao getGroupDao() {
        if (groupDao == null) {
            groupDao = new GroupDaoImpl();
        }
        return groupDao;
    }

    public static LectureDao getLectureDao() {
        if (lectureDao == null) {
            lectureDao = new LectureDaoImpl();
        }
        return lectureDao;
    }

    public static LessonDao getLessonDao() {
        if (lessonDao == null) {
            lessonDao = new LessonDaoImpl();
        }
        return lessonDao;
    }

    public static StudentDao getStudentDao() {
        if (studentDao == null) {
            studentDao = new StudentDaoImpl();
        }
        return studentDao;
    }

    public static TopicDao getTopicDao() {
        if (topicDao == null) {
            topicDao = new TopicDaoImpl();
        }
        return topicDao;
    }
}
