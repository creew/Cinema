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
    poster_id       uuid
);

create table if not exists session
(
    id          serial primary key,
    date        timestamp without time zone,
    hall_id     int,
    film_id     int,
    ticket_cost int8
);

create table if not exists file_description
(
    id           uuid primary key,
    filename     varchar(255),
    size         int8,
    content_type varchar(255)
);

create table if not exists client
(
    id        uuid primary key,
    avatar_id uuid
);

create table if not exists chat_message
(
    id        bigserial primary key,
    film_id   int,
    created   timestamp,
    author_id uuid,
    message   varchar(1000)
);

create table if not exists login_info
(
    id         bigserial primary key,
    login_time timestamp,
    ip         varchar(255),
    user_id    uuid
);

alter table session
    add constraint FK_SESSION_HALL_ID foreign key (hall_id) references hall (id);

alter table session
    add constraint FK_SESSION_FILM_ID foreign key (film_id) references film (id);

alter table film
    add constraint FK_FILM_POSTER_ID foreign key (poster_id) references file_description (id);

alter table client
    add constraint FK_USER_AVATAR_ID foreign key (avatar_id) references file_description (id);

alter table chat_message
    add constraint FK_CHAT_MESSAGE_FILM_ID foreign key (film_id) references film (id);

alter table chat_message
    add constraint FK_CHAT_MESSAGE_AUTHOR_ID foreign key (author_id) references client (id);

alter table login_info
    add constraint FK_LOGIN_INFO_USER_ID foreign key (user_id) references client (id);