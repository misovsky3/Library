DROP TABLE IF EXISTS accounts CASCADE;

CREATE TABLE accounts (
                          id serial PRIMARY KEY,
                          name varchar,
                          surname varchar NOT NULL,
                          is_admin boolean DEFAULT FALSE,
                          valid_until timestamp NOT NULL
);

DROP TABLE IF EXISTS books CASCADE;

CREATE TABLE books (
                       id serial PRIMARY KEY,
                       title varchar NOT NULL,
                       author_name varchar,
                       author_surname varchar NOT NULL
);

DROP TABLE IF EXISTS categories CASCADE;

CREATE TABLE categories (
                            id serial PRIMARY KEY,
                            category varchar NOT NULL,
                            term int
);

DROP TABLE IF EXISTS warehouses CASCADE;

CREATE TABLE warehouses (
                            id serial PRIMARY KEY,
                            name varchar NOT NULL,
                            address varchar NOT NULL
);

DROP TABLE IF EXISTS copies CASCADE;

CREATE TABLE copies (
                        id serial PRIMARY KEY,
                        is_in_library boolean NOT NULL,
                        is_in_warehouse boolean NOT NULL,
                        copy_state int NOT NULL,
                        is_lendable boolean NOT NULL,

                        book_id int REFERENCES books,
                        category_id int REFERENCES categories,
                        warehouse_id int --REFERENCES warehouses
);

DROP TABLE IF EXISTS penalties CASCADE;

CREATE TABLE penalties (
                           id serial PRIMARY KEY,
                           delay integer,
                           is_damaged boolean,
                           amount numeric NOT NULL,
                           is_paid boolean,

                           account_id int REFERENCES accounts ON DELETE SET NULL
);

DROP TABLE IF EXISTS requests CASCADE;

CREATE TABLE requests (
                          id serial PRIMARY KEY,
                          date_from timestamp NOT NULL,
                          date_to timestamp NOT NULL,
                          is_rented boolean,

                          account_id int REFERENCES accounts ON DELETE SET NULL,
                          copy_id int REFERENCES copies
);

DROP TABLE IF EXISTS rentals CASCADE;

CREATE TABLE rentals (
                         id serial PRIMARY KEY,
                         date_from timestamp NOT NULL,
                         date_to timestamp NOT NULL,
                         is_returned boolean,

                         account_id int REFERENCES accounts ON DELETE SET NULL,
                         copy_id int REFERENCES copies
);
