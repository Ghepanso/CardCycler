package com.mathmech.cards;

import java.util.ArrayList;

public class Node {
    final String name;
    ArrayList<Node> incident = new ArrayList<>();
    ArrayList<Card> leaves;

    public Node(String nodeName) {
        this.name = nodeName;
    }

    public String toString() {
        return name;
    }

}
