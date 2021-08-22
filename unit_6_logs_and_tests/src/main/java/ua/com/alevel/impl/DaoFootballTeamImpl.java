package ua.com.alevel.impl;

import lombok.SneakyThrows;
import ua.com.alevel.dao.DaoFootballTeam;
import ua.com.alevel.db.BaseOfFootballTeams;
import ua.com.alevel.entity.FootballTeam;

public class DaoFootballTeamImpl implements DaoFootballTeam {

    private final BaseOfFootballTeams registrationBaseFootballTeams = BaseOfFootballTeams.getInstance();

    @SneakyThrows
    @Override
    public void createFootballTeamInRegistrationBase(FootballTeam team) {
        registrationBaseFootballTeams.createFootballTeamInRegistrationBase(team);
    }

    @SneakyThrows
    @Override
    public void updateFootballTeamInRegistrationBase(FootballTeam team) {
        registrationBaseFootballTeams.updateFootballTeamInRegistrationBase(team);
    }

    @SneakyThrows
    @Override
    public void deleteFootballTeamFromRegistrationBase(String id) {
        registrationBaseFootballTeams.deleteFootballTeamFromRegistrationBase(id);
    }

    @SneakyThrows
    @Override
    public FootballTeam findFootballTeamFromRegistrationListById(String id) {
        return registrationBaseFootballTeams.findFootballTeamFromRegistrationBaseById(id);
    }

    @SneakyThrows
    @Override
    public void findAllFootballTeamsFromRegistrationBase() {
        registrationBaseFootballTeams.findAllAndPrintFootballTeamsFromRegistrationBase();
    }
}
