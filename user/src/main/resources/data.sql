INSERT INTO `users` (username, password, email, name, last_name) VALUES ('alejandro', 'sevaacambiar', 'alejandro95@gmail.com', 'Alejandro', 'Calderon');
INSERT INTO `users` (username, password, email, name, last_name) VALUES ('master', 'sevaacambiar2', 'test@gmail.com', 'Test', 'Prueba');

INSERT INTO `roles` (name) VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (name) VALUES ('ROLE_USER');

INSERT INTO `user_roles` (user_id, role_id) VALUES (1,1);
INSERT INTO `user_roles` (user_id, role_id) VALUES (2,2);
INSERT INTO `user_roles` (user_id, role_id) VALUES (2,1);