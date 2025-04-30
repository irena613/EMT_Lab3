package mk.ukim.finki.emt.lab.repository;
//

import mk.ukim.finki.emt.lab.model.domain.User;
import mk.ukim.finki.emt.lab.model.enums.Role;
import mk.ukim.finki.emt.lab.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    @EntityGraph(value = "user-without-wishlist")
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(value = "user-with-wishlist")
    @Query("select u from User u where u.username = :username")
    Optional<User> findByUsernameWithWishlist(@Param("username") String username);

    @EntityGraph(value = "user-without-wishlist")
    @Query("select u from User u")
    List<User> loadAll();

    UserProjection findByRole(Role role);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

}




