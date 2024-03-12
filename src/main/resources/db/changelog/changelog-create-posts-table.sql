--liquibase formatted sql

--changeset VitaliyTaranenko:changelog-create-posts-table
--comment create table forum.user_posts
create table forum.user_posts
(
    id                serial primary key,
    name           varchar(180) not null,
    user_profile_id   integer      not null,
    created_timestamp timestamp    not null,
    image_url         varchar(128) not null,
    description       varchar(1000) not null
);
--rollback drop table forum.user_posts;

--changeset VitaliyTaranenko:add-posts-user_profiles-table-constraints
--comment add constraints to forum.user_posts table
alter table forum.user_posts
    add constraint posts__user_profiles__fk
        foreign key (user_profile_id) references forum.user_profiles (id);

--rollback alter table forum.user_posts drop constraint posts__user_profiles__fk;

--changeset VitaliyTaranenko:add-posts-user_profiles-table-column-modified_timestamp
--comment add column modified_timestamp to forum.user_posts table
alter table forum.user_posts
    add column modified_timestamp timestamp;

update forum.user_posts
set modified_timestamp = created_timestamp
where modified_timestamp is null;

alter table forum.user_posts
    alter column modified_timestamp set not null;
--rollback alter table forum.user_posts drop column modified_timestamp;