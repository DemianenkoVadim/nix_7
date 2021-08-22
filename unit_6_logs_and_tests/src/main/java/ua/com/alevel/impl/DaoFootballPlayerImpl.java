package ua.com.alevel.impl;

import lombok.SneakyThrows;
import ua.com.alevel.dao.DaoFootballPlayer;
import ua.com.alevel.db.BaseOfFootballsPlayer;
import ua.com.alevel.entity.FootballPlayer;

public class DaoFootballPlayerImpl implements DaoFootballPlayer {

    private final BaseOfFootballsPlayer registrationBaseFootballPlayers = BaseOfFootballsPlayer.getInstance();

    @SneakyThrows
    @Override
    public void createFootballPlayerInRegistrationBase(FootballPlayer player) {
        registrationBaseFootballPlayers.createFootballPlayerInRegistrationBase(player);
    }

    @SneakyThrows
    @Override
    public void updateFootballPlayerInRegistrationBase(FootballPlayer player) {
        registrationBaseFootballPlayers.updateFootballPlayerInRegistrationBase(player);
    }

    @SneakyThrows
    @Override
    public void deleteFootballPlayerFromRegistrationBase(String id) {
        registrationBaseFootballPlayers.deleteFootballPlayerFromRegistrationBase(id);
    }

    @SneakyThrows
    @Override
    public FootballPlayer findFootballPlayerFromRegistrationBaseById(String id) {
        return registrationBaseFootballPlayers.findFootballPlayerFromRegistrationBaseById(id);
    }

    @SneakyThrows
    @Override
    public void findAllFootballPlayersFromRegistrationBase() {
        registrationBaseFootballPlayers.findAllFootballPlayersFromRegistrationBase();
    }
}
