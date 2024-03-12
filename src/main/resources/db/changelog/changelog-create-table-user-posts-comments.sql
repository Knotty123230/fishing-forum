--changeset VitaliyTaranenko:changelog-create-table-user_posts-commets
--comment: create-user-posts-comments table

CREATE TABLE forum.user_posts_comments (
                                           post_id BIGINT NOT NULL,
                                           comment_id BIGINT NOT NULL
);
--rollback DROP TABLE forum.user_posts_comments

ALTER TABLE forum.user_posts_comments
    ADD CONSTRAINT post_user_posts_comments_fk
        FOREIGN KEY (post_id) REFERENCES forum.user_posts(id);

--rollback DROP CONSTRAINT post_user_posts_comments_fk

ALTER TABLE forum.user_posts_comments
    ADD CONSTRAINT comment_user_posts_comments_fk
        FOREIGN KEY (comment_id) REFERENCES forum.comments(id);

--rollback DROP CONSTRAINT comment_user_posts_comments_fk
