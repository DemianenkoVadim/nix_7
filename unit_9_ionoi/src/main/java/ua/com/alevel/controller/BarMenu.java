package ua.com.alevel.controller;

import java.util.Arrays;

import static ua.com.alevel.util.Constant.*;
import static ua.com.alevel.util.CustomUtil.getInt;
import static ua.com.alevel.util.CustomUtil.printsRequestsOrInformationMessages;

public class BarMenu {

    public void printsBarMenu() {
        for (String barMenuValues : Arrays.asList(APPLICATION_FILMS_BASE, USER_CHOOSE_ZERO_POINT_ACTION,
                USER_CHOOSE_FIRST_POINT_ACTION, USER_CHOOSE_SECOND_POINT_ACTION, USER_CHOOSE_THIRD_POINT_ACTION,
                USER_CHOOSE_FOURS_POINT_ACTION, USER_CHOOSE_FIVES_POINT_ACTION, USER_CHOOSE_SIX_POINT_ACTION,
                USER_CHOOSE_SEVENTH_POINT_ACTION, USER_CHOOSE_EIGHTS_POINT_ACTION)) {
            printsRequestsOrInformationMessages(barMenuValues);
        }
    }

    public void chosenRightActionByInputUser() {
        Actions action = new Actions();
        while (true) {
            printsBarMenu();
            int userInput = getInt();
            switch (userInput) {
                case FIRST_USER_CH00SE:
                    action.addsNewFilm();
                    break;
                case SECOND_USER_CH00SE:
                    action.deleteFilm();
                    break;
                case THIRD_USER_CH00SE:
                    action.showsListOfAllFilms();
                    break;
                case FOURS_USER_CH00SE:
                    action.showsListOfAllActors();
                    break;
                case FIVES_USER_CH00SE:
                    action.changeFilmInformation();
                    break;
                case SIX_USER_CH00SE:
                    action.findFilm();
                    break;
                case SEVENS_USER_CH00SE:
                    action.findsFilmActors();
                    break;
                case EIGHTS_USER_CH00SE:
                    action.findFilmGenre();
                    break;
                case ZERO_POINT_USER_CHOOSE:
                    printsRequestsOrInformationMessages(MESSAGE_EXIT);
                    break;
                default:
                    printsRequestsOrInformationMessages(INPUT_RIGHT_CHOOSE);
                    break;
            }
            if (userInput == ZERO_POINT_USER_CHOOSE) break;
        }
    }
}
