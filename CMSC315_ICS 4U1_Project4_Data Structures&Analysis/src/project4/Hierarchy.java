package project4;

import java.util.*;

/**
 * Project 4
 * @author Kelvin Njenga
 * Date: October 10, 2023
 * Description: The Hierarchy class is an implementation of the DFSActions interface.
 * It produces a hierarchical representation of the class dependencies, highlighting cycles in the graph.
 * The hierarchical structure indicates which classes depend on others and detects circular dependencies.
 */

public class Hierarchy<T> implements DFSActions<T> {
    private StringBuilder result;
    private Set<T> visited;
    private Map<T, Boolean> cycleCheck;
    private int indentLevel;

    public Hierarchy() {
        result = new StringBuilder();
        visited = new HashSet<>();
        cycleCheck = new HashMap<>();
        indentLevel = 0;
    }

    @Override
    public void onCycleDetected(T vertex) {
        result.append("\t".repeat(indentLevel)).append(vertex).append(" *").append("\n");
    }

    @Override
    public void onProcessVertex(T vertex) {
        if (!visited.contains(vertex)) {
            visited.add(vertex);
            if (cycleCheck.getOrDefault(vertex, false)) {
                result.append("\t".repeat(indentLevel)).append(vertex).append(" *").append("\n");
            } else {
                result.append("\t".repeat(indentLevel)).append(vertex).append("\n");
            }
        }
    }

    @Override
    public void onDescend(T vertex) {
        indentLevel++;
    }

    @Override
    public void onAscend(T vertex) {
        indentLevel--;
    }

    @Override
    public String toString() {
        return result.toString();
    }

	public void setDependencies(Map<String, List<String>> adjacencyList) {
		// TODO Auto-generated method stub
		
	}
}