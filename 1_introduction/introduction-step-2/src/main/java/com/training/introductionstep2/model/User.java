package com.training.introductionstep2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

//@Data
@AllArgsConstructor
@Value
public class User {

    private String id;
    private String name;

    private int Age;

}
