package emt.lab.repository;

import emt.lab.model.domain.Author;
import emt.lab.model.projection.AuthorNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a.name AS name, a.surname AS surname FROM Author a")
    List<AuthorNameProjection> findAllAuthorNames();
}
