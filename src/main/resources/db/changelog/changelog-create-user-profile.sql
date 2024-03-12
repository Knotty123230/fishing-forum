--liquibase formatted sql


--changeset VitaliyTaranenko:create-forum-user_profiles-table

--comment create table forum.user_profiles
create table forum.user_profiles
(
    id         integer primary key unique ,
    nickname   varchar(32)  not null,
    image_link varchar(128) not null
);
--rollback drop table forum.user_profiles;

--changeset VitaliyTaranenko:add-forum-user_profiles-table-constraints
--comment add constraints to forum.user_profiles table
alter table forum.user_profiles
    add constraint user_profiles__user_accounts__fk
        foreign key (id) references users.user_accounts (id);

alter table forum.user_profiles
    add constraint user_profiles__nickname__unique
        unique (nickname);
--rollback alter table forum.user_profiles drop constraint user_profiles__user_accounts__fk;
--rollback alter table forum.user_profiles drop constraint user_profiles__nickname__unique;