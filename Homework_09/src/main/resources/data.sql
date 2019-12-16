delete from tasks;
delete from users;

INSERT INTO users (user_id, user_name, user_password) VALUES (1, 'Ivan', null);
INSERT INTO users (user_id, user_name, user_password) VALUES (2, 'Petr', null);
ALTER SEQUENCE users_user_id_seq RESTART WITH 3;

INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (3, 'Go to supermarket', 'Buy anything', 1, '2019-10-28 06:23:39.954000', 1);
INSERT INTO tasks (task_id, task_name, task_description, task_status, task_enddate, user_id) VALUES (4, 'Walk', 'Ddvf fd fdfdbd fd fdbfb  fdfbdbf dfbfdbbfdbdf', 0, '2019-10-28 04:24:33.308000', 2);
ALTER SEQUENCE tasks_task_id_seq RESTART WITH 5;