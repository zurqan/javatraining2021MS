package com.trinaing.rdsdb.adapter.rest.dto;

import com.trinaing.rdsdb.model.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String name;

    private int age;

    private String email;

    private String city;

    private boolean active;

}
