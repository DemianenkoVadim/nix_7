package ua.com.alevel.profitableway;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static ua.com.alevel.util.Constants.*;

public class CheapestWayFinder {

    private static final String output = "module_2/src/main/resources/files/output.txt";

    public static void findsWaysGraph(ArrayList<String> wayGraph) {
        SimpleWeightedGraph<String, DefaultWeightedEdge> weightedGraph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        ArrayList<String> directions = new ArrayList<>();
        ArrayList<String[]> allRegions = new ArrayList<>();

        String[] directionsOfCity;
        int direction = parsesInformationDirection(wayGraph);
        ArrayList<Integer> edgesOfEveryCity = new ArrayList<>();

        int marginOfCity;
        int lastDirection = SAME_DATA;
        for (int way = FIRST_DATA; way < wayGraph.size(); way++) {
            directions.add(wayGraph.get(way));
            marginOfCity = (Integer.parseInt((wayGraph.get(way + FIRST_DATA))));
            edgesOfEveryCity.add(marginOfCity);
            way += NEXT_WAY;
            directionsOfCity = new String[marginOfCity];
            for (int routes = FIRST_DATA_IN_ARRAY; routes < directionsOfCity.length; way++) {
                directionsOfCity[routes] = wayGraph.get(way);
                routes++;
            }
            allRegions.add(directionsOfCity);
            direction--;
            way--;
            if (direction == SAME_DATA) {
                lastDirection = way + ONE_INDEX;
                break;
            }
        }
        direction = parsesInformationDirection(wayGraph);
        for (int directionValue = FIRST_DATA_IN_ARRAY; directionValue < direction; directionValue++) {
            weightedGraph.addVertex(directions.get(directionValue));
        }

        String peak;
        double value;
        DefaultWeightedEdge defaultWeightedEdge;

        for (int intervals = FIRST_DATA_IN_ARRAY; intervals < direction; intervals++) {
            for (int routes = FIRST_DATA_IN_ARRAY; routes < edgesOfEveryCity.get(intervals); routes++) {
                String[] edgeString = allRegions.get(intervals)[routes].split(SPACE);
                peak = directions.get(Integer.parseInt(edgeString[FIRST_DATA_IN_ARRAY]) - ONE_INDEX);
                value = Double.parseDouble(edgeString[ONE_INDEX]);
                if (!weightedGraph.containsEdge(directions.get(intervals), peak)) {
                    defaultWeightedEdge = weightedGraph.addEdge(directions.get(intervals), peak);
                    weightedGraph.setEdgeWeight(defaultWeightedEdge, value);
                }
            }
        }
        findsDistance(weightedGraph, wayGraph.subList(lastDirection, wayGraph.size()));
    }

    private static int parsesInformationDirection(ArrayList<String> stringGraph) {
        return Integer.parseInt(stringGraph.get(FIRST_LIST_DATA));
    }

    private static void findsDistance(Graph<String, DefaultWeightedEdge> graph, List<String> pathFound) {
        int countingTheNumberOfPathFound = parsesWay(pathFound);
        DijkstraShortestPath<String, DefaultWeightedEdge> theShortestDistance = new DijkstraShortestPath<>(graph);
        double values;
        String[] source;
        StringBuilder outputInformation = new StringBuilder();
        for (int path = SECOND_DATA_IN_ARRAY; path < countingTheNumberOfPathFound + ONE_INDEX; path++) {
            source = pathFound.get(path).split(SPACE);
            values = theShortestDistance.getPath(source[FIRST_DATA_IN_ARRAY], source[SECOND_DATA_IN_ARRAY]).getWeight();
            System.out.println(values);
            outputInformation.append(values).append(INDENTION);
        }
        writeFilePath(outputInformation);
    }

    private static int parsesWay(List<String> distanceFound) {
        return Integer.parseInt(distanceFound.get(FIRST_LIST_DATA));
    }

    private static void writeFilePath(StringBuilder stringBuilder) {
        try {
            Path filePath = Paths.get(CheapestWayFinder.output);
            Files.writeString(filePath, stringBuilder);
        } catch (IOException wrongInputOutput) {
            wrongInputOutput.printStackTrace();
        }
    }
}
