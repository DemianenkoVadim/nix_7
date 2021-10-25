package ua.com.alevel.dao;

import ua.com.alevel.entity.Group;

import java.sql.SQLException;

public interface GroupDao {

    void addGroup(Group group) throws SQLException;

}
