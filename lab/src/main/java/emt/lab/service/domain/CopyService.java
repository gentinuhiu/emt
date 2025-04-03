package emt.lab.service.domain;

import emt.lab.model.domain.Author;
import emt.lab.model.domain.Copy;
import emt.lab.repository.CopyRepository;

import java.util.List;
import java.util.Optional;

public interface CopyService {
    List<Copy> findAll();
    Optional<Copy> findById(Long id);
    Optional<Copy> update(Long id, Copy copy);
    Optional<Copy> save(Copy copy);
    void deleteById(Long id);
}
