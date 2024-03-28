-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-03-28 12:01:59.87

-- tables
-- Table: city
CREATE TABLE city (
                      id serial  NOT NULL,
                      name varchar(255)  NOT NULL,
                      CONSTRAINT city_pk PRIMARY KEY (id)
);

-- Table: location
CREATE TABLE location (
                          id serial  NOT NULL,
                          city_id int  NOT NULL,
                          name varchar(255)  NOT NULL,
                          number_of_atms int  NOT NULL,
                          status char(1)  NOT NULL,
                          CONSTRAINT location_pk PRIMARY KEY (id)
);

-- Table: location_image
CREATE TABLE location_image (
                                id serial  NOT NULL,
                                location_id int  NOT NULL,
                                data bytea  NOT NULL,
                                CONSTRAINT location_image_pk PRIMARY KEY (id)
);

-- Table: location_transaction_type
CREATE TABLE location_transaction_type (
                                           id serial  NOT NULL,
                                           location_id int  NOT NULL,
                                           transaction_type_id int  NOT NULL,
                                           CONSTRAINT location_transaction_type_pk PRIMARY KEY (id)
);

-- Table: role
CREATE TABLE role (
                      id serial  NOT NULL,
                      name varchar(255)  NOT NULL,
                      CONSTRAINT role_ak_1 UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE,
                      CONSTRAINT role_pk PRIMARY KEY (id)
);

-- Table: transaction_type
CREATE TABLE transaction_type (
                                  id serial  NOT NULL,
                                  name varchar(255)  NOT NULL,
                                  CONSTRAINT transaction_type_pk PRIMARY KEY (id)
);

-- Table: user
CREATE TABLE "user" (
                        id serial  NOT NULL,
                        role_id int  NOT NULL,
                        username varchar(255)  NOT NULL,
                        password varchar(255)  NOT NULL,
                        status char(1)  NOT NULL,
                        CONSTRAINT user_ak_1 UNIQUE (username) NOT DEFERRABLE  INITIALLY IMMEDIATE,
                        CONSTRAINT user_pk PRIMARY KEY (id)
);

-- Table: user_image
CREATE TABLE user_image (
                            id serial  NOT NULL,
                            user_id int  NOT NULL,
                            data bytea  NOT NULL,
                            CONSTRAINT user_image_pk PRIMARY KEY (id)
);

-- foreign keys
-- Reference: location_city (table: location)
ALTER TABLE location ADD CONSTRAINT location_city
    FOREIGN KEY (city_id)
        REFERENCES city (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: location_image_location (table: location_image)
ALTER TABLE location_image ADD CONSTRAINT location_image_location
    FOREIGN KEY (location_id)
        REFERENCES location (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: location_transaction_type_location (table: location_transaction_type)
ALTER TABLE location_transaction_type ADD CONSTRAINT location_transaction_type_location
    FOREIGN KEY (location_id)
        REFERENCES location (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: location_transaction_type_transaction_type (table: location_transaction_type)
ALTER TABLE location_transaction_type ADD CONSTRAINT location_transaction_type_transaction_type
    FOREIGN KEY (transaction_type_id)
        REFERENCES transaction_type (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_image_user (table: user_image)
ALTER TABLE user_image ADD CONSTRAINT user_image_user
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- Reference: user_role (table: user)
ALTER TABLE "user" ADD CONSTRAINT user_role
    FOREIGN KEY (role_id)
        REFERENCES role (id)
        NOT DEFERRABLE
            INITIALLY IMMEDIATE
;

-- End of file.

