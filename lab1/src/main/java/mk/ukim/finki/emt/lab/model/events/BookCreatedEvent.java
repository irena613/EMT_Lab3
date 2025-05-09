package mk.ukim.finki.emt.lab.model.events;

import lombok.Getter;
import mk.ukim.finki.emt.lab.model.domain.Book;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class BookCreatedEvent extends ApplicationEvent {

    private final LocalDateTime when;

    public BookCreatedEvent(Book source) {
        super(source);
        this.when = LocalDateTime.now();
    }
}
