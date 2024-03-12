--changeset VitaliyTaranenko:add-created-timestamp-to-comments
--comment: add created_timestamp column to comments table

ALTER TABLE forum.comments
    ADD COLUMN created_timestamp TIMESTAMP NOT NULL;

--rollback ALTER TABLE forum.comments DROP COLUMN created_timestamp;

--changeset VitaliyTaranenko:add-edited-timestamp-to-comments
--comment: add edited_timestamp column to comments table

ALTER TABLE forum.comments
    ADD COLUMN edited_timestamp TIMESTAMP;

--rollback ALTER TABLE forum.comments DROP COLUMN edited_timestamp;
