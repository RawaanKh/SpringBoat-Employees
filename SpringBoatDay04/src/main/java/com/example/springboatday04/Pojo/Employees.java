package com.example.springboatday04.Pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.NumberFormat;
import org.hibernate.validator.constraints.Length;
@Data
@AllArgsConstructor
public class Employees {
@NotNull(message = "ID can not be null!")
@Size(min = 3,max = 10 , message = "ID should be longer than 3 ")
private String id;
     //----------
    @NotNull(message = "ID cannot be null!")
    @Size(min = 3,max = 15, message = "Name should be greater than 3 characters and less than 15")
    private String name;
     //----------
    @Min(value = 20, message = "Employee should be at least 20 years old.")
    @NotNull(message = "Age cannot be null!")
    @Positive(message = "age must be positive number only")
    private int age;
    //----------
    @NotNull(message = "Leave cannot be null")
    @AssertFalse(message = "on leave is prevented at the beginning")
    private boolean onLeave;
    //-------
    @NotNull(message = "Role is needed")
    @Pattern(regexp = ("^supervisor||coordinator$"), message = "Role should be eather a coordinator or supervisor ")
    private String role;
    //--------
    @NotNull(message = "Employment year is required")
    @Min(value = 1985, message = "Employment year should be between(1985-2023)")
    @Max(value = 2023)
    @Positive(message = "positive number is a must")
    private int employmentYear;
    //------
    @NotNull(message = "Annual leave cannot be null!")
    @PositiveOrZero
    private int annualLeave;




}
