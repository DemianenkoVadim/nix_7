package ua.com.alevel;

import org.hibernate.Session;
import ua.com.alevel.controller.UIController;
import ua.com.alevel.entity.*;
import ua.com.alevel.util.HibernateUtil;

import java.time.Instant;
import java.util.Arrays;

import static ua.com.alevel.factory.Factory.*;

public class Unit13Main {

    public static void main(String[] args) throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Course javaCourse = new Course();
        Course javaScriptCourse = new Course();

        javaCourse.setItCourseName("Java");
        javaScriptCourse.setItCourseName("JavaScript");

        for (Course course : Arrays.asList(javaCourse, javaScriptCourse)) {
            getCourseDao().addCourse(course);
        }

        Lecturer javaLecture = new Lecturer();
        Lecturer javaScriptLecture = new Lecturer();

        javaLecture.setFirstName("Steve");
        javaLecture.setLastName("Robinson");
        javaLecture.setLectureEmail("stro@gmail.com");
        javaLecture.setLectureTelephone("+30987894563");

        javaScriptLecture.setFirstName("Oliver");
        javaScriptLecture.setLastName("Taylor");
        javaScriptLecture.setLectureEmail("olta@gmail.com");
        javaScriptLecture.setLectureTelephone("+30975471245");

        for (Lecturer lecturer : Arrays.asList(javaLecture, javaScriptLecture)) {
            getLectureDao().addLecture(lecturer);
        }

        Group firstGroup = new Group();
        Group secondGroup = new Group();
        Group thirdGroup = new Group();
        Group fourthGroup = new Group();

        firstGroup.setGroupName("A-Level-1");
        secondGroup.setGroupName("A-Level-2");
        thirdGroup.setGroupName("A-Level-3");
        fourthGroup.setGroupName("A-Level_4");

        for (Group group : Arrays.asList(firstGroup, secondGroup, thirdGroup, fourthGroup)) {
            getGroupDao().addGroup(group);
        }

        firstGroup.setCourses(javaCourse);
        secondGroup.setCourses(javaScriptCourse);
        thirdGroup.setCourses(javaCourse);
        fourthGroup.setCourses(javaScriptCourse);

        Topic firstJavaTheme = new Topic();
        Topic secondJavaTheme = new Topic();
        Topic firstJavaScriptTheme = new Topic();
        Topic secondJavaScriptTheme = new Topic();

        firstJavaTheme.setTopicName("Java Core");
        secondJavaTheme.setTopicName("Java OOP");
        firstJavaScriptTheme.setTopicName("JavaScript Basic");
        secondJavaScriptTheme.setTopicName("JavaScript OOP");

        firstJavaTheme.setCourses(javaCourse);
        secondJavaTheme.setCourses(javaCourse);
        firstJavaScriptTheme.setCourses(javaScriptCourse);
        secondJavaScriptTheme.setCourses(javaScriptCourse);

        firstJavaTheme.setLecturer(javaLecture);
        secondJavaTheme.setLecturer(javaLecture);
        firstJavaScriptTheme.setLecturer(javaScriptLecture);
        secondJavaScriptTheme.setLecturer(javaScriptLecture);

        for (Topic topic : Arrays.asList(firstJavaTheme, secondJavaTheme, firstJavaScriptTheme, secondJavaScriptTheme)) {
            getTopicDao().addTopic(topic);
        }

        Student firstStudent = new Student();
        Student secondStudent = new Student();
        Student thirdStudent = new Student();
        Student fourthStudent = new Student();
        Student fivesStudent = new Student();
        Student sixStudent = new Student();
        Student sevensStudent = new Student();
        Student eightsStudent = new Student();

        firstStudent.setFirstName("Vadim");
        firstStudent.setLastName("Dominance");
        firstStudent.setStudentEmail("vaddom@gmail.com");
        firstStudent.setStudentTelephone("+380967614540");
        firstStudent.setGroups(firstGroup);

        secondStudent.setFirstName("Ivan");
        secondStudent.setLastName("Sokol");
        secondStudent.setStudentEmail("ivso@gmail.com");
        secondStudent.setStudentTelephone("+380927614540");
        secondStudent.setGroups(firstGroup);

        thirdStudent.setFirstName("Ilya");
        thirdStudent.setLastName("Bird");
        thirdStudent.setStudentEmail("ilbi@gmail.com");
        thirdStudent.setStudentTelephone("+380927624540");
        thirdStudent.setGroups(secondGroup);

        fourthStudent.setFirstName("Gleb");
        fourthStudent.setLastName("Brod");
        fourthStudent.setStudentEmail("glbro@gmail.com");
        fourthStudent.setStudentTelephone("+380927622340");
        fourthStudent.setGroups(secondGroup);

        fivesStudent.setFirstName("Jhon");
        fivesStudent.setLastName("Johnson");
        fivesStudent.setStudentEmail("jojo@gmail.com");
        fivesStudent.setStudentTelephone("+380923452340");
        fivesStudent.setGroups(thirdGroup);

        sixStudent.setFirstName("Jerry");
        sixStudent.setLastName("Henson");
        sixStudent.setStudentEmail("jehe@gmail.com");
        sixStudent.setStudentTelephone("+380927622323");
        sixStudent.setGroups(thirdGroup);

