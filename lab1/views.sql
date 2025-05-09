create materialized view books_per_author as
    select a.id        as author_id,
       count(b.id) as num_books
    from author a
         left join book b on b.author_id = a.id
    group by a.id;

create materialized view authors_per_country as
    select c.id        as country_id,
       count(a.id) as num_authors
    from country c
         left join author a on a.country_id = c.id
    group by c.id;

-- create materialized view books_in_good_condition as
--        select b.id as id,
--               b.name as name,
--               b.category as category,
--               b.author_id as author_id,
--               b.available_copies as available_copies,
--               b.good_condition as good_condition
--        from book b
--        where b.good_condition = true;

CREATE MATERIALIZED VIEW books_in_good_condition AS
SELECT b.id AS id,
       b.name AS name,
       b.category AS category,
       b.author_id AS author_id,
       b.available_copies AS available_copies,
       b.good_condition AS good_condition
FROM book b
WHERE b.good_condition = true;