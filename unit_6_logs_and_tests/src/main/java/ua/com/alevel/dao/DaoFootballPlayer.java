package ua.com.alevel.dao;

import ua.com.alevel.entity.FootballPlayer;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;

public interface DaoFootballPlayer {

    void createFootballPlayerInRegistrationBase(FootballPlayer player);

    void updateFootballPlayerInRegistrationBase(FootballPlayer player) throws NonExistentIDException;

    void deleteFootballPlayerFromRegistrationBase(String id) throws Exception;

    FootballPlayer findFootballPlayerFromRegistrationBaseById(String id) throws NonExistentIDException;

    void findAllFootballPlayersFromRegistrationBase() throws EmptyRegistrationBaseException, NonExistentIDException;
}
