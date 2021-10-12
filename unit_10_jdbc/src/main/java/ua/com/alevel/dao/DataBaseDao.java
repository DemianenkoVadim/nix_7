package ua.com.alevel.dao;

import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;

import java.sql.SQLException;
import java.util.List;

public  interface DataBaseDao extends AutoCloseable {

    List<Problem> getUnsolvedProblems() throws SQLException;

    Location getsLocationById(Integer id) throws SQLException;

    List<Location> readsAllLocations() throws SQLException;

    List<Route> readsAllRouts() throws SQLException;

    void createsSolutions(List<Solution> solutions) throws SQLException;

    @Override
    void close() throws SQLException;
}
