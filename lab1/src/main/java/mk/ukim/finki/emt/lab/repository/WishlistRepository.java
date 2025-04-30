package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.domain.Book;
import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mk.ukim.finki.emt.lab.model.domain.Wishlist;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByUserUsername(String username);
}
