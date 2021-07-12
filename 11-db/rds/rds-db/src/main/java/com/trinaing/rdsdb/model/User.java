package com.trinaing.rdsdb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private Long id;

    private String name;

    private int age;

    private Email email;

    private String city;

    private boolean active;
}
