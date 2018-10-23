package dj.leetcode.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String     value;
    private List<Node> adjacentNodes;

    public Node(String value) {
        this.value = value;
        this.adjacentNodes = new ArrayList<>();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void addAdjacentNode(Node node) {
        this.adjacentNodes.add(node);
    }
}
