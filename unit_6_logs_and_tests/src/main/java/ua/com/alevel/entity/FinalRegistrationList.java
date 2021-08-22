package ua.com.alevel.entity;

import ua.com.alevel.customutil.DynamicArray;

public class FinalRegistrationList extends BaseEntity {

    private String footballPlayerID;
    private String footballTeamID;
    private String finalRegistrationListID;
    private final DynamicArray<Integer> registrationList = new DynamicArray<>();

    public String getFootballPlayerID() {
        return footballPlayerID;
    }

    public void setFootballPlayerID(String footballPlayerID) {
        this.footballPlayerID = footballPlayerID;
    }

    public String getFootballTeamID() {
        return footballTeamID;
    }

    public void setFootballTeamID(String footballTeamID) {
        this.footballTeamID = footballTeamID;
    }

    public String getFinalRegistrationListID() {
        return finalRegistrationListID;
    }

    public void setFinalRegistrationListID(String finalRegistrationListID) {
        this.finalRegistrationListID = finalRegistrationListID;
    }

    @Override
    public String toString() {
        return "football Player ID - " + footballPlayerID + '\n' +
                "football Team ID - " + footballTeamID + '\n' +
                "final Registration List ID - " + finalRegistrationListID;
    }
}
