create table if not exists authors (
	id SERIAL PRIMARY KEY,
	alias VARCHAR(255),
	birth_date timestamp(6),
	century int4 NOT NULL,
	death_date TIMESTAMP(6),
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	middle_name VARCHAR(255)
);

create table if not exists books (
	id serial PRIMARY KEY,
	book_part OID,
	description VARCHAR(255),
	name VARCHAR(255) NOT NULL
);

create table if not exists users (
	id SERIAL PRIMARY KEY,
	account_created TIMESTAMP(6),
	birth_date timestamp(6),
	e_mail varchar(255) unique not null,
	first_name VARCHAR(255),
	last_name VARCHAR(255),
	middle_name VARCHAR(255),
	password varchar(255) not null,
	username varchar(255) not null
);

create table if not exists regals (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) UNIQUE not null,
	description VARCHAR(255),
	user_id int8 references users(id) not null
);
create table if not exists genres (
	id SERIAL PRIMARY KEY,
	name VARCHAR(255) UNIQUE not null,
	description VARCHAR(255)
);


create table if not exists books_and_authors_matches (
	book_id int8 references books(id) NOT NULL,
	author_id int8 references authors(id) NOT NULL,
	PRIMARY KEY (book_id, author_id)
);

create table if not exists books_and_genres_matches (
	book_id int8 references books(id) NOT NULL,
	genre_id int8 references genres(id) NOT NULL,
	PRIMARY KEY (book_id, genre_id)
);

create table if not exists books_and_regals_matches (
	book_id int8 references books(id) NOT NULL,
	regal_id int8 references regals(id) NOT NULL,
	PRIMARY KEY (book_id, regal_id)
);