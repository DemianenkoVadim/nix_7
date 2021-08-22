package ua.com.alevel.generationutil;

import ua.com.alevel.entity.FootballTeam;

public class FootballTeamGenerationUtil {

    public static final String NAME_OF_THE_FOOTBALL_TEAM = "TESTING NAME";
    public static final String LOCATION_COUNTRY_FOOTBALL_TEAM = "TESTING NAME OF THE COUNTRY";
    public static final String LOCATION_TOWN_FOOTBALL_TEAM = "TESTING NAME OF THE TOWN";
    public static final int YEAR_OF_FOOTBALL_TEAM_WAS_FOUNDED = 1905;

    public static FootballTeam generateFootballTeam() {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setNameOfTheFootballTeam(NAME_OF_THE_FOOTBALL_TEAM);
        footballTeam.setLocationCountryFootballTeam(LOCATION_COUNTRY_FOOTBALL_TEAM);
        footballTeam.setLocationTownFootballTeam(LOCATION_TOWN_FOOTBALL_TEAM);
        footballTeam.setYearOfFootballTeamWasFounded(YEAR_OF_FOOTBALL_TEAM_WAS_FOUNDED);
        return footballTeam;
    }

    public static FootballTeam generateFootballTeam(String nameOfTheFootballTeam, String locationCountryFootballTeam, String locationTownFootballTeam, int yearOfFootballTeamWasFounded) {
        FootballTeam footballTeam = new FootballTeam();
        footballTeam.setNameOfTheFootballTeam(nameOfTheFootballTeam);
        footballTeam.setLocationCountryFootballTeam(locationCountryFootballTeam);
        footballTeam.setLocationTownFootballTeam(locationTownFootballTeam);
        footballTeam.setYearOfFootballTeamWasFounded(yearOfFootballTeamWasFounded);
        return footballTeam;
    }
}
