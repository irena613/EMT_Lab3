package mk.ukim.finki.emt.lab.model.jobs;

import mk.ukim.finki.emt.lab.service.aplication.AuthorApplicationService;
import mk.ukim.finki.emt.lab.service.aplication.BookApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private final AuthorApplicationService authorApplicationService;
    private final BookApplicationService bookApplicationService;

    public ScheduledTasks(AuthorApplicationService authorApplicationService, BookApplicationService bookApplicationService) {
        this.authorApplicationService = authorApplicationService;
        this.bookApplicationService = bookApplicationService;
    }


    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
//        this.authorApplicationService.refreshMaterializedView();
//        this.bookApplicationService.refreshMaterializedView();
    }
}

