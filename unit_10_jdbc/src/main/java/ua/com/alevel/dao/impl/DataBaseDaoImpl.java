package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.DataBaseDao;
import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static ua.com.alevel.util.ApplicationConstants.*;

public class DataBaseDaoImpl implements DataBaseDao {

    private final Connection connection;

    public DataBaseDaoImpl() {
        Properties declaration = loadProperties();
        String URL = declaration.getProperty(URL_DATA_BASE_CONNECTION);
        String USER = declaration.getProperty(DATA_BASE_USER);
        String PASSWORD = declaration.getProperty(DATA_BASE_PASSWORD);
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException dataBaseAccessesError) {
            throw new RuntimeException(dataBaseAccessesError);
        }
    }

    private static Properties loadProperties() {
        Properties characteristicDataBaseAccess = new Properties();
        try (InputStream inputFilePropertyConnection = DataBaseDao.class.getResourceAsStream(FILE_INPUT)) {
            characteristicDataBaseAccess.load(inputFilePropertyConnection);
        } catch (IOException failedOInputOutputOperations) {
            throw new UncheckedIOException(failedOInputOutputOperations);
        }
        return characteristicDataBaseAccess;
    }

    public List<Problem> getUnsolvedProblems() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM problems a LEFT JOIN solutions b ON a.id = b.problem_id WHERE b.problem_id IS NULL")) {
            List<Problem> issues = new ArrayList<>();
            Problem issue;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                issue = new Problem();
                issue.setId(resultSet.getInt(ID_COLUMN_LABEL));
                issue.setFromIdLocation(resultSet.getInt(FROM_ID_COLUMN_LABEL));
                issue.setToIdLocation(resultSet.getInt(TO_ID_COLUMN_LABEL));
                issues.add(issue);
            }
            return issues;
        } catch (SQLException dataBaseAccessesError) {
            throw new RuntimeException(dataBaseAccessesError);
        }
    }

    public Location getsLocationById(Integer idLocation) {
        try (PreparedStatement assertion = connection.prepareStatement("SELECT * FROM locations WHERE id LIKE ?")) {
            Location station = new Location();
            assertion.setInt(FIRST_PARAMETER_INDEX, idLocation);
            ResultSet resultSet = assertion.executeQuery();
            while (resultSet.next()) {
                station.setIdLocation(resultSet.getInt(ID_COLUMN_LABEL));
                station.setNameLocation(resultSet.getString(NAME_COLUMN_LABEL));
            }
            return station;
        } catch (SQLException dataBaseAccessesError) {
            throw new RuntimeException(dataBaseAccessesError);
        }
    }

    public List<Location> readsAllLocations() {
        try (Statement assertion = connection.createStatement()) {
            List<Location> stations = new ArrayList<>();
            Location locality;
            ResultSet resultSet = assertion.executeQuery("select * from locations");
            while (resultSet.next()) {
                locality = new Location();
                locality.setIdLocation(resultSet.getInt(ID_COLUMN_LABEL));
                locality.setNameLocation(resultSet.getString(NAME_COLUMN_LABEL));
                stations.add(locality);
            }
            return stations;
        } catch (SQLException dataBaseAccessesError) {
            throw new RuntimeException(dataBaseAccessesError);
        }
    }

    public List<Route> readsAllRouts() {
        try (Statement assertion = connection.createStatement()) {
            List<Route> waybill = new ArrayList<>();
            Route road;
            ResultSet resultSet = assertion.executeQuery("select * from routes");
            while (resultSet.next()) {
                road = new Route();
                road.setId(resultSet.getInt(ID_COLUMN_LABEL));
                road.setFromIdLocation(resultSet.getInt(FROM_ID_COLUMN_LABEL));
                road.setToIdLocation(resultSet.getInt(TO_ID_COLUMN_LABEL));
                road.setCost(resultSet.getInt(COST_COLUMN_LABEL));
                waybill.add(road);
            }
            return waybill;
        } catch (SQLException dataBaseAccessesError) {
            throw new RuntimeException(dataBaseAccessesError);
        }
    }

    public void createsSolutions(List<Solution> decisions) {
        try (PreparedStatement assertion = connection.prepareStatement("insert into  solutions (problem_id, cost) values(?,?)")) {
            connection.setAutoCommit(false);
            for (Solution resolve : decisions) {
                assertion.setInt(FIRST_PARAMETER_INDEX, resolve.getProblemId());
                assertion.setInt(SECOND_PARAMETER_INDEX, resolve.getCost());
                assertion.addBatch();
            }
            assertion.executeBatch();
            connection.commit();
        } catch (SQLException dataBaseAccessesError) {
            throw new RuntimeException(dataBaseAccessesError);
        }
    }

    public void close() throws SQLException {
        connection.close();
    }
}
