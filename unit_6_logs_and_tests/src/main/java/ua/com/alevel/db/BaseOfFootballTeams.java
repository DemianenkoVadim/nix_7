package ua.com.alevel.db;

import ua.com.alevel.customutil.DynamicArray;
import ua.com.alevel.entity.FootballTeam;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;

import java.util.UUID;

import static java.lang.System.out;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class BaseOfFootballTeams {

    private static final BaseOfFootballTeams instance = new BaseOfFootballTeams();
    private final DynamicArray<FootballTeam> teams = new DynamicArray<>();
    private static final int ARRAY_INDEX = 0;

    private BaseOfFootballTeams() {
    }

    public static BaseOfFootballTeams getInstance() {
        return instance;
    }

    public void createFootballTeamInRegistrationBase(FootballTeam team) {
        team.setAvailable(true);
        team.setFootballTeamId(generateId());
        teams.addAdditionalPoint(team);
    }

    public void updateFootballTeamInRegistrationBase(FootballTeam team) throws NonExistentIDException {
        if (!teams.isEmpty()) {
            for (int footballCrew = ARRAY_INDEX; footballCrew < teams.getSize(); footballCrew++) {
                if (teams.get(footballCrew).getFootballTeamId().equals(team.getFootballTeamId())) {
                    teams.get(footballCrew).setNameOfTheFootballTeam(team.getNameOfTheFootballTeam());
                    teams.get(footballCrew).setLocationCountryFootballTeam(team.getLocationCountryFootballTeam());
                    teams.get(footballCrew).setLocationTownFootballTeam(team.getLocationTownFootballTeam());
                    teams.get(footballCrew).setYearOfFootballTeamWasFounded(team.getYearOfFootballTeamWasFounded());
                    teams.get(footballCrew).setAvailable(true);
                }
            }
        } else {
            throw new NonExistentIDException();
        }
    }

    public void deleteFootballTeamFromRegistrationBase(String id) throws NonExistentIDException {
        if (!teams.isEmpty()) {
            for (int footballCrew = ARRAY_INDEX; footballCrew < teams.getSize(); footballCrew++) {
                if (teams.get(footballCrew).getFootballTeamId().equals(id) && teams.get(footballCrew).isAvailable()) {
                    teams.get(footballCrew).setAvailable(false);
                }
            }
        } else {
            throw new NonExistentIDException();
        }
    }

    public void findAllAndPrintFootballTeamsFromRegistrationBase() throws EmptyRegistrationBaseException {
        if (!teams.isEmpty()) {
            for (int footballCrew = ARRAY_INDEX; footballCrew < teams.getSize(); footballCrew++) {
                if (teams.get(footballCrew).isAvailable()) {
                    printsFootballTeamsFromRegistration(footballCrew);
                }
            }
        } else {
            throw new EmptyRegistrationBaseException();
        }
    }

    private void printsFootballTeamsFromRegistration(int footballCrew) {
        out.println(FOOTBALL_TEAM_ID_PRINT + teams.get(footballCrew).getFootballTeamId() + "\n"
                + FOOTBALL_TEAM_NAME_PRINT + teams.get(footballCrew).getNameOfTheFootballTeam() + "\n"
                + FOOTBALL_TEAM_LOCATION_COUNTRY_PRINT + teams.get(footballCrew).getLocationCountryFootballTeam() + "\n"
                + FOOTBALL_TEAM_LOCATION_TOWN_PRINT + teams.get(footballCrew).getLocationTownFootballTeam() + "\n"
                + FOOTBALL_TEAM_YEAR_FOUNDATION_PRINT + teams.get(footballCrew).getYearOfFootballTeamWasFounded());
        out.println();
    }

    public FootballTeam findFootballTeamFromRegistrationBaseById(String id) throws NonExistentIDException {
        for (int footballCrew = ARRAY_INDEX; footballCrew < teams.getSize(); footballCrew++) {
            if (teams.get(footballCrew).getFootballTeamId().equals(id)) {
                return teams.get(footballCrew);
            }
        }
        throw new NonExistentIDException();
    }

    private String generateId() {
        String idOfTeam = UUID.randomUUID().toString();
        for (int oneTeam = ARRAY_INDEX; oneTeam < teams.getSize(); oneTeam++) {
            for (int anotherOne = ARRAY_INDEX; anotherOne < teams.getSize(); anotherOne++) {
                if (oneTeam == anotherOne) continue;
                if (teams.get(anotherOne).getFootballTeamId().equals(teams.get(oneTeam).getFootballTeamId())) {
                    return generateId();
                }
            }
        }
        return idOfTeam;
    }
}
