--liquibase formatted sql

--changeset VitaliyTaranenko:changelog-create-user-table-and-roles-table
--comment: createuser_accounts table

CREATE TABLE users.user_accounts
(
    id       serial PRIMARY KEY,
    username VARCHAR(255) unique NOT NULL,
    password VARCHAR(255)        NOT NULL
)
--rollback DROP TABLE users.user_account



--changeset VitaliyTaranenko:create-user_roles-table
--comment: create user_roles table
CREATE TABLE users.user_roles
(
    id        serial PRIMARY KEY,
    authority VARCHAR(255) NOT NULL
)
--rollback DROP TABLE users.user_roles



--changeset VitaliyTaranenko:create-user_account_roles-table
--comment: create user_accounts_roles table
CREATE TABLE users.user_accounts_roles
(
    user_account_id INTEGER not null,
    user_role_id    INTEGER not null
)
--rollback DROP TABLE users.user_account_roles



--changeset VitataliyTaranenko:add-user_accounts_roles-table-constraints
--comment add user_account_roles table constraints

ALTER TABLE users.user_accounts_roles
    ADD CONSTRAINT user_account_roles_user_account_id_fkey
        FOREIGN KEY (user_account_id) REFERENCES users.user_accounts (id);
ALTER TABLE users.user_accounts_roles
    ADD CONSTRAINT user_account_roles_user_role_id_fkey
        FOREIGN KEY (user_role_id) REFERENCES users.user_roles (id);
ALTER TABLE users.user_accounts_roles
    ADD CONSTRAINT user_account_roles_user_account_id_user_role_id_key
        UNIQUE (user_account_id, user_role_id)
--ROLLBACK ALTER TABLE users.user_accounts_roles DROP CONSTRAINT user_account_roles_user_account_id_fkey
--ROL:BACK ALTER TABLE users.user_accounts_roles DROP CONSTRAINT user_account_roles_user_role_id_fkey
--ROLLBACK ALTER TABLE users.user_accounts_roles DROP CONSTRAINT user_account_roles_user_account_id_user_role_id_key

--changeset VitaliyTaranenko:add-data-to-user_account-roles-table
--comment add data to user_accounts_roles table
INSERT INTO users.user_roles (authority)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN');
--rollback TRUNCATE TABLE users.user_roles