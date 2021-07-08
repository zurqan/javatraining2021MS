package com.training.cloudstreamhellofunc;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class HelperClass {

    public static <T,U> Consumer<T>  compose(Consumer<U> consumer, Function<T,? extends U> function){
        return t-> consumer.accept(function.apply(t));
    }

    public static <T,U> Supplier<U>  compose( Function<T,? extends U> function,Supplier<? extends T> supplier){
        return ()-> function.apply(supplier.get());
    }

    public static <T,U>   VoidConsumer compose( Consumer<U> consumer,Function<T,? extends U> function,Supplier<? extends T> supplier){
        return ()-> consumer.accept(function.apply(supplier.get())); // ()->{}
    }

    public static interface VoidConsumer{
        public void doJob();
    }
}
