package com.company.redBlack;

import com.company.Color;
import com.company.MyTree;
import com.company.Node;

public class RedBlackTree implements MyTree {

    private Node root;

    @Override
    public MyTree insert(int data) {
        Node node = new Node(data);
        root = insert(root, node);
        recolorAndRotate(node);
        return this;
    }

    private Node insert(Node node, Node toBeInserted) {
        if (node == null) {
            return toBeInserted;
        }
        if (toBeInserted.getData() < node.getData()) {
            node.setLeftChild(insert(node.getLeftChild(), toBeInserted));
            node.getLeftChild().setParent(node);
        } else if (toBeInserted.getData() > node.getData()) {
            node.setRightChild(insert(node.getRightChild(), toBeInserted));
            node.getRightChild().setParent(node);
        }
        return node;
    }

    private void recolorAndRotate(Node node) {
        Node parent = node.getParent();
        if (parent != root && parent.getColor() == Color.RED) {
            Node grandParent = parent.getParent();
            Node uncle = parent.isLeftChild() ? grandParent.getRightChild() : grandParent.getLeftChild();
            if (uncle != null && uncle.getColor() == Color.RED) {
                //recoloring
                handleRecoloring(parent, uncle, grandParent);
            } else if (parent.isLeftChild()) {
                //ll or lr
                handleLeftSituation(grandParent, parent, node);
            } else if (!parent.isLeftChild()) {
                //rr or rl
                handleRightSituation(grandParent, parent, node);
            }
        }
        root.setColor(Color.BLACK);
    }

    private void handleRecoloring(Node parent, Node uncle, Node grandParent) {
        uncle.flipColor();
        parent.flipColor();
        grandParent.flipColor();
        recolorAndRotate(grandParent);
    }

    private void handleLeftSituation(Node grandParent, Node parent, Node node) {
        if (!node.isLeftChild()) {
            rotateLeft(parent);
        }
        parent.flipColor();
        grandParent.flipColor();
        rotateRight(grandParent);
        recolorAndRotate(node.isLeftChild() ? parent : grandParent);
    }

    private void handleRightSituation(Node grandParent, Node parent, Node node) {
        if (node.isLeftChild()) {
            rotateRight(parent);
        }
        parent.flipColor();
        grandParent.flipColor();
        rotateLeft(grandParent);
        recolorAndRotate(!node.isLeftChild() ? parent : grandParent);
    }

    private void rotateRight(Node node) {
        Node leftNode = node.getLeftChild();
        node.setLeftChild(leftNode.getRightChild());
        if (node.getLeftChild() != null) {
            node.getLeftChild().setParent(node);
        }
        leftNode.setRightChild(node);
        leftNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, leftNode);
        node.setParent(leftNode);
    }

    private void updateChildrenOfParentNode(Node node, Node tempNode) {
        if (node.getParent() == null) {
            root = tempNode;
        } else if (node.isLeftChild()) {
            node.getParent().setLeftChild(tempNode);
        } else {
            node.getParent().setRightChild(tempNode);
        }

    }

    private void rotateLeft(Node node) {
        Node rightNode = node.getRightChild();
        node.setRightChild(rightNode.getLeftChild());
        if (node.getRightChild() != null) {
            node.getRightChild().setParent(node);
        }
        rightNode.setLeftChild(node);
        rightNode.setParent(node.getParent());
        updateChildrenOfParentNode(node, rightNode);
        node.setParent(rightNode);
    }


    @Override
    public void delete(int data) {

    }

    @Override
    public void traverse() {
        traverse(root);
    }

    private void traverse(Node node) {
        if (node != null) {
            traverse(node.getLeftChild());
            System.out.println(node.getData());
            traverse(node.getRightChild());
        }
    }

    @Override
    public Integer getMax() {
        if (isEmpty()) {
            return null;
        }
        Node current = root;
        while (current.getRightChild() != null) {
            current = current.getRightChild();
        }
        return current.getData();
    }

    @Override
    public Integer getMin() {
        if (isEmpty()) {
            return null;
        }
        Node current = root;
        while (current.getLeftChild() != null) {
            current = current.getLeftChild();
        }
        return current.getData();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}