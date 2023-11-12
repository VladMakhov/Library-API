drop table if exists reviews;
drop table if exists books;
drop table if exists authors;
drop table if exists users;

create table if not exists authors
(
    id          serial,
    birth_city  varchar(255),
    birth_year  integer not null,
    last_name   varchar(255),
    name        varchar(255),
    nationality varchar(255),
    occupation  varchar(255),
    primary key (id)
);

create table if not exists books
(
    id        serial,
    book_name varchar(255),
    year      integer not null,
    author_id bigint,
    primary key (id),
    FOREIGN KEY(author_id)
    REFERENCES authors(id)
);

create table if not exists reviews
(
    id          serial,
    description varchar(255),
    stars       integer not null,
    book_id     bigint,
    primary key (id),
    FOREIGN KEY(book_id)
    REFERENCES books(id)
);

create table if not exists users
(
    id       serial,
    password varchar(255),
    role     varchar(255),
    username varchar(255),
    primary key (id)
);

alter table if exists authors alter column id set data type bigint;
alter table if exists books alter column id set data type bigint;
alter table if exists reviews alter column id set data type bigint;