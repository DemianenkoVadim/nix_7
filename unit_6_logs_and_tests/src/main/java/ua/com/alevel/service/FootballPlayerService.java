package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.DaoFootballPlayer;
import ua.com.alevel.entity.FootballPlayer;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;
import ua.com.alevel.impl.DaoFootballPlayerImpl;

public class FootballPlayerService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("INFO");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("WARN");

    DaoFootballPlayer daoPlayer = new DaoFootballPlayerImpl();

    public void createFootballPlayerInRegistrationBase(FootballPlayer player) {
        LOGGER_INFO.info("Creating Football Player in Registration Base");
        daoPlayer.createFootballPlayerInRegistrationBase(player);
    }

    public void updateFootballPlayerInRegistrationBase(FootballPlayer player) throws NonExistentIDException {
        LOGGER_INFO.info("Update Football Player in Registration Base");
        daoPlayer.updateFootballPlayerInRegistrationBase(player);
    }

    public void deleteFootballPlayerFromRegistrationBase(String id) throws Exception {
        LOGGER_WARN.info("Deleting Football Player from Registration Base");
        daoPlayer.deleteFootballPlayerFromRegistrationBase(id);
    }

    public FootballPlayer findFootballPlayerFromRegistrationBaseById(String id) throws NonExistentIDException {
        LOGGER_INFO.info("Finding football player from registration Base");
        return daoPlayer.findFootballPlayerFromRegistrationBaseById(id);
    }

    public void findAllFootballPlayersFromRegistrationBase() throws EmptyRegistrationBaseException, NonExistentIDException {
        LOGGER_INFO.info("Starting find all football player from registration Base");
        daoPlayer.findAllFootballPlayersFromRegistrationBase();
    }
}
