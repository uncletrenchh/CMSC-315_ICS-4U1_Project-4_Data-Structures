package project4;

import java.util.*;

/**
 * Project 4
 * @author Kelvin Njenga
 * Date: October 10, 2023
 * Description: This class represents a directed graph data structure.
 * It stores information about class dependencies in a Java program and provides methods to add edges between classes, perform depth-first searches, and find unreachable classes.
 * The generic parameter 'T' specifies the type of labels associated with the graph vertices.
 */

public class DirectedGraph<T> {
    private Map<T, List<T>> adjacencyList;
    private List<T> vertices;
    private Set<T> discoveredVertices;
    private Set<T> finishedVertices;

    public DirectedGraph() {
        adjacencyList = new HashMap<>();
        vertices = new ArrayList<>();
        discoveredVertices = new HashSet<>();
        finishedVertices = new HashSet<>();
    }

    public void addEdge(T source, T destination) {
        adjacencyList.computeIfAbsent(source, k -> new ArrayList<>()).add(destination);
        if (!vertices.contains(source)) {
            vertices.add(source);
        }
        if (!vertices.contains(destination)) {
            vertices.add(destination);
        }
    }

    public void depthFirstSearch(T startVertex, DFSActions<T> actions) {
        discoveredVertices.clear();
        finishedVertices.clear();
        dfsRecursive(startVertex, actions);
    }

    private void dfsRecursive(T vertex, DFSActions<T> actions) {
        if (discoveredVertices.contains(vertex)) {
            actions.onCycleDetected(vertex);
            return;
        }

        actions.onProcessVertex(vertex);
        discoveredVertices.add(vertex);
        actions.onDescend(vertex);

        List<T> adjacentVertices = adjacencyList.getOrDefault(vertex, new ArrayList<>());
        for (T adjacentVertex : adjacentVertices) {
            if (!finishedVertices.contains(adjacentVertex)) {
                dfsRecursive(adjacentVertex, actions);
            }
        }

        actions.onAscend(vertex);
        finishedVertices.add(vertex);
    }

    public List<T> findUnreachableClasses() {
        List<T> unreachableClasses = new ArrayList<>();
        for (T vertex : vertices) {
            if (!discoveredVertices.contains(vertex) && !finishedVertices.contains(vertex)) {
                unreachableClasses.add(vertex);
            }
        }
        return unreachableClasses;
    }

    public List<T> getVertices() {
        return vertices;
    }

    public Map<T, List<T>> getAdjacencyList() {
        return adjacencyList;
    }
}
