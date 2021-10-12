package ua.com.alevel.service.impl;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;
import ua.com.alevel.exception.NoResultException;
import ua.com.alevel.service.GraphService;

import java.util.List;

import static ua.com.alevel.util.ApplicationConstants.*;

public class GraphServiceImpl implements GraphService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LOGGER_INFO);
    private SimpleWeightedGraph<String, DefaultWeightedEdge> graph;

    @Override
    public void setsValueGraph(List<Location> stations, List<Route> roads) throws NoResultException {
        LOGGER.info(INITIALIZE_GRAPH);
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
        for (Location locality : stations) {
            graph.addVertex(locality.getNameLocation());
        }
        DefaultWeightedEdge edge;
        for (Route way : roads) {
            Location from = findsLocationById(stations, way.getFromIdLocation());
            Location to = findsLocationById(stations, way.getToIdLocation());
            edge = graph.addEdge(from.getNameLocation(), to.getNameLocation());
            graph.setEdgeWeight(edge, way.getCost());
        }
    }

    @Override
    public Solution findsTheShortestWay(Location from, Location to) {
        LOGGER.info(FIND_THE_SHORTEST_WAY);
        int maximumWayCost = MOST_EXPENSIVE_WAY;
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstraShortestPath = new DijkstraShortestPath<>(graph);
        double cost = dijkstraShortestPath.getPath(from.getNameLocation(), to.getNameLocation()).getWeight();
        Solution solution = new Solution();
        if (cost <= maximumWayCost)
            solution.setCost((int) cost);
        else {
            LOGGER.info(MORE_THEN_MOST_EXPENSIVE_WAY_COST);
            solution.setCost(maximumWayCost);
        }
        return solution;
    }

    private Location findsLocationById(List<Location> stations, Integer id) throws NoResultException {
        LOGGER.info(FIND_LOCATION_BY_ID);
        for (Location locality : stations) {
            if (locality.getLocationId().equals(id)) return locality;
        }
        throw new NoResultException(NOT_FOUND_LOCATION);
    }
}
