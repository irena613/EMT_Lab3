-- Create materialized views if they don't exist
DO $$
BEGIN
    -- Check if books_per_author view exists
    IF NOT EXISTS (SELECT 1 FROM pg_matviews WHERE matviewname = 'books_per_author') THEN
        CREATE MATERIALIZED VIEW books_per_author AS
        SELECT a.id        AS author_id,
               count(b.id) AS num_books
        FROM author a
                 LEFT JOIN book b ON b.author_id = a.id
        GROUP BY a.id;
    END IF;

    -- Check if authors_per_country view exists
    IF NOT EXISTS (SELECT 1 FROM pg_matviews WHERE matviewname = 'authors_per_country') THEN
        CREATE MATERIALIZED VIEW authors_per_country AS
        SELECT c.id        AS country_id,
               count(a.id) AS num_authors
        FROM country c
                 LEFT JOIN author a ON a.country_id = c.id
        GROUP BY c.id;
    END IF;
END $$; 