        sevensStudent.setFirstName("Tom");
        sevensStudent.setLastName("Cat");
        sevensStudent.setStudentEmail("toca@gmail.com");
        sevensStudent.setStudentTelephone("+380935874125");
        sevensStudent.setGroups(fourthGroup);

        eightsStudent.setFirstName("Tommy");
        eightsStudent.setLastName("Li");
        eightsStudent.setStudentEmail("toli@gmail.com");
        eightsStudent.setStudentTelephone("+380955874125");
        eightsStudent.setGroups(fourthGroup);

        for (Student student : Arrays.asList(firstStudent, secondStudent, thirdStudent,
                fourthStudent, fivesStudent, sixStudent, sevensStudent, eightsStudent)) {
            getStudentDao().addStudent(student);
        }

        Lesson firstGroupFirstJavaLesson = new Lesson();
        Lesson firstGroupSecondJavaLesson = new Lesson();
        Lesson thirdGroupFirstJavaLesson = new Lesson();
        Lesson thirdGroupSecondJavaLesson = new Lesson();
        Lesson secondGroupFirstJavaScriptLesson = new Lesson();
        Lesson secondGroupSecondJavaScriptLesson = new Lesson();
        Lesson fourthGroupFirstJavaScriptLesson = new Lesson();
        Lesson fourthGroupSecondJavaScriptLesson = new Lesson();

        firstGroupFirstJavaLesson.setStartDateTime(Instant.parse("2021-10-25T19:00:00.00Z"));
        firstGroupFirstJavaLesson.setEndDataTime(Instant.parse("2021-10-25T22:00:00.00Z"));
        firstGroupSecondJavaLesson.setStartDateTime(Instant.parse("2021-10-26T19:00:00.00Z"));
        firstGroupSecondJavaLesson.setEndDataTime(Instant.parse("2021-10-26T22:00:00.00Z"));

        thirdGroupFirstJavaLesson.setStartDateTime(Instant.parse("2021-10-21T19:00:00.00Z"));
        thirdGroupFirstJavaLesson.setEndDataTime(Instant.parse("2021-10-21T22:00:00.00Z"));
        thirdGroupSecondJavaLesson.setStartDateTime(Instant.parse("2021-10-26T19:00:00.00Z"));
        thirdGroupSecondJavaLesson.setEndDataTime(Instant.parse("2021-10-26T22:00:00.00Z"));

        secondGroupFirstJavaScriptLesson.setStartDateTime(Instant.parse("2021-10-23T19:00:00.00Z"));
        secondGroupFirstJavaScriptLesson.setEndDataTime(Instant.parse("2021-10-23T22:00:00.00Z"));
        secondGroupSecondJavaScriptLesson.setStartDateTime(Instant.parse("2021-10-27T19:00:00.00Z"));
        secondGroupSecondJavaScriptLesson.setEndDataTime(Instant.parse("2021-10-27T22:00:00.00Z"));

        fourthGroupFirstJavaScriptLesson.setStartDateTime(Instant.parse("2021-10-28T19:00:00.00Z"));
        fourthGroupFirstJavaScriptLesson.setEndDataTime(Instant.parse("2021-10-28T22:00:00.00Z"));
        fourthGroupSecondJavaScriptLesson.setStartDateTime(Instant.parse("2021-10-30T19:00:00.00Z"));
        fourthGroupSecondJavaScriptLesson.setEndDataTime(Instant.parse("2021-10-30T22:00:00.00Z"));

        firstGroupFirstJavaLesson.setGroups(firstGroup);
        firstGroupSecondJavaLesson.setGroups(firstGroup);

        thirdGroupFirstJavaLesson.setGroups(thirdGroup);
        thirdGroupSecondJavaLesson.setGroups(thirdGroup);

        secondGroupFirstJavaScriptLesson.setGroups(secondGroup);
        secondGroupSecondJavaScriptLesson.setGroups(secondGroup);

        fourthGroupFirstJavaScriptLesson.setGroups(fourthGroup);
        fourthGroupSecondJavaScriptLesson.setGroups(fourthGroup);

        firstGroupFirstJavaLesson.setTopics(firstJavaTheme);
        firstGroupSecondJavaLesson.setTopics(secondJavaTheme);

        thirdGroupFirstJavaLesson.setTopics(firstJavaTheme);
        thirdGroupSecondJavaLesson.setTopics(secondJavaTheme);
        secondGroupFirstJavaScriptLesson.setTopics(firstJavaScriptTheme);
        secondGroupSecondJavaScriptLesson.setTopics(secondJavaScriptTheme);
        fourthGroupFirstJavaScriptLesson.setTopics(firstJavaScriptTheme);
        fourthGroupSecondJavaScriptLesson.setTopics(secondJavaScriptTheme);

        for (Lesson lesson : Arrays.asList(firstGroupFirstJavaLesson, firstGroupSecondJavaLesson,
                thirdGroupFirstJavaLesson, thirdGroupSecondJavaLesson, secondGroupFirstJavaScriptLesson,
                secondGroupSecondJavaScriptLesson, fourthGroupFirstJavaScriptLesson, fourthGroupSecondJavaScriptLesson)) {
            getLessonDao().addLesson(lesson);
        }
        new UIController().startsTheProgram();

        session.close();
        HibernateUtil.shutdown();
    }
}
