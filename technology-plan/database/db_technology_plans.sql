##
## CREATE DATABASE TECHNOLOGY_PLANS
##

create database technology_plans;
use technology_plans;

-- table projects
create table tb_projects
(
    ID           bigint auto_increment primary key,
    CREATED_AT   datetime(6)  null,
    DELETED_AT   datetime(6)  null,
    UPDATED_AT   datetime(6)  null,
    UPDATED_BY   bigint       null,
    CONTENT      varchar(255) null,
    MANAGER      varchar(255) null,
    NAME_PROJECT varchar(255) null,
    STRUCTURE    varchar(255) null
);

-- table technologys
create table tb_technologys
(
    ID              bigint auto_increment primary key,
    CREATED_AT      datetime(6)  null,
    DELETED_AT      datetime(6)  null,
    UPDATED_AT      datetime(6)  null,
    UPDATED_BY      bigint       null,
    TECHNOLOGY_NAME varchar(255) null
);



-- table tb_project_technologys
create table tb_project_technologys
(
    ID            bigint auto_increment primary key,
    CREATED_AT    datetime(6) null,
    DELETED_AT    datetime(6) null,
    UPDATED_AT    datetime(6) null,
    UPDATED_BY    bigint      null,
    PROJECT_ID    bigint      null,
    TECHNOLOGY_ID bigint      null
);

-- table technology_plan_contents
create table tb_technology_plan_contents
(
    ID            bigint auto_increment primary key,
    CREATED_AT    datetime(6)  null,
    DELETED_AT    datetime(6)  null,
    UPDATED_AT    datetime(6)  null,
    UPDATED_BY    bigint       null,
    PLAN_CONTENT  varchar(255) null,
    TECHNOLOGY_ID bigint       null,
    constraint FK_tech_techPlan foreign key (TECHNOLOGY_ID) references tb_technologys (ID)
);

-- table hashtags
create table tb_hashtags
(
    ID            bigint auto_increment primary key,
    CREATED_AT    datetime(6)  null,
    DELETED_AT    datetime(6)  null,
    UPDATED_AT    datetime(6)  null,
    UPDATED_BY    bigint       null,
    HASTAG_NAME   varchar(255) null,
    TECHNOLOGY_ID bigint       null,
    URL_LINK      varchar(255) null,
    constraint FK_tech_hash foreign key (TECHNOLOGY_ID) references tb_technologys (ID)
);


