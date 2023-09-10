drop table if exists reviews;
drop table if exists books;
drop table if exists authors;
create table if not exists authors
(
    id          bigint  not null auto_increment,
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
    id        bigint  not null auto_increment,
    book_name varchar(255),
    year      integer not null,
    author_id bigint,
    primary key (id)
);

create table if not exists reviews
(
    id          bigint  not null auto_increment,
    description varchar(255),
    stars       integer not null,
    book_id     bigint,
    primary key (id)
);

create table if not exists users
(
    id       integer not null auto_increment,
    password varchar(255),
    role     enum ('ADMIN','USER'),
    username varchar(255),
    primary key (id)
);

alter table books
    add constraint foreign key (author_id) references authors (id);
alter table reviews
    add constraint foreign key (book_id) references books (id);
