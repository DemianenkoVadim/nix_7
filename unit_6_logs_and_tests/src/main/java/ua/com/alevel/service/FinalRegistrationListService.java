package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.DaoFinalRegistrationList;
import ua.com.alevel.entity.FinalRegistrationList;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;
import ua.com.alevel.impl.DaoFinalRegistrationListImpl;

public class FinalRegistrationListService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("INFO");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("WARN");

    DaoFinalRegistrationList daoRegistrationList = new DaoFinalRegistrationListImpl();

    public void createFinalRegistrationList(FinalRegistrationList registrationList) throws EmptyRegistrationBaseException {
        LOGGER_INFO.info("Creating Final Registration List");
        daoRegistrationList.createFinalRegistrationList(registrationList);
    }

    public void updateFinalRegistrationList(FinalRegistrationList registrationList) throws EmptyRegistrationBaseException {
        LOGGER_INFO.info("Update Final Registration List");
        daoRegistrationList.updateFinalRegistrationList(registrationList);
    }

    public void deletePositionInFinalRegistrationList(String id) throws NonExistentIDException {
        LOGGER_WARN.info("Delete Registration Final List");
        daoRegistrationList.deletePositionInFinalRegistrationList(id);
    }

    public void readAllRegistrationList() throws EmptyRegistrationBaseException, NonExistentIDException {
        LOGGER_INFO.info("Read All list");
        daoRegistrationList.readAllRegistrationList();
    }

    public FinalRegistrationList findRegistrationById(String id) throws NonExistentIDException {
        LOGGER_INFO.info("Find Registration by ID");
        return daoRegistrationList.findRegistrationById(id);
    }

    public void showsAllFootballPlayersInChosenByIdFootballTeam(String id) throws NonExistentIDException, EmptyRegistrationBaseException {
        LOGGER_INFO.info("Find All Football Players in Chosen Football team");
        daoRegistrationList.showsAllFootballPlayersInChosenByIdFootballTeam(id);
    }

    public void showsAllFootballTeamsInChosenByIdFootballPlayer(String id) throws NonExistentIDException {
        LOGGER_INFO.info("Find All Football Teams, Football Player has chosen");
        daoRegistrationList.showsAllFootballTeamsInChosenByIdFootballPlayer(id);
    }
}
