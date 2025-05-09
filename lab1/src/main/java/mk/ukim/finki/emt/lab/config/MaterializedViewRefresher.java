package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.repository.views.AuthorsPerCountryViewRepository;
import mk.ukim.finki.emt.lab.repository.views.BooksPerAuthorViewRepository;
import mk.ukim.finki.emt.lab.repository.views.BooksInGoodConditionViewRepository;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MaterializedViewRefresher {
    private static final Logger logger = LoggerFactory.getLogger(MaterializedViewRefresher.class);
    
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;
    private final BooksInGoodConditionViewRepository booksInGoodConditionViewRepository;
    private final JdbcTemplate jdbcTemplate;

    public MaterializedViewRefresher(
            BooksPerAuthorViewRepository booksPerAuthorViewRepository, 
            AuthorsPerCountryViewRepository authorsPerCountryViewRepository,
            BooksInGoodConditionViewRepository booksInGoodConditionViewRepository,
            JdbcTemplate jdbcTemplate) {
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
        this.booksInGoodConditionViewRepository = booksInGoodConditionViewRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        try {
            // Check if views exist before refreshing
            boolean booksPerAuthorExists = checkViewExists("books_per_author");
            boolean authorsPerCountryExists = checkViewExists("authors_per_country");
            boolean booksInGoodConditionExists = checkViewExists("books_in_good_condition");
            
            if (booksPerAuthorExists) {
                booksPerAuthorViewRepository.refreshMaterializedView();
                logger.info("Refreshed books_per_author materialized view");
            } else {
                logger.warn("books_per_author materialized view does not exist, skipping refresh");
            }
            
            if (authorsPerCountryExists) {
                authorsPerCountryViewRepository.refreshMaterializedView();
                logger.info("Refreshed authors_per_country materialized view");
            } else {
                logger.warn("authors_per_country materialized view does not exist, skipping refresh");
            }

            if (booksInGoodConditionExists) {
                booksInGoodConditionViewRepository.refreshMaterializedView();
                logger.info("Refreshed books_in_good_condition materialized view");
            }
//            else {
//                createBooksInGoodConditionView();
//                logger.info("Created and refreshed books_in_good_condition materialized view");
//            }
        } catch (Exception e) {
            logger.error("Error refreshing materialized views", e);
        }
    }
    
    private boolean checkViewExists(String viewName) {
        try {
            jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM pg_matviews WHERE matviewname = ?",
                Integer.class,
                viewName
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    private void createBooksInGoodConditionView() {
//        try {
//            jdbcTemplate.execute("""
//                CREATE MATERIALIZED VIEW public.books_in_good_condition AS
//                SELECT b.id AS id,
//                       b.name AS name,
//                       b.category AS category,
//                       b.author_id AS author_id,
//                       b.available_copies AS available_copies,
//                       b.good_condition AS good_condition
//                FROM book b
//                WHERE b.good_condition = true
//                """);
//            booksInGoodConditionViewRepository.refreshMaterializedView();
//        } catch (Exception e) {
//            logger.error("Error creating books_in_good_condition materialized view", e);
//            throw new RuntimeException("Failed to create books_in_good_condition materialized view", e);
//        }
//    }
}