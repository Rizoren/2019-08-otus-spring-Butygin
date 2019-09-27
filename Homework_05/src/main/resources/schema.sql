create table if not exists books
(
	book_id bigserial not null
		constraint books_pk
			primary key,
	book_name varchar(255),
	book_description varchar(255),
	book_isbn varchar(17),
	book_year integer
);

create table if not exists genres
(
	genre_id bigserial not null
		constraint genres_pk
			primary key,
	genre_name varchar(255)
		constraint uk_genre_name
			unique
);

create table if not exists authors
(
	author_id bigserial not null
		constraint authors_pk
			primary key,
	author_family varchar(255),
	author_name varchar(255),
	author_patronymic varchar(255)
);

create table if not exists books_genres
(
	book_id bigint
		constraint books_genres_books_book_id_fk
			references books
				on delete cascade,
	genre_id bigint
		constraint books_genres_genres_genre_id_fk
			references genres
);


create table if not exists books_authors
(
	book_id bigint
		constraint books_authors_books_book_id_fk
			references books
				on delete cascade,
	author_id bigint
		constraint books_authors_authors_author_id_fk
			references authors
);
/*
create sequence books_book_id_seq;

create sequence genres_genre_id_seq;

create sequence authors_author_id_seq;
*/

