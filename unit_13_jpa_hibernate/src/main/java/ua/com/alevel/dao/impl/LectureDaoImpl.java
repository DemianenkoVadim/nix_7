package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.LectureDao;
import ua.com.alevel.entity.Lecturer;

import static ua.com.alevel.util.SessionUtil.openSaveCloseSession;

@Log4j2
public class LectureDaoImpl implements LectureDao {

    @Override
    public void addLecture(Lecturer lecturer) {
        log.info("Stars adding and saving Lecture to base");
        openSaveCloseSession(lecturer);
    }
}
