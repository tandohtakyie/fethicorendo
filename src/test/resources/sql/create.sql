# creating and connecting to database
DROP DATABASE IF EXISTS corendon;
CREATE DATABASE IF NOT EXISTS corendon;
USE corendon;

SET @@GLOBAL.sql_mode="STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION";

# creating general user

GRANT ALL PRIVILEGES ON corendon.*
TO 'admin'@'%'
IDENTIFIED BY 'Corendon001';

FLUSH PRIVILEGES;

# creating tables

# countries
CREATE TABLE IF NOT EXISTS countries (
    id SERIAL,
    code VARCHAR(10) NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# luggage types
CREATE TABLE IF NOT EXISTS luggage_types (
    id SERIAL,
    name VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# colors
CREATE TABLE IF NOT EXISTS colors (
    id SERIAL,
    ral_code INT NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# brands
CREATE TABLE IF NOT EXISTS brands (
    id SERIAL,
    name VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# statuses
CREATE TABLE IF NOT EXISTS statuses (
    id SERIAL,
    name VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# airports
CREATE TABLE IF NOT EXISTS airports (
    id SERIAL,
    name VARCHAR(50) UNIQUE NOT NULL,
    country_id BIGINT UNSIGNED NOT NULL,
    timezone TINYINT NOT NULL,
    daylight_saving BOOLEAN NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (country_id) REFERENCES countries(id)
);

# flights
CREATE TABLE IF NOT EXISTS flights (
    id SERIAL,
    flightnumber VARCHAR(50) UNIQUE NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# labels
CREATE TABLE IF NOT EXISTS labels (
    id SERIAL,
    tag VARCHAR(50) NOT NULL,
    flight_id BIGINT UNSIGNED NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (flight_id) REFERENCES flights(id)
);

# passengers
CREATE TABLE IF NOT EXISTS passengers (
    id SERIAL,
    first_name VARCHAR(50),
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    address VARCHAR(50),
    postcode VARCHAR(50),
    city VARCHAR(50),
    country_id BIGINT UNSIGNED,
    email VARCHAR(50),
    phone VARCHAR(50),
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (country_id) REFERENCES countries(id)
);

# insurance companies
CREATE TABLE IF NOT EXISTS insurance_companies (
    id SERIAL,
    name VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# luggages
CREATE TABLE IF NOT EXISTS luggages (
    id SERIAL,
    registration_id VARCHAR(100) NOT NULL,
    date_found DATE,
    luggage_type_id BIGINT UNSIGNED,
    brand_id BIGINT UNSIGNED,
    label_id BIGINT UNSIGNED,
    location_found_id BIGINT UNSIGNED,
    primary_color_id BIGINT UNSIGNED,
    secondary_color_id BIGINT UNSIGNED,
    size_width FLOAT,
    size_length FLOAT,
    size_height FLOAT,
    weight FLOAT,
    passenger_id BIGINT UNSIGNED,
    other_characteristics TEXT,
    insurance_company_id BIGINT UNSIGNED,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (luggage_type_id) REFERENCES luggage_types(id),
    FOREIGN KEY (brand_id) REFERENCES brands(id),
    FOREIGN KEY (label_id) REFERENCES labels(id),
    FOREIGN KEY (location_found_id) REFERENCES airports(id),
    FOREIGN KEY (primary_color_id) REFERENCES colors(id),
    FOREIGN KEY (secondary_color_id) REFERENCES colors(id),
    FOREIGN KEY (passenger_id) REFERENCES passengers(id),
    FOREIGN KEY (insurance_company_id) REFERENCES insurance_companies(id)
);

# statuses history
CREATE TABLE IF NOT EXISTS statuses_history (
	luggage_id BIGINT UNSIGNED,
	status_id BIGINT UNSIGNED,
	created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (luggage_id) REFERENCES luggages(id),
	FOREIGN KEY (status_id) REFERENCES statuses(id),
	PRIMARY KEY (luggage_id, status_id)
);

# functions
CREATE TABLE IF NOT EXISTS functions (
    id SERIAL,
    name VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# department locations
CREATE TABLE IF NOT EXISTS department_locations (
    id SERIAL,
    location VARCHAR(50) NOT NULL,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

# users
CREATE TABLE IF NOT EXISTS users (
    id SERIAL,
    username VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(50) NOT NULL,
    function_id BIGINT UNSIGNED NOT NULL,
    employment_date DATE NOT NULL,
    country_id BIGINT UNSIGNED NOT NULL,
    password VARCHAR(50) NOT NULL, # TODO fix hash+salt
    image LONGBLOB,
    reset_password BOOLEAN,
    created_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (function_id) REFERENCES functions(id),
    FOREIGN KEY (country_id) REFERENCES countries(id)
);