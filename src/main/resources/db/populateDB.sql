DELETE FROM user_roles;
delete from meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

insert into meals (date_time, description, calories, user_id)
values ('2020-01-30 10:00:00', 'завтрак', '500', '100000'),
       ('2020-01-30 13:00:00', 'обед', '500', '100000'),
       ('2020-01-30 18:00:00', 'ужин', '500', '100000'),
       ('2020-01-31 10:00:00', 'Завтрак', '500', '100001'),
       ('2020-01-31 13:00:00', 'обед', '500', '100001'),
       ('2020-01-31 18:00:00', 'ужин', '500', '100001')
