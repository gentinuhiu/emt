package emt.lab.service.application;

import emt.lab.dto.create.CreateAuthLogDto;
import emt.lab.dto.display.DisplayAuthLogDto;
import emt.lab.dto.display.DisplayAuthorDto;

import java.util.List;

public interface AuthLogApplicationService {
    List<DisplayAuthLogDto> findAll();
    DisplayAuthLogDto save(CreateAuthLogDto createAuthLogDto);
}
