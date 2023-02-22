package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Node {

    private int data;
    private int height;
    private Node leftChild;
    private Node rightChild;

    public Node(int data) {
        this.data = data;
    }
}
