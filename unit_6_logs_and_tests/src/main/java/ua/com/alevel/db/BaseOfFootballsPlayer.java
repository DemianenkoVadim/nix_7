package ua.com.alevel.db;

import ua.com.alevel.customutil.DynamicArray;
import ua.com.alevel.entity.FootballPlayer;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;

import java.util.UUID;

import static java.lang.System.out;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class BaseOfFootballsPlayer {

    private static final BaseOfFootballsPlayer instance = new BaseOfFootballsPlayer();
    private final DynamicArray<FootballPlayer> players = new DynamicArray<>();
    private static final int ARRAY_INDEX = 0;

    private BaseOfFootballsPlayer() {
    }

    public static BaseOfFootballsPlayer getInstance() {
        return instance;
    }

    public void createFootballPlayerInRegistrationBase(FootballPlayer player) {
        player.setAvailable(true);
        player.setFootballPlayerId(generateId());
        players.addAdditionalPoint(player);
    }

    public void updateFootballPlayerInRegistrationBase(FootballPlayer player) throws NonExistentIDException {
        if (!players.isEmpty()) {
            for (int footballer = ARRAY_INDEX; footballer < players.getSize(); footballer++) {
                if (players.get(footballer).getFootballPlayerId().equals(player.getFootballPlayerId())) {
                    players.get(footballer).setFirstName(player.getFirstName());
                    players.get(footballer).setLastName(player.getLastName());
                    players.get(footballer).setFootballPlayerPosition(player.getFootballPlayerPosition());
                    players.get(footballer).setFootballPlayerNational(player.getFootballPlayerNational());
                    players.get(footballer).setAgeOfFootballPlayer(player.getAgeOfFootballPlayer());
                    players.get(footballer).setAvailable(true);
                }
            }
        } else {
            throw new NonExistentIDException();
        }
    }

    public void deleteFootballPlayerFromRegistrationBase(String id) throws NonExistentIDException {
        if (!players.isEmpty()) {
            for (int footballer = ARRAY_INDEX; footballer < players.getSize(); footballer++) {
                if (players.get(footballer).getFootballPlayerId().equals(id) && players.get(footballer).isAvailable())
                    players.get(footballer).setAvailable(false);
                out.println();
            }
        } else {
            throw new NonExistentIDException();
        }
    }

    public void findAllFootballPlayersFromRegistrationBase() throws EmptyRegistrationBaseException {
        if (!players.isEmpty()) {
            for (int footballer = ARRAY_INDEX; footballer < players.getSize(); footballer++) {
                if (players.get(footballer).isAvailable()) {
                    printsFootballPlayersFromRegistrationBase(footballer);
                }
            }
        } else {
            throw new EmptyRegistrationBaseException();
        }
    }

    private void printsFootballPlayersFromRegistrationBase(int footballer) {
        out.println(FOOTBALL_PLAYER_ID_PRINT + players.get(footballer).getFootballPlayerId() + "\n"
                + FOOTBALL_PLAYER_FIRST_NAME_PRINT + players.get(footballer).getFirstName() + "\n"
                + FOOTBALL_PLAYER_LAST_NAME_PRINT + players.get(footballer).getLastName() + "\n"
                + FOOTBALL_PLAYER_POSITION_PRINT + players.get(footballer).getFootballPlayerPosition() + "\n"
                + FOOTBALL_PLAYER_NATIONAL_PRINT + players.get(footballer).getFootballPlayerNational() + "\n"
                + FOOTBALL_PLAYER_AGE_PRINT + players.get(footballer).getAgeOfFootballPlayer());
        out.println();
    }

    public FootballPlayer findFootballPlayerFromRegistrationBaseById(String id) throws NonExistentIDException {
        for (int footballer = ARRAY_INDEX; footballer < players.getSize(); footballer++) {
            if (players.get(footballer).getFootballPlayerId().equals(id)) {
                return players.get(footballer);
            }
        }
        throw new NonExistentIDException();
    }

    private String generateId() {
        String idOfPlayer = UUID.randomUUID().toString();
        for (int onePlayer = ARRAY_INDEX; onePlayer < players.getSize(); onePlayer++) {
            for (int secondOnePlayer = ARRAY_INDEX; secondOnePlayer < players.getSize(); secondOnePlayer++) {
                if (onePlayer == secondOnePlayer) continue;
                if (players.get(secondOnePlayer).getFootballPlayerId().equals(players.get(onePlayer).getFootballPlayerId())) {
                    return generateId();
                }
            }
        }
        return idOfPlayer;
    }
}
