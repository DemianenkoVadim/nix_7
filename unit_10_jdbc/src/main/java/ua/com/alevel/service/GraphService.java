package ua.com.alevel.service;

import ua.com.alevel.entity.Location;
import ua.com.alevel.entity.Route;
import ua.com.alevel.entity.Solution;
import ua.com.alevel.exception.NoResultException;

import java.util.List;

public interface GraphService {

    void setsValueGraph(List<Location> stations, List<Route> roads) throws ClassNotFoundException, NoResultException;

    Solution findsTheShortestWay(Location from, Location to);
}
