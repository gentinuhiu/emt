package emt.lab.listeners;

import emt.lab.event.AuthorChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AuthorEventListener {

    private final JdbcTemplate jdbcTemplate;

    public AuthorEventListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener
    public void onAuthorChanged(AuthorChangedEvent event) {
        jdbcTemplate.execute("REFRESH MATERIALIZED VIEW authors_by_country");
    }
}
