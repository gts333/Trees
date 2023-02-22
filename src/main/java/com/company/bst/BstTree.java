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
        } else if (val > node.getData()){
            node.setRightChild(insert(node.getRightChild(), val));
        } else {
            return node;
        }
        updateHeight(node);
        return applyRotation(node);
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
            Node right = node.getRightChild();
            Node left = node.getLeftChild();
            if (right == null) {
                return left;
            }
            Node temp = right;
            while (temp.getLeftChild() != null) {
                temp = temp.getLeftChild();
            }
            temp.setLeftChild(left);
            return right;
        }
        if (val < node.getData()) {
            node.setLeftChild(delete(node.getLeftChild(), val));
        } else {
            node.setRightChild(delete(node.getRightChild(), val));
        }
        updateHeight(node);
        return applyRotation(node);
    }

    private void updateHeight(Node node) {
        node.setHeight(Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1);
    }

    private int height(Node node) {
        return node == null ? 0 : node.getHeight();
    }

    private Node applyRotation(Node node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeftChild()) < 0) {
                node.setLeftChild(rotateLeft(node.getLeftChild()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRightChild()) > 0) {
                node.setRightChild(rotateRight(node.getRightChild()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.getLeftChild();
        Node centerNode = leftNode.getRightChild();
        leftNode.setRightChild(node);
        node.setLeftChild(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.getRightChild();
        Node centerNode = rightNode.getLeftChild();
        rightNode.setLeftChild(node);
        node.setRightChild(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private int balance(Node node) {
        return node == null ? 0 : height(node.getLeftChild()) - height(node.getRightChild());
    }

    @Override
    public void traverse() {
        traverse(root);
    }

    private void traverse(Node root) {
        if (root == null) {
            return;
        }
        traverse(root.getLeftChild());
        System.out.println(root.getData());
        traverse(root.getRightChild());
    }

    @Override
    public Integer getMax() {
        if (isEmpty()) {
            return null;
        }
        return getMax(root);
    }

    private Integer getMax(Node node) {
        if (node.getRightChild() == null) {
            return node.getData();
        }
        return getMax(node.getRightChild());
    }

    @Override
    public Integer getMin() {
        if (isEmpty()) {
            return null;
        }
        return getMin(root);
    }

    private Integer getMin(Node node) {
        if (node.getLeftChild() == null) {
            return node.getData();
        }
        return getMin(node.getLeftChild());
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
