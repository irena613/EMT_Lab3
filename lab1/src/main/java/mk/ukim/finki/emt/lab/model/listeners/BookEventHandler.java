package mk.ukim.finki.emt.lab.model.listeners;

import mk.ukim.finki.emt.lab.model.events.AuthorChangedEvent;
import mk.ukim.finki.emt.lab.model.events.AuthorCreatedEvent;
import mk.ukim.finki.emt.lab.model.events.AuthorDeletedEvent;
import mk.ukim.finki.emt.lab.model.events.BookCreatedEvent;
import mk.ukim.finki.emt.lab.service.aplication.BookApplicationService;
import mk.ukim.finki.emt.lab.service.aplication.CountryApplicationService;
import org.springframework.context.event.EventListener;

public class BookEventHandler {

        private final BookApplicationService bookApplicationService;

    public BookEventHandler(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
    }


    @EventListener
        public void onBookCreated(BookCreatedEvent event) {
            this.bookApplicationService.refreshMaterializedView();
        }
//        @EventListener
//        public void onBookDeleted(BookDeletedEvent event) {
//            this.bookApplicationService.refreshMaterializedView();
//        }
//        @EventListener
//        public void onBookChanged(BookChangedEvent event) {
//            this.bookApplicationService.refreshMaterializedView();
//        }

}
