package project4;

/**
 * Project 4
 * @author Kelvin Njenga
 * Date: October 10, 2023
 * Description: This is a generic interface that specifies the actions to be performed during depth-first searches in the DirectedGraph.
 * It includes methods for handling cycle detection, processing vertices, descending into vertices, and ascending back from vertices.
 */

public interface DFSActions<T> {
    void onCycleDetected(T vertex);
    void onProcessVertex(T vertex);
    void onDescend(T vertex);
    void onAscend(T vertex);
}
