package com.training.cloudstreambatchexample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SpringBootApplication
public class CloudStreamBatchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudStreamBatchExampleApplication.class, args);
	}


	@Data
    @AllArgsConstructor
    @NoArgsConstructor
	public static class User{

	    private String id;
	    private String name;
	    private int age;
    }

	@Bean
    public Function<List<User>,List<String>> names(){

	    return users->users.stream().map(User::getName).collect(Collectors.toList());
    }

    @Bean
    public Function<List<String>,String> join(){
	    return names->names.stream().collect(Collectors.joining(","));
    }

    @Bean
    public Function<String,String> upper(){
	    return String::toUpperCase;
    }

    @Bean
    public Consumer<Object> print(){
	    return obj-> System.out.println(obj);
    }

    @Bean
    public Supplier<User> generate(){
	    return ()-> new User("1212","Mosa",20);
    }
}
