package ua.com.alevel.dao;

import ua.com.alevel.entity.FootballTeam;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;

public interface DaoFootballTeam {

    void createFootballTeamInRegistrationBase(FootballTeam team);

    void updateFootballTeamInRegistrationBase(FootballTeam team);

    void deleteFootballTeamFromRegistrationBase(String id) throws EmptyRegistrationBaseException, NonExistentIDException;

    FootballTeam findFootballTeamFromRegistrationListById(String id) throws NonExistentIDException;

    void findAllFootballTeamsFromRegistrationBase() throws EmptyRegistrationBaseException;
}
