package ua.com.alevel.entity;

import ua.com.alevel.customutil.DynamicArray;

import java.util.Objects;

public class FootballTeam extends BaseEntity {

    private String footballTeamId;
    private String nameOfTheFootballTeam;
    private String locationCountryFootballTeam;
    private String locationTownFootballTeam;
    private int yearOfFootballTeamWasFounded;
    private final DynamicArray<Integer> footballTeams = new DynamicArray<>();

    public String getFootballTeamId() {
        return footballTeamId;
    }

    public void setFootballTeamId(String footballTeamId) {
        this.footballTeamId = footballTeamId;
    }

    public String getNameOfTheFootballTeam() {
        return nameOfTheFootballTeam;
    }

    public void setNameOfTheFootballTeam(String nameOfTheFootballTeam) {
        this.nameOfTheFootballTeam = nameOfTheFootballTeam;
    }

    public String getLocationCountryFootballTeam() {
        return locationCountryFootballTeam;
    }

    public void setLocationCountryFootballTeam(String locationCountryFootballTeam) {
        this.locationCountryFootballTeam = locationCountryFootballTeam;
    }

    public String getLocationTownFootballTeam() {
        return locationTownFootballTeam;
    }

    public void setLocationTownFootballTeam(String locationTownFootballTeam) {
        this.locationTownFootballTeam = locationTownFootballTeam;
    }

    public int getYearOfFootballTeamWasFounded() {
        return yearOfFootballTeamWasFounded;
    }

    public void setYearOfFootballTeamWasFounded(int yearOfFootballTeamWasFounded) {
        this.yearOfFootballTeamWasFounded = yearOfFootballTeamWasFounded;
    }

    @Override
    public String toString() {
        return "Name Of The Football Team - " + nameOfTheFootballTeam + '\n' +
                "Country of The Football Team - " + locationCountryFootballTeam + '\n' +
                "Town of the Football Team - " + locationTownFootballTeam + '\n' +
                "Football team was founded - " + yearOfFootballTeamWasFounded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballTeam team = (FootballTeam) o;
        return yearOfFootballTeamWasFounded == team.yearOfFootballTeamWasFounded &&
                Objects.equals(footballTeamId, team.footballTeamId) &&
                Objects.equals(nameOfTheFootballTeam, team.nameOfTheFootballTeam) &&
                Objects.equals(locationCountryFootballTeam, team.locationCountryFootballTeam) &&
                Objects.equals(locationTownFootballTeam, team.locationTownFootballTeam) &&
                Objects.equals(footballTeams, team.footballTeams);
    }

    @Override
    public int hashCode() {
        return Objects.hash(footballTeamId, nameOfTheFootballTeam, locationCountryFootballTeam, locationTownFootballTeam, yearOfFootballTeamWasFounded, footballTeams);
    }
}
