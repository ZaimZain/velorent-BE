ALTER TABLE rentals
ADD COLUMN created_by BIGINT;

ALTER TABLE rentals
ADD CONSTRAINT fk_rental_created_by
FOREIGN KEY (created_by)
REFERENCES users(user_id);