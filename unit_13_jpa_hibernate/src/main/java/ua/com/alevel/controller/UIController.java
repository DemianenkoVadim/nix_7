package ua.com.alevel.controller;

import lombok.extern.log4j.Log4j2;
import ua.com.alevel.service.LessonService;
import ua.com.alevel.service.StudentService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.System.*;
import static ua.com.alevel.util.ConstantsApplicationUnit_13.*;
import static ua.com.alevel.util.MessagesAndRequest.*;

@Log4j2
public class UIController {

    StudentService studentService = new StudentService();
    LessonService lessonService = new LessonService();

    public void startsTheProgram() throws Exception {
        BufferedReader readerFromConsole = new BufferedReader(new InputStreamReader(in));
        String userChoice;
        try {
            out.println();
            printsApplicationHeader();
            printsRequestToChooseOption();
            printsRequestToFindOutFullListOfStudents();
            printsRequestToChooseActionToFindNearestLesson();
            printsRequestToExitTheApplication();
            out.println();
            while ((userChoice = readerFromConsole.readLine()) != null) {
                runNecessaryOperations(userChoice, readerFromConsole);
                userChoice = readerFromConsole.readLine();
                if (userChoice.equals(ZERO_POSITION)) {
                    exit(CORRECT_EXIT_PROGRAM);
                }
                runNecessaryOperations(userChoice, readerFromConsole);
            }
        } catch (IOException wrongUserInput) {
            log.error("Your input data is incorrect");
            out.println();
        }
    }

    private void runNecessaryOperations(String userChoicePosition, BufferedReader readerFromConsole) throws Exception {
        switch (userChoicePosition) {
            case USER_CHOOSE_FIRST_POINT:
                studentService.getAllStudents();
                break;
            case USER_CHOOSE_SECOND_POINT:
                getNearestStudentsLesson(readerFromConsole);
                break;
            case USER_CHOOSE_ZERO_POINT:
                exit(CORRECT_EXIT_PROGRAM);
                break;
        }
    }

    private void getNearestStudentsLesson(BufferedReader readFromConsole) {
        out.println();
        try {
            out.println(lessonService.getNearestStudentsLessonByStudentId(requestStudentId(readFromConsole)).toString());
            startsTheProgram();
        } catch (Exception e) {
            log.error("Your input data is incorrect");
        }
    }

    public Integer requestStudentId(BufferedReader readerFromConsole) throws IOException {
        out.println("Input student id");
        return Integer.valueOf(readerFromConsole.readLine());
    }
}
