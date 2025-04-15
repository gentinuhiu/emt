package emt.lab.service.application;

import emt.lab.dto.create.CreateBookDto;
import emt.lab.dto.create.CreateCopyDto;
import emt.lab.dto.display.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {
    List<DisplayBookDto> findAll();
    Optional<DisplayBookDto> findById(Long id);
    Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto);
    void deleteById(Long id);
//    Optional<DisplayBookDto> rentBook(Long id);
    Optional<DisplayBookDto> save(CreateBookDto createBookDto);
//    Optional<DisplayBookDto> addCopy(Long id, CreateCopyDto createCopyDto);
//    Optional<DisplayBookDto> removeCopy(Long id, Long copyId);
}
