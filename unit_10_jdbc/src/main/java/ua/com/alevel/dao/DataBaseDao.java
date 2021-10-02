package ua.com.alevel.dao;

import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;

import java.sql.SQLException;
import java.util.List;

public interface DataBaseDao extends AutoCloseable {

    List<Problem> getUnsolvedProblems() throws SQLException;

    Location getLocationById(Integer id) throws SQLException;

    List<Location> readAllLocations() throws SQLException;

    List<Route> readAllRoutes() throws SQLException;

    void createSolutions(List<Solution> solutions) throws SQLException;

    @Override
    void close() throws SQLException;
}
