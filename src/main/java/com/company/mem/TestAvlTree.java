package com.company.mem;

import com.company.MyTree;
import com.company.Node;

public class TestAvlTree implements MyTree {

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
        updateHeight(node);
        return rotate(node);
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
        updateHeight(node);
        return rotate(node);
    }

    private void updateHeight(Node node) {
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }

    private int height(Node node) {
        return node == null ? 0 : node.getHeight();
    }

    private Node rotate(Node node) {
        int balance = balance(node);
        if (balance < -1) {
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
        if (balance > 1) {
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        return node;
    }



    private int balance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.getLeftChild()) - height(node.getRightChild());
    }

    private Node rotateLeft(Node node) {
        Node right = node.getRightChild();
        Node center = right.getLeftChild();
        right.setLeftChild(node);
        node.setRightChild(center);
        updateHeight(right);
        updateHeight(node);
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.getLeftChild();
        Node center = left.getRightChild();
        left.setRightChild(node);
        node.setLeftChild(center);
        updateHeight(left);
        updateHeight(node);
        return left;
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
