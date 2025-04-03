package emt.lab.service.application;

import emt.lab.dto.create.CreateBookDto;
import emt.lab.dto.create.CreateCopyDto;
import emt.lab.dto.display.DisplayCopyDto;
import emt.lab.model.domain.Copy;

import java.util.List;
import java.util.Optional;

public interface CopyApplicationService {
    List<DisplayCopyDto> findAll();
    Optional<DisplayCopyDto> findById(Long id);
    Optional<DisplayCopyDto> update(Long id, CreateCopyDto copy);
    Optional<DisplayCopyDto> save(CreateCopyDto copy);
    void deleteById(Long id);
}
