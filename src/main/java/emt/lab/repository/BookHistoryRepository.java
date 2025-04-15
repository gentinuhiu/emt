package emt.lab.repository;

import emt.lab.model.domain.Book;
import emt.lab.model.domain.BookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookHistoryRepository extends JpaRepository<BookHistory, Long> {
    List<BookHistory> findAllByBookId(Long id);
}
