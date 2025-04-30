package mk.ukim.finki.emt.lab.model.listeners;

import mk.ukim.finki.emt.lab.model.events.AuthorChangedEvent;
import mk.ukim.finki.emt.lab.model.events.AuthorCreatedEvent;
import mk.ukim.finki.emt.lab.model.events.AuthorDeletedEvent;
import mk.ukim.finki.emt.lab.service.aplication.CountryApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.CountryService;
import org.springframework.context.event.EventListener;

public class AuthorEventHandler {
    private final CountryApplicationService countryApplicationService;

    public AuthorEventHandler(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @EventListener
    public void onAuthorCreated(AuthorCreatedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorDeleted(AuthorDeletedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        this.countryApplicationService.refreshMaterializedView();
    }
}
