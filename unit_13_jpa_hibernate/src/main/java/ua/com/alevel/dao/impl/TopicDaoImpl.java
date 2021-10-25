package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.TopicDao;
import ua.com.alevel.entity.Topic;

import static ua.com.alevel.util.SessionUtil.openSaveCloseSession;
@Log4j2
public class TopicDaoImpl implements TopicDao {

    @Override
    public void addTopic(Topic theme) {
        log.info("Stars adding and saving Topic to base");
        openSaveCloseSession(theme);
    }
}
