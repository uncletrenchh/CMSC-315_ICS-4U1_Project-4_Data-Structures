package project4;

/**
 * Project 4
 * @author Kelvin Njenga
 * Date: October 10, 2023
 * ParenthesizedList is another implementation of the DFSActions interface. 
 * It generates an alternate representation of class dependencies using parentheses to indicate the hierarchy.
 * It also marks circular dependencies with an asterisk.
 */

public class ParenthesizedList<T> implements DFSActions<T> {
    private StringBuilder result;
    private boolean isFirstVertex;

    public ParenthesizedList() {
        result = new StringBuilder();
        isFirstVertex = true;
    }

    @Override
    public void onCycleDetected(T vertex) {
        result.append(" *");
    }

    @Override
    public void onProcessVertex(T vertex) {
        if (!isFirstVertex) {
            result.append(")");
            result.append(" ");
        }
        result.append("(").append(vertex);
        isFirstVertex = false;
    }

    @Override
    public void onDescend(T vertex) {
        result.append(" ");
    }

    @Override
    public void onAscend(T vertex) {
        // No need to add anything here
    }

    @Override
    public String toString() {
        if (!isFirstVertex) {
            result.append(")");
        }
        return result.toString();
    }
}
