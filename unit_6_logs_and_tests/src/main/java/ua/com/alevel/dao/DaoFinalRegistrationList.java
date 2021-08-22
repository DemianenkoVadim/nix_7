package ua.com.alevel.dao;

import ua.com.alevel.entity.FinalRegistrationList;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;

public interface DaoFinalRegistrationList {

    void createFinalRegistrationList(FinalRegistrationList registrationList) throws EmptyRegistrationBaseException;

    void updateFinalRegistrationList(FinalRegistrationList registrationList) throws EmptyRegistrationBaseException;

    void deletePositionInFinalRegistrationList(String id) throws NonExistentIDException;

    FinalRegistrationList findRegistrationById(String id) throws NonExistentIDException;

    void readAllRegistrationList() throws EmptyRegistrationBaseException, NonExistentIDException;

    void showsAllFootballPlayersInChosenByIdFootballTeam(String id) throws NonExistentIDException, EmptyRegistrationBaseException;

    void showsAllFootballTeamsInChosenByIdFootballPlayer(String id) throws NonExistentIDException;
}
