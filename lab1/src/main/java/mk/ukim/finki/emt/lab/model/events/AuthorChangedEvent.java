package mk.ukim.finki.emt.lab.model.events;

import mk.ukim.finki.emt.lab.model.domain.Author;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class AuthorChangedEvent extends ApplicationEvent {
    private final LocalDateTime when;

    public AuthorChangedEvent(Author source) {
        super(source);
        this.when = LocalDateTime.now();
    }

}