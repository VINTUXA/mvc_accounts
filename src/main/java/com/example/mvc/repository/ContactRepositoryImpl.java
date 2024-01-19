package com.example.mvc.repository;

import com.example.mvc.Contact;
import com.example.mvc.exception.ContactNotFoundException;
import com.example.mvc.repository.mapper.ContactRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ContactRepositoryImpl implements ContactRepository{

    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Contact> findAll() {
        log.debug("Calling ContactRepository -> findAll");

        String sql = "SELECT * FROM contact";

        return jdbcTemplate.query(sql, new ContactRowMapper());
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("Calling ContactRepository -> findById with ID: {}", id);
        String sql = "SELECT * FROM contact where id = ?";
        Contact contact = DataAccessUtils.singleResult(
                jdbcTemplate.query(
                        sql,
                        new ArgumentPreparedStatementSetter(new Object[] {id}),
                        new RowMapperResultSetExtractor<>(new ContactRowMapper(), 1))
        );
        return Optional.ofNullable(contact);
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Calling ContactRepository -> save with contact: {}", contact);

        contact.setId(System.currentTimeMillis());
        String sql = "INSERT INTO contact (first_name, last_name, phone_number, email, id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql
                , contact.getFirstName()
                , contact.getLastName()
                , contact.getPhoneNumber()
                , contact.getEmail()
                , contact.getId());
        return contact;
    }

    @Override
    public Contact update(Contact contact) {
        log.debug("Calling ContactRepository -> update with contact: {}", contact);

        Contact existingContact = findById(contact.getId()).orElse(null);
        if (existingContact != null){
            String sql = "UPDATE contact SET first_name = ?, last_name = ?, phone_number = ?, email = ? WHERE id = ?";
            jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber(), contact.getEmail(), existingContact.getId());
            return contact;
        }
        log.warn("Contact with id : {} not found", contact.getId());
        throw new ContactNotFoundException("Contact with id : {} not found" + contact.getId());
    }

    @Override
    public void delete(Long id) {
        log.debug("Calling ContactRepository -> delete with contact id : {}", id);
        String sql = "DELETE FROM contact WHERE id = ?";
        jdbcTemplate.update(sql, id);

    }
}
