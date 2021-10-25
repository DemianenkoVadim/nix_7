package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.CourseDao;
import ua.com.alevel.entity.Course;

import static ua.com.alevel.util.SessionUtil.openSaveCloseSession;

@Log4j2
public class CourseDaoImpl implements CourseDao {

    @Override
    public void addCourse(Course course) {
        log.info("Stars adding and saving Course to base");
        openSaveCloseSession(course);
    }
}
