--changeset VitaliyTaranenko:drop-constraint-user_posts_comments__fk
--comment: Drop constraint user_posts_comments__fk from forum.comments table
ALTER TABLE forum.comments
    DROP CONSTRAINT user_posts_comments__fk;

--rollback DROP CONSTRAINT user_posts_comments__fk

--changeset VitaliyTaranenko:drop-column-post_id
--comment: Drop column post_id from forum.comments table
ALTER TABLE forum.comments
    DROP COLUMN post_id;
--rollback ALTER TABLE forum.comments
--rollback    ADD COLUMN post_id BIGINT;
