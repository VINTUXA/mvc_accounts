package com.example.mvc;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@FieldNameConstants
public class Contact {
//    Сущность «Контакт» должна представлять из себя
//    идентификатор (id), имя (firstName),
//    фамилию (lastName), почту (email) и телефон (phone).
//
//    public static final String DB_FIRST_NAME = "DB_FIRST_NAME";
//    public static final String DB_LAST_NAME = "DB_LAST_NAME";
//    public static final String DB_EMAIL = "DB_EMAIL";
//    public static final String DB_PHONE_NUMBER = "DB_PHONE_NUMBER";


    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

}
