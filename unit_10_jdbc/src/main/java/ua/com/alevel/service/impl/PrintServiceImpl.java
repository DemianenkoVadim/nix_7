package ua.com.alevel.service.impl;

import ua.com.alevel.dao.DataBaseDao;
import ua.com.alevel.dao.impl.DataBaseDaoImpl;
import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Problem;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;
import ua.com.alevel.exception.IDException;
import ua.com.alevel.exception.NoResultException;
import ua.com.alevel.service.PrintService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.util.ApplicationConstants.FIRST_PROBLEM;

public class PrintServiceImpl implements PrintService {

    @Override
    public List<Solution> findsSolutionUnsolvedProblem(List<Problem> issues) {

        try (DataBaseDao dataBaseDao = new DataBaseDaoImpl()) {
            DataBaseServiceImpl dataBaseService = new DataBaseServiceImpl(dataBaseDao);

            GraphServiceImpl graphService = new GraphServiceImpl();
            List<Location> stationsFrom = new ArrayList<>();
            List<Location> stationsTo = new ArrayList<>();

            for (Problem problem : issues) {
                stationsFrom.add(dataBaseService.findsLocationById(problem.getFromIdLocation()));
                stationsTo.add(dataBaseService.findsLocationById(problem.getToIdLocation()));
            }

            List<Location> stations = dataBaseService.readsAllLocations();
            List<Route> roads = dataBaseService.readsAllRouts();
            graphService.setsValueGraph(stations, roads);

            List<Solution> solutions = new ArrayList<>();

            for (int issueOfFact = FIRST_PROBLEM; issueOfFact < issues.size(); issueOfFact++) {
                solutions.add(graphService.findsTheShortestWay(stationsFrom.get(issueOfFact), stationsTo.get(issueOfFact)));
            }
            Solution solution;
            for (int issueOfFact = FIRST_PROBLEM; issueOfFact < issues.size(); issueOfFact++) {
                solution = solutions.get(issueOfFact);
                solution.setProblemId(issues.get(issueOfFact).getId());
            }
            dataBaseService.createsSolution(solutions);
            return solutions;
        } catch (SQLException accessDataBaseError) {
            throw new RuntimeException(accessDataBaseError);
        } catch (IDException IdIsNotFound) {
            throw new RuntimeException(IdIsNotFound);
        } catch (NoResultException resultError) {
            throw new RuntimeException(resultError);
        }
    }
}
