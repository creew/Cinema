insert into hall (id, number_of_seats)
values (1, 10);

insert into file_description (id, filename, size, content_type)
values ( '11111111-1111-1111-1111-111111111111', 'unknown.jpeg', 38463, 'image/jpeg'),
       ( '22222222-2222-2222-2222-222222222222', 'mib.jpeg', 15511, 'image/jpeg');

insert into film (title, year_of_release, restrictions, description, poster_id)
values ('Бриллиантовая рука', 1969, '0+', 'Комедия', null),
       ('Люди в черном', 1997, '12+', 'Комедия', '22222222-2222-2222-2222-222222222222');

insert into client (id, session_id, avatar_id)
values (1, '541d7d7a-006a-4ba1-a398-f556337af0e3', null );

insert into chat_message (film_id, created, author_id, message)
values (1, {ts '2012-09-17 18:47:52.69'}, 1, 'hello! how are you');

insert into session (date, hall_id, film_id, ticket_cost)
values (CURRENT_TIMESTAMP(), 1, 1, 100 ),
       (CURRENT_TIMESTAMP(), 1, 2, 100 );
