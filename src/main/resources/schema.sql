-- 1. Create the materialized view
CREATE MATERIALIZED VIEW IF NOT EXISTS books_by_author AS
SELECT b.author_id, a.name, a.surname, COUNT(*) AS total_books
FROM book b
         JOIN author a ON a.id = b.author_id
GROUP BY b.author_id, a.name, a.surname;


-- 2. Optional: Create a refresh function
-- CREATE OR REPLACE FUNCTION refresh_books_by_author_view() RETURNS void AS $$
-- BEGIN
--     REFRESH MATERIALIZED VIEW books_by_author;
-- END;
-- $$ LANGUAGE plpgsql;
