package com.training.session2.adapter.rest.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDTO {

    private String id;

    private String name;

    private int userAge;

    private LocalDate birthDay;

}
