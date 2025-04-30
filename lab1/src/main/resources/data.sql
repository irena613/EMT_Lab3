-- Drop existing materialized views if they exist
DROP MATERIALIZED VIEW IF EXISTS books_per_author;
DROP MATERIALIZED VIEW IF EXISTS authors_per_country;

-- Create materialized views
CREATE MATERIALIZED VIEW books_per_author AS
SELECT a.id        AS author_id,
       count(b.id) AS num_books
FROM author a
         LEFT JOIN book b ON b.author_id = a.id
GROUP BY a.id;

CREATE MATERIALIZED VIEW authors_per_country AS
SELECT c.id        AS country_id,
       count(a.id) AS num_authors
FROM country c
         LEFT JOIN author a ON a.country_id = c.id
GROUP BY c.id; 