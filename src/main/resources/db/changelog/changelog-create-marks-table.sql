--liquibase formatted sql


--changeset VitaliyTaranenko:changelog-create-geo-schema
--comment create schema geo
create schema geo;
--rollback drop schema geo;

--changeset VitaliyTaranenko:changelog-create-marks-table
--comment create table geo.marks
create table geo.marks
(
    id                serial primary key,
    lat               double precision not null,
    lng               double precision not null
);
--rollback drop table geo.marks;






