package com.trinaing.rdsdb.adapter.repository.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private int age;

    @Column(unique = true)
    private String email;

    private String city;

    private boolean active;

}
