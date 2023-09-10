insert into authors (id, name, last_name, nationality, birth_year, birth_city, occupation)
values (1, 'Alexander', 'Pushkin', 'Russian', 1799, 'Moscow', 'Poet'),
       (2, 'Nikolai', 'Gogol', 'Ukrainian', 1809, 'Velyki Sorochyntsi', 'Novelist'),
       (3, 'Fyodor', 'Dostoevsky', 'Russian', 1821, 'Moscow', 'Novelist'),
       (4, 'Leo', 'Tolstoy', 'Russian', 1828, 'Yasnaya Polyana', 'Writer'),
       (5, 'Mikhail', 'Lermontov', 'Russian', 1814, 'Moscow', 'Poet');

insert into books (id, book_name, year, author_id)
values (1, 'War and Peace', 1867, 1),
       (2, 'Anna Karenina', 1877, 1),
       (3, 'Dead Souls', 1842, 2),
       (4, 'Taras Bulba', 1835, 2),
       (5, 'The Captains Daughter', 1836, 3),
       (6, 'Dubrovsky', 1831, 3),
       (7, 'Crime and Punishment', 1866, 4),
       (8, 'Demons', 1872, 4),
       (9, 'A Hero of Our Time', 1840, 5),
       (10, 'Borodino', 1837, 5);

insert into reviews (id, description, stars, book_id)
values (1, 'Best book ever', 5, 1),
       (2, 'Recommend for every one', 5, 1),
       (3, 'Not bad', 4, 2),
       (4, 'I have red something better', 4, 2),
       (5, 'Interesting book', 4, 3),
       (6, 'Quite good', 5, 3),
       (7, 'Not enough', 4, 4),
       (8, 'Love it', 5, 4),
       (9, 'Got to army because of this book', 5, 5),
       (10, 'Good', 5, 5),
       (11, 'Not bad', 4, 6),
       (12, 'Recommend for every one', 5, 6),
       (13, 'Made me think', 5, 7),
       (14, 'So deep', 5, 7),
       (15, 'Did not get it', 4, 8),
       (16, 'Cool one', 5, 8),
       (17, 'Big book, but interesting', 4, 9),
       (18, 'Best book', 5, 9),
       (19, 'Quite good', 5, 10),
       (20, 'Best book', 4, 10);
