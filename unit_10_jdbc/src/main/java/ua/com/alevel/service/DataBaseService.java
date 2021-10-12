package ua.com.alevel.service;

import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;
import ua.com.alevel.exception.IDException;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseService {

    List<Problem> findsUnsolvedProblems() throws SQLException;

    Location findsLocationById(Integer id) throws SQLException, IDException;

    List<Route> readsAllRouts() throws SQLException;

    List<Location> readsAllLocations() throws SQLException;

    void createsSolution(List<Solution> solutions) throws SQLException;
}
