package com.training.session2.adapter.rest.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Builder
public class UserDTO {

    private String id;

    @NotEmpty
    private String name;

    @Min(10)
    private int userAge;

    private LocalDate birthDay;

}
