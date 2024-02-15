package com.example.demo.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Document(collection = "employee") 
public class Employee {

    @Id
    private UUID id;

    @NotNull(message = "Employee name cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Employee name must contain only alphabetic characters and spaces")
    private String employeeName;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    private UUID reportsTo;

    private String profileImage;

    public Employee() {
        this.id = UUID.randomUUID();
    }
}
