--changeset VitaliyTaranenko:add-forum-posts-table-column
--comment Add mark_id column to forum.user_posts table to reference geo.marks
ALTER TABLE forum.user_posts
    ADD COLUMN mark_id BIGINT;
ALTER TABLE forum.user_posts
    ADD CONSTRAINT fk_user_posts_mark_id
        FOREIGN KEY (mark_id) REFERENCES geo.marks(id);
--rollback ALTER TABLE forum.user_posts DROP COLUMN mark_id;

--changeset VitaliyTaranenko:modify-rollback-for-add-forum-posts-table-column
--comment Adjust rollback for adding mark_id to drop the foreign key before dropping the column
--rollback DROP CONSTRAINT fk_user_posts_mark_id FROM forum.user_posts;
--rollback ALTER TABLE forum.user_posts DROP COLUMN mark_id;