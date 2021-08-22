package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.DaoFootballTeam;
import ua.com.alevel.entity.FootballTeam;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;
import ua.com.alevel.impl.DaoFootballTeamImpl;

public class FootballTeamService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("INFO");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("WARN");

    DaoFootballTeam daoTeam = new DaoFootballTeamImpl();

    public void createFootballTeamInRegistrationBase(FootballTeam team) {
        LOGGER_INFO.info("Creating football Team in Registration Base");
        daoTeam.createFootballTeamInRegistrationBase(team);
    }

    public void updateFootballTeamInRegistrationBase(FootballTeam team) {
        LOGGER_INFO.info("Update Football Team in Registration Base");
        daoTeam.updateFootballTeamInRegistrationBase(team);
    }

    public void deleteFootballTeamFromRegistrationBase(String id) throws EmptyRegistrationBaseException, NonExistentIDException {
        LOGGER_WARN.info("Delete Football Team in Registration Base");
        daoTeam.deleteFootballTeamFromRegistrationBase(id);
    }

    public FootballTeam findFootballTeamFromRegistrationListById(String id) throws NonExistentIDException {
        LOGGER_INFO.info("Start find Football Team in Registration Base");
        return daoTeam.findFootballTeamFromRegistrationListById(id);
    }

    public void findAllFootballTeamsFromRegistrationBase() throws EmptyRegistrationBaseException {
        LOGGER_INFO.info("Find Football team in Registration Base");
        daoTeam.findAllFootballTeamsFromRegistrationBase();
    }
}
