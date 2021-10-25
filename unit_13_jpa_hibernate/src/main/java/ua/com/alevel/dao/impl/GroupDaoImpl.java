package ua.com.alevel.dao.impl;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;

import static ua.com.alevel.util.SessionUtil.openSaveCloseSession;

@Log4j2
public class GroupDaoImpl implements GroupDao {

    @Override
    public void addGroup(Group group) {
        log.info("Stars adding and saving Group to base");
        openSaveCloseSession(group);
    }
}
