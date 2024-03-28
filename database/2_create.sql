
-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-20 11:58:32.16

-- tables
-- Table: role
CREATE TABLE role
(
    id   serial      NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user"
(
    id       serial       NOT NULL,
    role_id  int          NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    status   char(1)      NOT NULL,
    CONSTRAINT user_ak_1 UNIQUE (username) NOT DEFERRABLE INITIALLY IMMEDIATE,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE city
(
    id   serial       NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT city_ak_1 UNIQUE (name) NOT DEFERRABLE INITIALLY IMMEDIATE,
    CONSTRAINT city_pk PRIMARY KEY (id)
);

-- Table: image
CREATE TABLE image
(
    id   serial NOT NULL,
    data bytea  NOT NULL,
    CONSTRAINT image_pk PRIMARY KEY (id)
);

-- Table: location
CREATE TABLE location
(
    id             serial       NOT NULL,
    city_id        int          NOT NULL,
    name           varchar(255) NOT NULL,
    number_of_atms int          NOT NULL,
    image_id       int          NULL,
    status         char(1)      NOT NULL,
    CONSTRAINT location_ak_1 UNIQUE (name) NOT DEFERRABLE INITIALLY IMMEDIATE,
    CONSTRAINT location_pk PRIMARY KEY (id)
);

-- Table: transaction_type
CREATE TABLE transaction_type
(
    id   serial      NOT NULL,
    name varchar(50) NOT NULL,
    CONSTRAINT transaction_type_pk PRIMARY KEY (id)
);

-- Table: location_transaction_type
CREATE TABLE location_transaction_type
(
    id                  serial NOT NULL,
    location_id         int    NOT NULL,
    transaction_type_id int    NOT NULL,
    CONSTRAINT location_transaction_type_pk PRIMARY KEY (id)
);


-- foreign keys
-- Reference: user_role (table: user)
ALTER TABLE "user"
    ADD CONSTRAINT user_role
        FOREIGN KEY (role_id)
            REFERENCES role (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- foreign keys
-- Reference: location_city (table: location)
ALTER TABLE location
    ADD CONSTRAINT location_city
        FOREIGN KEY (city_id)
            REFERENCES city (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;
-- foreign keys
-- Reference: location_image (table: location)
ALTER TABLE location
    ADD CONSTRAINT location_image
        FOREIGN KEY (image_id)
            REFERENCES image (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: location_transaction_type_location (table: location_transaction_type)
ALTER TABLE location_transaction_type
    ADD CONSTRAINT location_transaction_type_location
        FOREIGN KEY (location_id)
            REFERENCES location (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE
;

-- Reference: location_transaction_type_transaction_type (table: location_transaction_type)
ALTER TABLE location_transaction_type
    ADD CONSTRAINT location_transaction_type_transaction_type
        FOREIGN KEY (transaction_type_id)
            REFERENCES transaction_type (id)
            NOT DEFERRABLE
                INITIALLY IMMEDIATE

-- End of file.

