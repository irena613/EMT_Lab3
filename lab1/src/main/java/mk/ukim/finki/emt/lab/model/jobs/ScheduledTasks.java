package mk.ukim.finki.emt.lab.model.jobs;

import mk.ukim.finki.emt.lab.service.aplication.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.domain.AuthorService;
import mk.ukim.finki.emt.lab.service.domain.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final AuthorApplicationService authorApplicationService;

    public ScheduledTasks(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }


    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        this.authorApplicationService.refreshMaterializedView();
    }
}

