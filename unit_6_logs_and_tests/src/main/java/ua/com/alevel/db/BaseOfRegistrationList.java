package ua.com.alevel.db;

import ua.com.alevel.customutil.DynamicArray;
import ua.com.alevel.entity.FinalRegistrationList;
import ua.com.alevel.entity.FootballPlayer;
import ua.com.alevel.exception.EmptyRegistrationBaseException;
import ua.com.alevel.exception.NonExistentIDException;

import java.util.UUID;

import static java.lang.System.out;
import static ua.com.alevel.customutil.ConstantOfRequestAndPrints.*;

public class BaseOfRegistrationList extends FootballPlayer {

    private static final BaseOfRegistrationList instance = new BaseOfRegistrationList();
    private final DynamicArray<FinalRegistrationList> registrationList = new DynamicArray<>();


    private BaseOfRegistrationList() {
    }

    public static BaseOfRegistrationList getInstance() {
        return instance;
    }

    public void createFinalRegistrationList(FinalRegistrationList registration) {
        registration.setAvailable(true);
        registration.setFinalRegistrationListID(generateId());
        registrationList.addAdditionalPoint(registration);
    }

    public void updateFinalRegistrationList(FinalRegistrationList finalRegistration) throws EmptyRegistrationBaseException {
        if (!registrationList.isEmpty()) {
            for (int registration = ARRAY_INDEX; registration < registrationList.getSize(); registration++) {
                if (registrationList.get(registration).getFinalRegistrationListID().equals(finalRegistration.getFinalRegistrationListID())) {
                    registrationList.get(registration).setFootballTeamID(finalRegistration.getFootballTeamID());
                    registrationList.get(registration).setFootballPlayerID(finalRegistration.getFootballPlayerID());
                    registrationList.get(registration).setAvailable(true);
                }
            }
        } else {
            throw new EmptyRegistrationBaseException();
        }
    }

    public void deletePositionInFinalRegistrationList(String id) throws NonExistentIDException {
        if (!registrationList.isEmpty()) {
            for (int registration = ARRAY_INDEX; registration < registrationList.getSize(); registration++) {
                if (registrationList.get(registration).getFinalRegistrationListID().equals(id) && registrationList.get(registration).isAvailable())
                    registrationList.get(registration).setAvailable(false);
                out.println();
            }
        } else {
            throw new NonExistentIDException();
        }
    }


    public FinalRegistrationList findRegistrationById(String id) throws NonExistentIDException {
        for (int registration = ARRAY_INDEX; registration < registrationList.getSize(); registration++) {
            if (registrationList.get(registration).getFinalRegistrationListID().equals(id))
                return registrationList.get(registration);
            out.println();
        }
        throw new NonExistentIDException();
    }

    public void readAndAllRegistrationList() throws EmptyRegistrationBaseException {
        if (!registrationList.isEmpty()) {
            for (int registration = ARRAY_INDEX; registration < registrationList.getSize(); registration++) {
                if (registrationList.get(registration).isAvailable())
                    printsAllRegistrationList(registration);
            }
        } else {
            throw new EmptyRegistrationBaseException();
        }
    }

    private void printsAllRegistrationList(int registration) {
        out.println(REGISTRATION_ID + registrationList.get(registration).getFinalRegistrationListID() + "\n" +
                FOOTBALL_PLAYER_ID_PRINT + registrationList.get(registration).getFootballPlayerID() + "\n" +
                FOOTBALL_TEAM_ID_PRINT + registrationList.get(registration).getFootballTeamID());
        out.println();
    }


    public void showsAllFootballPlayersInChosenByIdFootballTeam(String id) throws NonExistentIDException {
        if (!registrationList.isEmpty()) {
            for (int registration = ARRAY_INDEX; registration < registrationList.getSize(); registration++) {
                if (registrationList.get(registration).getFootballTeamID().equals(id)) {
                    out.println(FOOTBALL_PLAYER_ID_PRINT + registrationList.get(registration).getFootballPlayerID());
                    out.println();
                }
            }
        } else {
            throw new NonExistentIDException();
        }
    }

    public void showsAllFootballTeamsInChosenByIdFootballPlayer(String id) throws NonExistentIDException {
        if (!registrationList.isEmpty()) {
            for (int registration = ARRAY_INDEX; registration < registrationList.getSize(); registration++) {
                if (registrationList.get(registration).getFootballPlayerID().equals(id)) {
                    out.println(FOOTBALL_TEAM_ID_PRINT + registrationList.get(registration).getFootballTeamID());
                    out.println();
                }
            }
        } else {
            throw new NonExistentIDException();
        }
    }

    private String generateId() {
        String baseRegistrationID = UUID.randomUUID().toString();
        for (int onePointInList = ARRAY_INDEX; onePointInList < registrationList.getSize(); onePointInList++) {
            for (int secondOnePointInList = ARRAY_INDEX; secondOnePointInList < registrationList.getSize(); secondOnePointInList++) {
                if (onePointInList == secondOnePointInList) continue;
                if (registrationList.get(secondOnePointInList).getFinalRegistrationListID().equals(registrationList.get(onePointInList).getFinalRegistrationListID())) {
                    return generateId();
                }
            }
        }
        return baseRegistrationID;
    }
}
