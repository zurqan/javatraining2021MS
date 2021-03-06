package com.training.session2.common.util;

public class Tuple<A,B> {
    public final A _1;
    public final B _2;

    public Tuple(A _1, B _2) {
        this._1 = _1;
        this._2 = _2;
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "_1=" + _1 +
                ", _2=" + _2 +
                '}';
    }
}
