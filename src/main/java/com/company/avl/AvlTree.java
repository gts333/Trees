package com.company.avl;

import com.company.MyTree;
import com.company.Node;

public class AvlTree implements MyTree {
    private Node root;

    @Override
    public MyTree insert(int data) {
        return null;
    }

    @Override
    public void delete(int data) {

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
