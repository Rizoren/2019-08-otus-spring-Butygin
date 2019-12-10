delete from tasks;
delete from users;

INSERT INTO users (user_id, user_name, user_password) VALUES (1, 'User1', 'User1');
INSERT INTO users (user_id, user_name, user_password) VALUES (2, 'User2', 'User2');
ALTER SEQUENCE users_user_id_seq RESTART WITH 3;

INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (1, 'Task1', 'Task1', 1, '2000-01-01 00:00:00.000000', 1);
INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (2, 'Task2', 'Task2', 0, '2000-01-01 00:00:00.000000', 1);
INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (3, 'Task3', 'Task3', 1, '2000-01-01 00:00:00.000000', 2);
INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (4, 'Task4', 'Task4', 0, '2000-01-01 00:00:00.000000', 2);
ALTER SEQUENCE tasks_task_id_seq RESTART WITH 5;