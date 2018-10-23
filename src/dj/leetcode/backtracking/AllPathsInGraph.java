package dj.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import dj.leetcode.graph.Graph;
import dj.leetcode.graph.Node;

public class AllPathsInGraph {
    Graph      graph;
    List<Node> currentPath;

    AllPathsInGraph(Graph graph) {
        this.graph = graph;
        this.currentPath = new ArrayList<>();
    }

    Set<Node> visited = new HashSet<>();

    public void allPaths(Node start, Node end) {
        visitNode(start);

        if (start.equals(end) && !currentPath.isEmpty()) {
            List<Node> path = new ArrayList<>(currentPath);
            path.add(end);
            printPath(path);
        }
        else {
            currentPath.add(start);
            for (Node node : start.getAdjacentNodes()) {
                if (!isVisited(node)) {
                    allPaths(node, end);
                }
            }
        }
        //backtrack
        currentPath.remove(start);
        resetVisitNode(start);

    }

    private void printPath(List<Node> path) {
        String pathString = path.stream()
                                .map(Node::getValue)
                                .collect(Collectors.joining(" -> "));
        System.out.println(pathString);
    }

    private void visitNode(Node node) {
        this.visited.add(node);
    }

    private void resetVisitNode(Node node) {
        this.visited.remove(node);
    }

    private boolean isVisited(Node node) {
        return this.visited.contains(node);
    }

    public static void main(String[] args) {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        a.addAdjacentNode(b);
        a.addAdjacentNode(c);
        b.addAdjacentNode(d);
        d.addAdjacentNode(e);
        c.addAdjacentNode(e);

        Graph graph = new Graph();
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
        graph.addNode(e);


        AllPathsInGraph allPathsInGraph = new AllPathsInGraph(graph);
        for (Node node : graph.getNodes()) {
            allPathsInGraph.allPaths(node, e);
        }

    }
}
