package emt.lab.config;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViewRefresher {

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(cron = "0 0 * * * *") // Every hour
    @Transactional
    public void refreshView() {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW books_by_author").executeUpdate();
        System.out.println("Materialized view refreshed!");
    }
}

