package com.trinaing.rdsdb.adapter.repository.impl.rds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

@Component
public class JPATest implements CommandLineRunner {
    private final UserRepositorySpringImpl userRepositorySpring;

    public JPATest(UserRepositorySpringImpl userRepositorySpring) {
        this.userRepositorySpring = userRepositorySpring;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {


        print(userRepositorySpring.findByNameOrderByNameAsc("Ahmad")
            .stream(),"findByNameOrderByNameAsc");

        print(userRepositorySpring.findByNameOrderByNameAsc("ahmad")
            .stream(),"findByNameOrderByNameAsc - ahmad");

        print(userRepositorySpring.findByNameIgnoreCaseOrderByNameAsc("ahmad")
            .stream(),"findByNameIgnoreCaseOrderByNameAsc - ahmad");

        print(userRepositorySpring.findByNameAndAgeOrderByNameAsc("Mosa",20)
            .stream(),"findByNameAndAgeOrderByNameAsc - Mosa");

        print(userRepositorySpring.findByNameContaining("Ahmad")
            .stream(),"findByNameContaining - Ahmad");

        print(userRepositorySpring.findByNameStartsWith("M")
            .stream(),"findByNameStartsWith - M");
//        userRepositorySpring.findByAgeGreaterThan(20);
        print(userRepositorySpring.findByAgeGreaterThan(20)
           ,"findByAgeGreaterThan - 20");
        print(userRepositorySpring.findByNameOrAgeOrderByNameDesc("Ahmad",20)
                .stream()
           ,"findByNameOrAgeOrderByNameDesc - 20");
        print(userRepositorySpring.findFirst2ByName("Ahmad")
                .stream()
           ,"findFirst2ByName - Ahmad");
        print(userRepositorySpring.findByNameEndsWith("a")
                .stream()
           ,"findByNameEndsWith - a");
        print(userRepositorySpring.findByAgeIn(Arrays.asList(20,10))
                .stream()
           ,"findByAgeIn - 20,10");
        print(userRepositorySpring.findByActiveTrue()
                .stream()
           ,"findByActiveTrue ");
        print(userRepositorySpring.findByActiveFalse()
                .stream()
           ,"findByActiveFalse ");

        print(userRepositorySpring.searchByEmail("ahmadIbid@g.com")
                        .map(Collections::singletonList)
                        .orElse(null)
                    .stream()

           ,"searchByEmail ahmadIbid@g.com");

        print(userRepositorySpring.searchBy("Ahmad","ahmadIbid@g.com")
                        .stream()
                ,"searchBy ");
        print(userRepositorySpring.searchBy("Mosa","ahmadIbid@g.com")
                        .stream()
                ,"searchBy ");
        print(userRepositorySpring.<UserEntity>searchBySPEL("Mosa","ahmadIbid@g.com")
                        .stream()
                ,"searchBySPEL ");

        print(userRepositorySpring.findByAgeBetween(11,29)
                        .stream()
                ,"findByAgeBetween ");

        userRepositorySpring
                .findByAgeLessThan(30)
                .stream()
                .map(n->n.getName())
                .forEach(System.out::println);

        userRepositorySpring
                .findByEmailContaining("@a.com")
                .stream()
                .map(n->new Tuple(n.getName(),n.getAge()))
                .forEach(System.out::println);
        System.out.println("findByEmailEndsWith=====");

        userRepositorySpring
                .findByEmailEndsWith("@a.com")
                .stream()
                .map(n->n.getData())
                .forEach(System.out::println);
        print(userRepositorySpring.doNative()
                        .stream()
                ,"doNative ");
        print(userRepositorySpring.doNative("Amman")
                        .stream()
                ,"doNative ");
       userRepositorySpring.searchByV3("Ahmad","ahmadIbid@g.com")
                        .stream()
               .forEach(System.out::println);
       userRepositorySpring.groupActive()
                        .stream()
               .forEach(System.out::println);
    }

    @Transactional
    public void print(Stream<UserEntity> stream,String queryMethod){
        System.out.println("===========================");
        System.out.println("Querry: "+queryMethod);
        stream.forEach(System.out::println);
        System.out.println("===========================");
    }

    public static class Tuple<T,U>{
        public final T _1;
        public final U _2;

        public Tuple(T _1, U _2) {
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
}
