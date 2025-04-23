package emt.lab.repository;

import emt.lab.model.domain.BooksByAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksByAuthorRepository extends JpaRepository<BooksByAuthor, String> {
}
