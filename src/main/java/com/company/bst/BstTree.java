package com.company.bst;

import com.company.MyTree;
import com.company.Node;

public class BstTree implements MyTree {
    private Node root;

    @Override
    public MyTree insert(int data) {
        root = insert(root, data);
        return this;
    }

    private Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }
        if (val < node.getData()) {
            node.setLeftChild(insert(node.getLeftChild(), val));
        } else if (val > node.getData()) {
            node.setRightChild(insert(node.getRightChild(), val));
        }
        return node;
    }

    @Override
    public void delete(int data) {
        delete(root, data);
    }

    private Node delete(Node node, int val) {
        if (node == null) {
            return null;
        }
        if (node.getData() == val) {
            Node left = node.getLeftChild();
            Node right = node.getRightChild();
            if (right == null) {
                return left;
            }
            node = right;
            while (node.getLeftChild() != null) {
                node = node.getLeftChild();
            }
            node.setLeftChild(left);
            return right;
        } else if (val < node.getData()) {
            node.setLeftChild(delete(node.getLeftChild(), val));
        } else {
            node.setRightChild(delete(node.getRightChild(), val));
        }
        return node;
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