package com.training.servicea.adapter.rest;

public class Tuple<A, B> {

    public final A _1;
    public final B _2;

    public Tuple(A a, B b) {
        _1 = a;
        _2 = b;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "_1=" + _1 +
                ", _2=" + _2 +
                '}';
    }
}
