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
        } else {
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
        return node;
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
