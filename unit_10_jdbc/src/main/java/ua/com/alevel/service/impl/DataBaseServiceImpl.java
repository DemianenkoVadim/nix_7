package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.DataBaseDao;
import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;
import ua.com.alevel.exception.IDException;
import ua.com.alevel.service.DataBaseService;

import java.sql.SQLException;
import java.util.List;

import static ua.com.alevel.util.ApplicationConstants.*;

public class DataBaseServiceImpl implements DataBaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LOGGER_INFO);
    private final DataBaseDao dataBaseDao;

    public DataBaseServiceImpl(DataBaseDao dao) {
        dataBaseDao = dao;
    }

    @Override
    public List<Problem> findsUnsolvedProblems() throws SQLException {
        LOGGER.info(UNSOLVED_PROBLEMS);
        return dataBaseDao.getUnsolvedProblems();
    }

    @Override
    public Location findsLocationById(Integer id) throws SQLException, IDException {
        LOGGER.info(FIND_LOCATION_BY_ID);
        if (id <= ID_IS_ZERO) throw new IDException();
        return dataBaseDao.getsLocationById(id);
    }

    @Override
    public List<Route> readsAllRouts() throws SQLException {
        LOGGER.info(READ_ALL_ROUTS);
        return dataBaseDao.readsAllRouts();
    }

    @Override
    public List<Location> readsAllLocations() throws SQLException {
        LOGGER.info(READ_ALL_LOCATIONS);
        return dataBaseDao.readsAllLocations();
    }

    @Override
    public void createsSolution(List<Solution> solutions) throws SQLException {
        LOGGER.info(CREATE_SOLUTION);
        dataBaseDao.createsSolutions(solutions);
    }
}
