package ua.com.alevel.generationutil;

import ua.com.alevel.entity.FootballPlayer;

public class FootballPlayerGenerationUtil {

    public static final String FIRST_NAME = "TESTING NAME";
    public static final String LAST_NAME = "TESTING LAST NAME";
    public static final String FOOTBALL_PLAYER_POSITION = "TESTING POSITION";
    public static final String FOOTBALL_PLAYER_NATIONAL = "TESTING NATIONAL";
    public static final int FOOTBALL_PLAYER_AGE = 21;

    public static FootballPlayer generateFootballPlayer() {
        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setFirstName(FIRST_NAME);
        footballPlayer.setLastName(LAST_NAME);
        footballPlayer.setFootballPlayerPosition(FOOTBALL_PLAYER_POSITION);
        footballPlayer.setFootballPlayerNational(FOOTBALL_PLAYER_NATIONAL);
        footballPlayer.setAgeOfFootballPlayer(FOOTBALL_PLAYER_AGE);
        return footballPlayer;
    }

    public static FootballPlayer generateFootballPlayer(String firstName, String lastName, String footballPlayerPosition, String footballPlayerNational, int footballPlayerAge) {
        FootballPlayer footballPlayer = new FootballPlayer();
        footballPlayer.setFirstName(firstName);
        footballPlayer.setLastName(lastName);
        footballPlayer.setFootballPlayerPosition(footballPlayerPosition);
        footballPlayer.setFootballPlayerNational(footballPlayerNational);
        footballPlayer.setAgeOfFootballPlayer(footballPlayerAge);
        return footballPlayer;
    }
}
