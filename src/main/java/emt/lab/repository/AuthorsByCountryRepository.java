package emt.lab.repository;

import emt.lab.model.domain.AuthorsByCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorsByCountryRepository extends JpaRepository<AuthorsByCountry, Long> {
}
