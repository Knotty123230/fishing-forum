--liquibase formatted sql

--changeset VitaliyTaranenko:changelog-create-comments-table-
--comment: createuser_accounts table

CREATE TABLE forum.comments
(
    id       serial PRIMARY KEY,
    comment VARCHAR(255) NOT NULL,
    user_profile_id bigint not null,
    post_id bigint not null
)
--rollback DROP TABLE forum.comments

--changeset VitaliyTaranenko:add-comments-constraints
--comment add constraints to forum.comments table
alter table  forum.comments
    add constraint comments__user_profile__fk
        foreign key (user_profile_id) references forum.user_profiles (id) on DELETE no action ;

alter table forum.comments
    add constraint user_posts_comments__fk
        foreign key (post_id) references forum.user_posts (id) on delete no action ;

--rollback drop constraint comments__user_profile__fk
--rollback drop constraint user_posts_comments__fk
