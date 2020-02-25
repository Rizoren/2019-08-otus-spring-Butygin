delete from tasks;
delete from users_authority;
delete from users;
delete from authority;

INSERT INTO authority (authority_name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (authority_name) VALUES ('ROLE_USER');

INSERT INTO users (user_id, user_name, user_password) VALUES (1, 'admin', 'admin');
INSERT INTO users (user_id, user_name, user_password) VALUES (2, 'Ivan', 'Ivan');
INSERT INTO users (user_id, user_name, user_password) VALUES (3, 'Petr', 'Petr');
ALTER SEQUENCE users_user_id_seq RESTART WITH 4;

INSERT INTO users_authority (user_id, authority_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO users_authority (user_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO users_authority (user_id, authority_name) VALUES (2, 'ROLE_USER');
INSERT INTO users_authority (user_id, authority_name) VALUES (3, 'ROLE_USER');
INSERT INTO users_authority (user_id, authority_name) VALUES (3, 'ROLE_ADMIN');

INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (2, 'Check ToDo', 'Monitoring', 0, '2019-10-28 06:23:39.954000', 1);
INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (3, 'Go to supermarket', 'Buy anything', 1, '2019-10-28 06:23:39.954000', 2);
INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (4, 'Walk', 'Ddvf fd fdfdbd fd fdbfb  fdfbdbf dfbfdbbfdbdf', 0, '2019-10-28 04:24:33.308000', 3);
ALTER SEQUENCE tasks_task_id_seq RESTART WITH 5;
