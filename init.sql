-- Create ENUM-like values as text
-- (You can convert them to PostgreSQL ENUMs later if needed)

-- Country Table
CREATE TABLE country (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    continent VARCHAR(100)
);

-- Author Table
CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100),
    country_id INTEGER REFERENCES country(id)
);

-- Book Table
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    category VARCHAR(50), -- CATEGORY enum as VARCHAR
    author_id INTEGER REFERENCES author(id)
);

-- Book History Table
CREATE TABLE book_history (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    category VARCHAR(50), -- CATEGORY enum as VARCHAR
    author_id INTEGER REFERENCES author(id),
    book_id INTEGER REFERENCES book(id),
    stamp TIMESTAMP
);

-- Copy Table
CREATE TABLE copy (
    id SERIAL PRIMARY KEY,
    book_id INTEGER REFERENCES book(id) ON DELETE CASCADE,
    quality VARCHAR(50), -- QUALITY enum as VARCHAR
    rented BOOLEAN
);

-- Cart Table
CREATE TABLE cart (
    id SERIAL PRIMARY KEY
);

-- Many-to-Many table for Cart Reviews (Cart -> Book)
CREATE TABLE cart_review_books (
    cart_id INTEGER REFERENCES cart(id),
    book_id INTEGER REFERENCES book(id),
    PRIMARY KEY (cart_id, book_id)
);

-- One-to-Many Cart -> Copy (rented copies)
CREATE TABLE cart_rented_copies (
    cart_id INTEGER REFERENCES cart(id),
    copy_id INTEGER REFERENCES copy(id),
    PRIMARY KEY (cart_id, copy_id)
);

-- User Table (app_users)
CREATE TABLE app_users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    surname VARCHAR(100),
    is_account_non_expired BOOLEAN DEFAULT true,
    is_account_non_locked BOOLEAN DEFAULT true,
    is_credentials_non_expired BOOLEAN DEFAULT true,
    is_enabled BOOLEAN DEFAULT true,
    role VARCHAR(50), -- ROLE enum as VARCHAR
    cart_id INTEGER REFERENCES cart(id)
);

CREATE MATERIALIZED VIEW IF NOT EXISTS books_by_author AS
SELECT b.author_id, a.name, a.surname, COUNT(*) AS total_books
FROM book b
         JOIN author a ON a.id = b.author_id
GROUP BY b.author_id, a.name, a.surname;
