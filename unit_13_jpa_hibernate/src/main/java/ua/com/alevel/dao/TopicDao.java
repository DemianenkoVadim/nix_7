package ua.com.alevel.dao;

import ua.com.alevel.entity.Topic;

import java.sql.SQLException;

public interface TopicDao {

    void addTopic(Topic topic) throws SQLException;
}
