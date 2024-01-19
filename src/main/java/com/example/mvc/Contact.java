package com.example.mvc;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class Contact {
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

}
