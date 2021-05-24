CREATE SEQUENCE MOVIES_SEQ START 1;-- Table Definition ----------------------------------------------
CREATE TABLE TBL_movies (
    id bigint PRIMARY KEY DEFAULT nextval('MOVIES_SEQ'),
    movie_name character varying(200) NOT NULL UNIQUE,
    score integer
);

INSERT INTO TBL_movies("movie_name", "score") VALUES('Polar (2019)', 64); 
INSERT INTO TBL_movies("movie_name", "score") VALUES('Iron Man (2008)', 79); 
INSERT INTO TBL_movies("movie_name", "score") VALUES('The Shawshank Redemption (1994)', 93); 
INSERT INTO TBL_movies("movie_name", "score") VALUES('Forrest Gump (1994)', 83); 
INSERT INTO TBL_movies("movie_name", "score") VALUES('Glass (2019)', 70);


CREATE SEQUENCE USERS_SEQ START 1;
CREATE TABLE TBL_USERS (
    id bigint PRIMARY KEY DEFAULT nextval('USERS_SEQ'),
    LOGIN_ID varchar(32) NOT NULL,
    USER_PASSWORD varchar(100) NOT NULL,
    FULL_NAME varchar(30),
    PHONE varchar(20),
    FULL_ADDRESS varchar(60),
    EMAIL varchar(80),
    IS_DELETED numeric(2,0)
);

CREATE SEQUENCE ROLES_SEQ START 1;
CREATE TABLE TBL_ROLES (
    id bigint PRIMARY KEY DEFAULT nextval('ROLES_SEQ'),
    ROLE_NAME varchar(100),
    ROLE_CODE varchar(20) NOT NULL,
    IS_DELETED numeric(2,0) DEFAULT '0'::numeric
);

CREATE SEQUENCE USER_ROLE_SEQ START 1;
CREATE TABLE TBL_USER_ROLE (
    id bigint PRIMARY KEY DEFAULT nextval('USER_ROLE_SEQ'),
    USER_ID integer NOT NULL,
    ROLE_ID integer NOT NULL,
    IS_DELETED numeric(2,0) DEFAULT '0'::numeric,
    constraint FK_TBL_USER1
        foreign key (USER_ID) 
        REFERENCES TBL_USERS(id),
    constraint FK_TBL_ROLE1
        foreign key (ROLE_ID) 
        REFERENCES TBL_ROLES(id)
);

-- Create data Roles.
INSERT INTO TBL_ROLES(role_code, role_name, is_deleted) VALUES
('SYSTEM_ADMIN', 'System Admin', 0)
,('CORE_HOSPITAL', 'Core Hospital', 0)
,('COLLAB_CLINIC', 'Collab Clinic', 0)
,('MAMA_KELLY', 'Mama Kelly', 0)
,('INPUT_PERSON', 'Input Person', 0);

-- Create data user
INSERT INTO TBL_USERS(login_id, user_password, full_name, phone, full_address, email, is_deleted) VALUES
('0000000001', '$2a$10$8GC98PbjcqxiGyJ4u2jQ2ukA5ulBoB1v8QHixtFdYyDW4KM0TVaT.', NULL, NULL, NULL, NULL, 0)   -- asiantech
,('0000000002', '$2a$10$8GC98PbjcqxiGyJ4u2jQ2ukA5ulBoB1v8QHixtFdYyDW4KM0TVaT.', NULL, NULL, NULL, NULL, 0)
,('0000000003', '$2a$10$8GC98PbjcqxiGyJ4u2jQ2ukA5ulBoB1v8QHixtFdYyDW4KM0TVaT.', NULL, NULL, NULL, NULL, 0)
,('0000000004', '$2a$10$8GC98PbjcqxiGyJ4u2jQ2ukA5ulBoB1v8QHixtFdYyDW4KM0TVaT.', NULL, NULL, NULL, NULL, 0)
,('0000000005', '$2a$10$8GC98PbjcqxiGyJ4u2jQ2ukA5ulBoB1v8QHixtFdYyDW4KM0TVaT.', NULL, NULL, NULL, NULL, 0)
;

-- Create data user_role
INSERT INTO TBL_USER_ROLE(user_id, role_id, is_deleted) VALUES
(1, 1, 0)
,(2, 2, 0)
,(3, 3, 0)
,(4, 4, 0)
,(5, 5, 0);
