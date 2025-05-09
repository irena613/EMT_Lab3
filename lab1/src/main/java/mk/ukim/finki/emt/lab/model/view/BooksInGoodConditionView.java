package mk.ukim.finki.emt.lab.model.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM books_in_good_condition")
@Immutable
public class BooksInGoodConditionView {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "available_copies")
    private Long availableCopies;

    @Column(name = "good_condition")
    private boolean goodCondition;
} 