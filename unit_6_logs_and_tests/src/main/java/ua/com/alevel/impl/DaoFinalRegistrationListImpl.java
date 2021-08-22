package ua.com.alevel.impl;

import lombok.SneakyThrows;
import ua.com.alevel.dao.DaoFinalRegistrationList;
import ua.com.alevel.db.BaseOfRegistrationList;
import ua.com.alevel.entity.FinalRegistrationList;

public class DaoFinalRegistrationListImpl implements DaoFinalRegistrationList {

    private final BaseOfRegistrationList registration = BaseOfRegistrationList.getInstance();

    @SneakyThrows
    @Override
    public void createFinalRegistrationList(FinalRegistrationList registrationList) {
        registration.createFinalRegistrationList(registrationList);
    }

    @SneakyThrows
    @Override
    public void updateFinalRegistrationList(FinalRegistrationList registrationList) {
        registration.updateFinalRegistrationList(registrationList);
    }

    @SneakyThrows
    @Override
    public void deletePositionInFinalRegistrationList(String id) {
        registration.deletePositionInFinalRegistrationList(id);
    }

    @SneakyThrows
    @Override
    public FinalRegistrationList findRegistrationById(String id) {
        return registration.findRegistrationById(id);
    }

    @SneakyThrows
    @Override
    public void readAllRegistrationList() {
        registration.readAndAllRegistrationList();
    }

    @SneakyThrows
    @Override
    public void showsAllFootballPlayersInChosenByIdFootballTeam(String id) {
        registration.showsAllFootballPlayersInChosenByIdFootballTeam(id);
    }

    @SneakyThrows
    @Override
    public void showsAllFootballTeamsInChosenByIdFootballPlayer(String id) {
        registration.showsAllFootballTeamsInChosenByIdFootballPlayer(id);
    }
}
