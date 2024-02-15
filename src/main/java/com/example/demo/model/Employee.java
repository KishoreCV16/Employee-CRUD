package com.example.demo.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "employee_name")
    @NotNull(message = "Employee name cannot be null")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Employee name must contain only alphabetic characters and spaces")
    private String employeeName;

    @Column(name = "phone_number")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @Column(name = "email")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Column(name = "reports_to")
    private UUID reportsTo;

    @Column(name = "profile_image")
    private String profileImage;

    public Employee() {
        this.id = UUID.randomUUID();
    }
}
