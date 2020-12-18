INSERT INTO `users` (username, password, email, name, last_name, enabled) VALUES ('alejandro', '$2a$10$ti7/C4R8teKCMs1TjTjnfuSeavoLQs9tHRWyvGr06jYBJ7EKMN9bi', 'alejandro95@gmail.com', 'Alejandro', 'Calderon', true);
INSERT INTO `users` (username, password, email, name, last_name, enabled) VALUES ('master', '$2a$10$myS1FoIEJRJnOeBQTToaY.qXrPz/gnS4OUA7XfoRZYuhigldbjoNe', 'test@gmail.com', 'Test', 'Prueba', true);

INSERT INTO `roles` (name) VALUES ('ROLE_USER');
INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');

INSERT INTO `user_roles` (user_id, role_id) VALUES (1,1);
INSERT INTO `user_roles` (user_id, role_id) VALUES (2,2);
