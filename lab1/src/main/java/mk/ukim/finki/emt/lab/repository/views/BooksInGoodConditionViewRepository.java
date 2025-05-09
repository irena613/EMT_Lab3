package mk.ukim.finki.emt.lab.repository.views;

import mk.ukim.finki.emt.lab.model.view.BooksInGoodConditionView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BooksInGoodConditionViewRepository extends JpaRepository<BooksInGoodConditionView, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.books_in_good_condition", nativeQuery = true)
    void refreshMaterializedView();
}