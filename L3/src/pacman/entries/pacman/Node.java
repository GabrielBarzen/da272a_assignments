package pacman.entries.pacman;

import java.util.HashMap;
import java.util.Map;

public class Node {
    String attribute;
    Map<String, ID3Man.Node> children;
    String label;

    public Node(String attribute) {
        this.attribute = attribute;
        children = new HashMap<>();
    }

    public Node(String label, String attribute) {
        this.label = label;
        this.attribute = attribute;
        children = new HashMap<>();
    }
}