insert into hall (id, number_of_seats)
values (1, 10);

insert into film (title, year_of_release, restrictions, description)
values ('Бриллиантовая рука', 1969, '0+', 'Комедия');

insert into client (id, avatar_id)
VALUES ( '541d7d7a-006a-4ba1-a398-f556337af0e3', null );

insert into chat_message (film_id, created, author_id, message)
values (1, {ts '2012-09-17 18:47:52.69'}, '541d7d7a-006a-4ba1-a398-f556337af0e3', 'hello! how are you');