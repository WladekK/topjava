DELETE FROM user_roles;
DELETE FROM users;
delete from meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

insert into meals (user_id, date_time, description, calories) values
  (100001, '19.12.1979', 'admin`s meal', 1000),
  (100000, '05.10.1981', 'user`s meal', 1500);
