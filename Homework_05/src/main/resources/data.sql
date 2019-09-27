delete from books_authors;
delete from books_genres;
delete from authors;
delete from books;
delete from genres;

INSERT INTO authors (author_id, author_family, author_name, author_patronymic) VALUES (1, 'Пушкин', 'Александр', 'Сергеевич');
INSERT INTO authors (author_id, author_family, author_name, author_patronymic) VALUES (2, 'Лермонтов', 'Михаил', 'Юрьевич');
INSERT INTO authors (author_id, author_family, author_name, author_patronymic) VALUES (3, 'Роулинг', 'Джоан', null);
ALTER SEQUENCE authors_author_id_seq RESTART WITH 3;

INSERT INTO genres (genre_id, genre_name) VALUES (1, 'поэма');
INSERT INTO genres (genre_id, genre_name) VALUES (2, 'русская сказка');
INSERT INTO genres (genre_id, genre_name) VALUES (3, 'фэнтези');
ALTER SEQUENCE genres_genre_id_seq RESTART WITH 3;

INSERT INTO books (book_id, book_name, book_description, book_isbn, book_year) VALUES (1, 'Руслан и Людмила', 'первая законченная поэма Александра Сергеевича Пушкина; волшебная сказка, вдохновлённая древнерусскими былинами', '000-0-00-000001-0', 1820);
INSERT INTO books (book_id, book_name, book_description, book_isbn, book_year) VALUES (2, 'Демон', 'поэма Михаила Юрьевича Лермонтова, над которой поэт работал в течение десяти лет - с 1829 по 1839 год', '000-0-00-000002-0', 1856);
INSERT INTO books (book_id, book_name, book_description, book_isbn, book_year) VALUES (3, 'Гарри Поттер и философский камень', 'первый роман в серии книг про юного волшебника Гарри Поттера, написанный Дж. К. Роулинг', '000-0-00-000003-0', 1997);
ALTER SEQUENCE books_book_id_seq RESTART WITH 3;

INSERT INTO books_authors (book_id, author_id) VALUES (1, 1);
INSERT INTO books_authors (book_id, author_id) VALUES (2, 2);
INSERT INTO books_authors (book_id, author_id) VALUES (3, 3);

INSERT INTO books_genres (book_id, genre_id) VALUES (1, 1);
INSERT INTO books_genres (book_id, genre_id) VALUES (1, 2);
INSERT INTO books_genres (book_id, genre_id) VALUES (2, 1);
INSERT INTO books_genres (book_id, genre_id) VALUES (3, 3);