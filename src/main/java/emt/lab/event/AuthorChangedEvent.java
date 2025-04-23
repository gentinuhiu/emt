package emt.lab.event;

import emt.lab.model.domain.Author;
import org.springframework.context.ApplicationEvent;

public class AuthorChangedEvent extends ApplicationEvent {
    public AuthorChangedEvent(Author author) {
        super(author);
    }
}
