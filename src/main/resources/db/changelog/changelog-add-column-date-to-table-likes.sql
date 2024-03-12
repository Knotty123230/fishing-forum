--changeset VitaliyTaranenko:add-created-timestamp-to-likes
--comment: add created_timestamp column to likes table

ALTER TABLE forum.likes
    ADD COLUMN created_timestamp TIMESTAMP NOT NULL;

--rollback ALTER TABLE forum.comments DROP COLUMN created_timestamp;