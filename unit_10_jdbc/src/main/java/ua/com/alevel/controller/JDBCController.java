package ua.com.alevel.controller;

import ua.com.alevel.dao.impl.DataBaseDaoImpl;
import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;
import ua.com.alevel.service.impl.DataBaseServiceImpl;
import ua.com.alevel.service.impl.PrintServiceImpl;

import java.sql.SQLException;
import java.util.List;

import static java.lang.System.out;
import static ua.com.alevel.util.ApplicationConstants.*;

public class JDBCController {

    PrintServiceImpl printService = new PrintServiceImpl();
    DataBaseServiceImpl dataBaseService = new DataBaseServiceImpl(new DataBaseDaoImpl());

    public void resultsInDataBase() {
        try {
            List<Location> stations = makesListOfStations();
            List<Route> roads = makesListOfRouts();
            List<Problem> issues = makesListOfProblems();
            List<Solution> assertion = makesListOfSolutions(issues);
            showsLocations(stations);
            showsRoutes(roads);
            showsUnsolvedProblems(issues);
            showsSolutions(assertion);
        } catch (SQLException dataBaseAccessError) {
            throw new RuntimeException(dataBaseAccessError);
        }
    }

    private List<Location> makesListOfStations() throws SQLException {
        return dataBaseService.readsAllLocations();
    }

    private List<Route> makesListOfRouts() throws SQLException {
        return dataBaseService.readsAllRouts();
    }

    private List<Problem> makesListOfProblems() throws SQLException {
        return dataBaseService.findsUnsolvedProblems();
    }

    private List<Solution> makesListOfSolutions(List<Problem> issues) {
        return printService.findsSolutionUnsolvedProblem(issues);
    }

    private void showsLocations(List<Location> stations) {
        out.println(SHOWS_LOCATIONS);
        for (Location locality : stations) {
            out.println(locality.getLocationId() + DASH + locality.getNameLocation());
        }
    }

    private void showsRoutes(List<Route> roads) {
        out.println(SHOWS_ROUTES);
        for (Route way : roads) {
            out.println(way.getId() + FROM_LOCATION + way.getFromIdLocation() + TO_LOCATION + way.getToIdLocation() + COST + way.getCost());
        }
    }

    private void showsUnsolvedProblems(List<Problem> issues) {
        out.println(SHOW_UNSOLVED_PROBLEMS);
        for (Problem problem : issues) {
            out.println(problem.getId() + DASH + problem.getFromIdLocation() + TO_LOCATION + problem.getToIdLocation());
        }
    }

    private void showsSolutions(List<Solution> assertion) {
        out.println(SHOW_SOLUTIONS);
        for (Solution solution : assertion) {
            out.println(solution.getProblemId() + PROBLEM + solution.getCost());
        }
    }
}
