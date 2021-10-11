package ua.com.alevel.entity;

import ua.com.alevel.annotations.MapperCsvFile;

import java.time.LocalDateTime;

import static ua.com.alevel.util.ApplicationConstants.*;

public class PlayerOfFootballTeam {

    @MapperCsvFile(value = FOOTBALL_PLAYER_ID)
    private Integer footballPlayerId;

    @MapperCsvFile(value = FOOTBALL_PLAYER_FIRST_NAME)
    private String footballPlayerFirstName;

    @MapperCsvFile(value = FOOTBALL_PLAYER_LAST_NAME)
    private String footballPlayerLastName;

    @MapperCsvFile(value = FOOTBALL_PLAYER_BIRTHDAY)
    private LocalDateTime birthday;

    @MapperCsvFile(value = FOOTBALL_PLAYER_GROSS_UTILITY_COEFFICIENT)
    private Double grossUtilityCoefficient;

    @MapperCsvFile(value = FOOTBALL_PLAYER_IS_AVAILABLE)
    private Boolean isAvailable;

    public PlayerOfFootballTeam() {
    }

    @Override
    public String toString() {
        return "Player Of Football Team " +
                " Id Football team Player - " + footballPlayerId +
                ", Football Player first name - '" + footballPlayerFirstName + '\'' +
                ", Football Player last name - '" + footballPlayerLastName + '\'' +
                ", Football Player birthday - " + birthday +
                ", Football Player gross utility coefficient - " + grossUtilityCoefficient +
                ", Football Player is available - " + isAvailable;
    }
 }
