delete from tasks;
delete from users;

INSERT INTO users (user_id, user_name, user_password, user_uuid) VALUES (1, 'Ivan', null, '113b766d-18cb-453f-8841-7286fbab4b93');
INSERT INTO users (user_id, user_name, user_password, user_uuid) VALUES (2, 'Petr', null, 'b5d80a64-f52d-439e-b0c1-64d5d0a86c1d');
ALTER SEQUENCE users_user_id_seq RESTART WITH 3;

INSERT INTO tasks (task_id, task_name, task_description, task_status, task_uuid, user_uuid, task_enddate) VALUES (1, 'Go to supermarket', 'Buy anything', 1, '1da30d2b-0afb-442a-a0ce-ff67311e7f18', '113b766d-18cb-453f-8841-7286fbab4b93', '2019-10-28 06:23:39.954000');
INSERT INTO tasks (task_id, task_name, task_description, task_status, task_uuid, user_uuid, task_enddate) VALUES (2, 'Walk', 'Ddvf fd fdfdbd fd fdbfb  fdfbdbf dfbfdbbfdbdf', 0, 'daf1529b-bd42-4315-a863-f3b1bc69cbfd', 'b5d80a64-f52d-439e-b0c1-64d5d0a86c1d', '2019-10-28 04:24:33.308000');
ALTER SEQUENCE tasks_task_id_seq RESTART WITH 3;