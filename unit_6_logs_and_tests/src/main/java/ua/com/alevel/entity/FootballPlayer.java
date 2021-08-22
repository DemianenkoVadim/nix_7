package ua.com.alevel.entity;

import ua.com.alevel.customutil.DynamicArray;

import java.util.Objects;

public class FootballPlayer extends BaseEntity {

    private String footballPlayerId;
    private String firstName;
    private String lastName;
    private String footballPlayerPosition;
    private String footballPlayerNational;
    private int ageOfFootballPlayer;
    private final DynamicArray<Integer> footballPlayers = new DynamicArray<>();

    public String getFootballPlayerId() {
        return footballPlayerId;
    }

    public void setFootballPlayerId(String footballPlayerId) {
        this.footballPlayerId = footballPlayerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFootballPlayerPosition() {
        return footballPlayerPosition;
    }

    public void setFootballPlayerPosition(String footballPlayerPosition) {
        this.footballPlayerPosition = footballPlayerPosition;
    }

    public String getFootballPlayerNational() {
        return footballPlayerNational;
    }

    public void setFootballPlayerNational(String footballPlayerNational) {
        this.footballPlayerNational = footballPlayerNational;
    }

    public int getAgeOfFootballPlayer() {
        return ageOfFootballPlayer;
    }

    public void setAgeOfFootballPlayer(int ageOfFootballPlayer) {
        this.ageOfFootballPlayer = ageOfFootballPlayer;
    }

    @Override
    public String toString() {
        return "First name - " + firstName + '\n' +
                "Last lame - " + lastName + '\n' +
                "Position - " + footballPlayerPosition + '\n' +
                "National - " + footballPlayerNational + '\n' +
                "Age - " + ageOfFootballPlayer + '\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballPlayer player = (FootballPlayer) o;
        return ageOfFootballPlayer == player.ageOfFootballPlayer &&
                Objects.equals(footballPlayerId, player.footballPlayerId) &&
                Objects.equals(firstName, player.firstName) &&
                Objects.equals(lastName, player.lastName) &&
                Objects.equals(footballPlayerPosition, player.footballPlayerPosition) &&
                Objects.equals(footballPlayerNational, player.footballPlayerNational) &&
                Objects.equals(footballPlayers, player.footballPlayers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(footballPlayerId, firstName, lastName, footballPlayerPosition, footballPlayerNational, ageOfFootballPlayer, footballPlayers);
    }
}
