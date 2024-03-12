--liquibase formatted sql

--changeset VitaliyTaranenko:changelog-create-schema-forum
--comment create schema forum

create schema forum
--rollback drop schema forum