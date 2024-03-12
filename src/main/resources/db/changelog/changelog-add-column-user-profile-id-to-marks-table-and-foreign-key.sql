-- Add column user_profile_id to geo.marks table
ALTER TABLE geo.marks
    ADD COLUMN user_profile_id BIGINT ;

-- Ensure that the user_profile_id column is unique
-- You might need to add a unique constraint if it's not already unique

-- Add foreign key constraint to link forum.user_profiles table with geo.marks table
ALTER TABLE geo.marks
    ADD CONSTRAINT fk_marks_user_profiles
        FOREIGN KEY (user_profile_id) REFERENCES forum.user_profiles(id);
