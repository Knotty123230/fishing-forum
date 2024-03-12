--changeset VitaliyTaranenko:create-user-profile-likes-table
--comment: create user_profile_likes table

CREATE TABLE forum.user_profile_likes
(
    like_id         SERIAL PRIMARY KEY,
    user_profile_id BIGINT,
    CONSTRAINT user_profile_likes__user_profile_id__fk FOREIGN KEY (user_profile_id) REFERENCES forum.user_profiles (id)
);

--rollback DROP TABLE forum.user_profile_likes;
--rollback drop constraint user_profile_likes__user_profile_id__fk;

--changeset VitaliyTaranenko:create-post-likes-table
--comment: create post_likes table

CREATE TABLE forum.post_likes
(
    like_id  SERIAL PRIMARY KEY,
    post_id  BIGINT,
    CONSTRAINT post_likes__post_id__fk FOREIGN KEY (post_id) REFERENCES forum.user_posts (id)
);

--rollback DROP TABLE forum.post_likes;
--rollback drop constraint post_likes__post_id__fk;

--changeset VitaliyTaranenko:create-comment-likes-table
--comment: create comment_likes table

CREATE TABLE forum.comment_likes
(
    like_id     SERIAL PRIMARY KEY,
    comment_id  BIGINT,
    CONSTRAINT comment_likes__comment_id__fk FOREIGN KEY (comment_id) REFERENCES forum.comments (id)
);

--rollback DROP TABLE forum.comment_likes;
--rollback drop constraint comment_likes__comment_id__fk;

--changeset VitaliyTaranenko:create-likes-table
--comment: create likes-table
create table forum.likes
(
                      id serial primary key
)
--rollback drop table likes;