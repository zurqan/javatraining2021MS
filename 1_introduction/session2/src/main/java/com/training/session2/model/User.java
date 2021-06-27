package com.training.session2.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private String id;

    private String firstName;
    private String lastName;

    private int age;

    private LocalDate birthDay;

    private String department;

}
