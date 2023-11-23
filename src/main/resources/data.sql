delete
from user;
delete
from authority;
delete
from user_authority;
delete
from event;

insert into authority (authority_name)
values ('ROLE_ADMIN'),
       ('ROLE_USER');

insert into user (username, password)
values ('admin', '$2a$12$idvVNoEhmDFHg9/my0c.juxD77hEID6AX1bc0YqRJXdpuNvuJE9b2'),
       ('user', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('John', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Mathew', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Claire', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Thia', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Guldar', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Carridin', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Pirin', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq'),
       ('Evendur', '$2a$12$zO0CNe/dZKlE13Kn4pntcuImw624HPvfyknHIO9R03..OYhuSKxMq');


insert into user_authority (user_id, authority_id)
values (1, 1),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 2),
       (8, 2),
       (9, 2),
       (10, 2);

insert into event (creator_id, title, location, date, event_details, creation_date)
values (2, 'D&D night', 'Zagreb', '2022-09-26T18:00:00', 'Roll initiative', '2022-05-02'),
       (5, 'Experimental Techno', 'Rijeka', '2022-10-10T22:00:00', 'Upad 50 kn, klub Život', '2022-05-02'),
       (7, 'Beach party', 'Lovran', '2022-10-31T16:00:00', 'Vrijeme je za kupanje', '2022-05-02'),
        (4, '"2HG prerelease dominaria united"', 'Zagreb', '2022-10-31T16:00:00', 'upad 200kn/30€', '2022-05-02'),
        (7, 'Turski Psychopop', 'Zagreb', '2022-12-25T20:00:00', 'Karte ne možete još pokupiti', '2022-05-02'),
        (1, 'Okupljanje sk8era', 'Zagreb', '2022-10-31T16:00:00', 'On je bio sk8er dečko, rekla sam vidimo se poslije dečko - nije bio dovoljno dobar za mene', '2022-05-02'),
        (6, 'Nova Godina', 'Zagreb', '2022-12-31T23:59:59', 'Više nemam ideja ovo je zadnji event', '2022-05-02');


insert into message_group (name)
values ('Grupa #1'),
       ('Grupa #2');

insert into user_group (user_id, group_id)
values (1, 1),
       (2, 2),
       (1, 2),
       (2, 1);

insert into message (creator_id, message_body, create_date, recipient_id)
values (1, 'Mogao sam stavit lorem ipsum', '2022-01-01T15:01:17', 2),
       (2, 'Trebao si', '2022-01-02T17:22:19', 1),
       (1, 'Ovo stvarno radi', '2022-01-02T18:43:11', 2),
       (1, 'Test', '2022-01-02T19:34:13', 3),
       (1, 'Tko bi rekao', '2022-01-03T10:12:14', 2),
       (1, '12345', '2022-01-03T21:52:15', 3),
       (3, 'Mogao sam stavit lorem ipsum', '2022-01-03T22:25:18', 2),
       (3, 'old test message', '2022-01-04T06:14:11', 1),
       (3, 'old test message', '2022-01-04T07:34:12', 2),
       (3, 'old test message', '2022-01-04T23:12:13', 2);

insert into message (creator_id, message_body, create_date, recipient_group_id)
values (1, 'Hey yall', '2022-01-01T15:27:10', 1),
       (2, 'test group message od usera', '2022-01-01T15:32:14', 1),
       (1, '2. test group message', '2022-01-02T15:43:21', 2),
       (3, '2. test group message od usera', '2022-01-02T15:55:51', 2),
       (4, 'Bok moje ime je Matthew', '2022-01-02T15:16:56', 1),
       (2, '2. test group message', '2022-01-02T15:23:58', 2),
       (3, '2. test group message od usera', '2022-01-03T15:52:12', 2);;

insert into comment (event_id, creator_id, comment_body, creation_date)
values (1, 1, 'o da ovo je komentar tuturutu', '2022-01-01T15:27:10'),
       (2, 2, 'pipurutu', '2022-01-01T15:27:11'),
       (3, 3, 'tuUUUUUUUUUU', '2022-01-01T15:27:12')