INSERT INTO roles (name)
VALUES ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password, email)
VALUES ('user', '$2a$12$J7XWTPK2GY7USJ6SJLZRwem2OvB55mTnhzROyovBBpvIFs4AbhphG', 'user@gmail.com'),
       ('admin', '$2a$12$J7XWTPK2GY7USJ6SJLZRwem2OvB55mTnhzROyovBBpvIFs4AbhphG', 'admin@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1), (2, 2);