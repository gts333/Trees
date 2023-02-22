package com.company;

public interface MyTree {

    MyTree insert(int data);

    void delete(int data);

    void traverse();

    Integer getMax();

    Integer getMin();

    boolean isEmpty();
}
