package mk.ukim.finki.emt.lab.model.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emt.lab.model.enums.Category;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private Long availableCopies;

    @ColumnDefault("true")
    private Boolean goodCondition;

    public Book(String name, Category category, Author author, Long availableCopies, Boolean goodCondition) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        this.goodCondition = goodCondition;
    }

//    public Book(String name, Category category, Author author, Long availableCopies) {
//        this.name = name;
//        this.category = category;
//        this.availableCopies = availableCopies;
//        this.author = author;
//    }
}
