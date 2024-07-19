package project4;

import javax.swing.*;
import java.io.*;
import java.util.List;

/**
 * Project 4
 * @author Kelvin Njenga
 * Date: October 10, 2023
 * The Main class serves as the entry point for the Java program.
 * It provides the user interface for selecting an input file that contains information about class dependencies in a Java program.
 * The class reads the input, constructs a DirectedGraph to represent the dependencies and produce hierarchical and parenthesized list representations of the class relationships.
 */

public class Main {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                FileReader fileReader = new FileReader(selectedFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                DirectedGraph<String> graph = new DirectedGraph<>();

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] classes = line.split(" ");
                    String source = classes[0];
                    for (int i = 1; i < classes.length; i++) {
                        String destination = classes[i];
                        graph.addEdge(source, destination);
                    }
                }

                bufferedReader.close();

                List<String> vertices = graph.getVertices();
                if (!vertices.isEmpty()) {
                	Hierarchy<String> hierarchy = new Hierarchy<>();
                	hierarchy.setDependencies(graph.getAdjacencyList());
                	graph.depthFirstSearch(vertices.get(0), hierarchy);
                	System.out.println("Hierarchy representation:");
                	System.out.println(hierarchy.toString());

                	
                    ParenthesizedList<String> parenthesizedList = new ParenthesizedList<>();
                    graph.depthFirstSearch(vertices.get(0), parenthesizedList);
                    System.out.println("Parenthesized list representation:");
                    System.out.println(parenthesizedList.toString());

                    List<String> unreachableClasses = graph.findUnreachableClasses();
                    if (!unreachableClasses.isEmpty()) {
                        System.out.println("Unreachable classes:");
                        for (String className : unreachableClasses) {
                            System.out.println(className + " is unreachable");
                        }
                    } else {
                        System.out.println("No unreachable classes found.");
                    }
                } else {
                    System.out.println("No vertices found in the graph.");
                }

            } catch (FileNotFoundException e) {
                System.err.println("Error: The selected file was not found.");
            } catch (IOException e) {
                System.err.println("Error: An IO error occurred while reading the file.");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("Error: An unexpected error occurred.");
                e.printStackTrace();
            }
        } else {
            System.out.println("No file selected.");
        }
    }
}
