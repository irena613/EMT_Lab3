package mk.ukim.finki.emt.lab.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table
@NoArgsConstructor
// @NamedEntityGraph(
//     name = "wishlist-with-books",
//     attributeNodes = {
//         @NamedAttributeNode("id"),
//         @NamedAttributeNode("dateCreated"),
//         @NamedAttributeNode("books")
//     }
// )
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToMany
    @JoinTable(
        name = "wishlist_books",
        joinColumns = @JoinColumn(name = "wishlist_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books = new ArrayList<>();

    @OneToOne
    private User user;

    public Wishlist(LocalDateTime dateCreated, List<Book> books, User user) {
        this.dateCreated = dateCreated;
        this.books = books;
        this.user = user;
    }
}
