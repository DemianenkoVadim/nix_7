package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import ua.com.alevel.dao.LessonDao;
import ua.com.alevel.dto.LessonDto;
import ua.com.alevel.entity.Lesson;
import ua.com.alevel.entity.Student;
import ua.com.alevel.util.HibernateUtil;

import javax.swing.*;
import java.time.Instant;

import static ua.com.alevel.util.ConstantsApplicationUnit_13.FIRST_DATA_TYPE_IN_LIST;
import static ua.com.alevel.util.SessionUtil.openSaveCloseSession;

@Log4j2
public class LessonDaoImpl implements LessonDao {

    LessonDto lessonDto = new LessonDto();

    @Override
    public void addLesson(Lesson lesson) {
        log.info("Stars adding and saving Lesson to base");
        openSaveCloseSession(lesson);
    }

    @Override
    public LessonDto getNearestStudentsLessonByStudentId(Integer idStudent) {
        Session session = null;
        Student student;
        Lesson lesson;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            log.debug("Finding Student by Student ID, and get all information about student ");
            var studentsQuery = session.createQuery("From Student s where s.id =: id").setParameter("id", idStudent);
            student = (Student) studentsQuery.getSingleResult();
            log.debug("Finding Lessons by student`s group and get list all futures lessons, sorts them by ascending");
            var lessonQuery = session.createQuery("from Lesson l where l.groups = :group AND l.startDateTime > :datetime order by l.startDateTime asc");
            lessonQuery.setParameter("group", student.getGroups());
            lessonQuery.setParameter("datetime", Instant.now());
            lesson = (Lesson) lessonQuery.list().get(FIRST_DATA_TYPE_IN_LIST);
            createNearestStudentsLessonInformation(lesson);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "I/O Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return lessonDto;
    }

    private void createNearestStudentsLessonInformation(Lesson lesson) {
        lessonDto.setLessonId(lesson.getId());
        lessonDto.setGroupName(lesson.getGroups().getGroupName());
        lessonDto.setTopicLesson(lesson.getTopics().getTopicName());
        lessonDto.setLector(lesson.getTopics().getLecturer().getFirstName() + lesson.getTopics().getLecturer().getLastName());
        lessonDto.setStartLessonDataTime(lesson.getStartDateTime());
        lessonDto.setEndLessonDataTime(lesson.getEndDataTime());
    }
}