create table if not exists hall
(
    id              int primary key,
    number_of_seats int
);

create table if not exists film
(
    id              serial primary key,
    title           varchar(255),
    year_of_release int,
    restrictions    varchar(255),
    description     varchar(255),
    poster          varchar(255)
);

create table if not exists session
(
    id          serial primary key,
    date        timestamp,
    hall_id     int,
    film_id     int,
    ticket_cost int8
);

alter table session
    add constraint FK_SESSION_HALL_ID foreign key (hall_id) references hall (id);

alter table session
    add constraint FK_SESSION_FILM_ID foreign key (film_id) references film (id);