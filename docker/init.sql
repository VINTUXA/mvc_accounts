-- Create schema if not exists
CREATE SCHEMA IF NOT EXISTS contacts_schema;

-- Create table if not exists in the "contacts_schema" schema
CREATE TABLE IF NOT EXISTS contacts_schema.contact
(
    id BIGINT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);
