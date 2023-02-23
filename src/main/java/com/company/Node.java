package com.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Node {

    private int data;
    private Node leftChild;
    private Node rightChild;

    //The property required for AVL BUT NOT FOR RBT:
    private int height;

    //The properties required for RBT:
    private Color color = Color.RED;
    private Node parent;

    public Node(int data) {
        this.data = data;
    }

    //The methods required for RBT:
    public boolean isLeftChild() {
        return this == parent.getLeftChild();
    }

    public void flipColor() {
        setColor(color == Color.RED ? Color.BLACK : Color.RED);
    }
}
