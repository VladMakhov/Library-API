insert into authors (name, last_name, nationality, birth_year, birth_city, occupation)
values ('Leo', 'Tolstoy', 'Russian', 1828, 'Yasnaya Polyana', 'Writer'),
       ('Nikolai', 'Gogol', 'Ukrainian', 1809, 'Velyki Sorochyntsi', 'Novelist'),
       ('Alexander', 'Pushkin', 'Russian', 1799, 'Moscow', 'Poet'),
       ('Fyodor', 'Dostoevsky', 'Russian', 1821, 'Moscow', 'Novelist'),
       ('Mikhail', 'Lermontov', 'Russian', 1814, 'Moscow', 'Poet');

insert into books (book_name, year, author_id)
values ('War and Peace', 1867, 1),
       ('Anna Karenina', 1877, 1),
       ('Dead Souls', 1842, 2),
       ('Taras Bulba', 1835, 2),
       ('The Captains Daughter', 1836, 3),
       ('Dubrovsky', 1831, 3),
       ('Crime and Punishment', 1866, 4),
       ('Demons', 1872, 4),
       ('A Hero of Our Time', 1840, 5),
       ('Borodino', 1837, 5);

insert into reviews (description, stars, book_id)
values ('Best book ever', 5, 1),
       ('Recommend for every one', 5, 1),
       ('Not bad', 4, 2),
       ('I have red something better', 4, 2),
       ('Interesting book', 4, 3),
       ('Quite good', 5, 3),
       ('Not enough', 4, 4),
       ('Love it', 5, 4),
       ('Got to army because of this book', 5, 5),
       ('Good', 5, 5),
       ('Not bad', 4, 6),
       ('Recommend for every one', 5, 6),
       ('Made me think', 5, 7),
       ('So deep', 5, 7),
       ('Did not get it', 4, 8),
       ('Cool one', 5, 8),
       ('Big book, but interesting', 4, 9),
       ('Best book', 5, 9),
       ('Quite good', 5, 10),
       ('Best book', 4, 10);
