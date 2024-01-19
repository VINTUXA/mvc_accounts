package com.example.mvc.repository.mapper;

import com.example.mvc.Contact;
import com.example.mvc.ContactRowMapperFields;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();

        contact.setId(rs.getLong(ContactRowMapperFields.ID));
        contact.setFirstName(rs.getString(ContactRowMapperFields.FIRST_NAME));
        contact.setLastName(rs.getString(ContactRowMapperFields.LAST_NAME));
        contact.setPhoneNumber(rs.getString(ContactRowMapperFields.PHONE_NUMBER));
        contact.setEmail(rs.getString(ContactRowMapperFields.EMAIL));

        return contact;
    }
}
