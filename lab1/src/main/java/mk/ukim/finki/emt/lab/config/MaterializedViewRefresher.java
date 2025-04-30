package mk.ukim.finki.emt.lab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emt.lab.repository.views.AuthorsPerCountryViewRepository;
import mk.ukim.finki.emt.lab.repository.views.BooksPerAuthorViewRepository;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MaterializedViewRefresher {
    private static final Logger logger = LoggerFactory.getLogger(MaterializedViewRefresher.class);
    
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;
    private final JdbcTemplate jdbcTemplate;

    public MaterializedViewRefresher(
            BooksPerAuthorViewRepository booksPerAuthorViewRepository, 
            AuthorsPerCountryViewRepository authorsPerCountryViewRepository,
            JdbcTemplate jdbcTemplate) {
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        try {
            // Check if views exist before refreshing
            boolean booksPerAuthorExists = checkViewExists("books_per_author");
            boolean authorsPerCountryExists = checkViewExists(" ");
            
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
}