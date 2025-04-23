package emt.lab.service.application;

import emt.lab.dto.display.DisplayBooksByAuthorDto;

import java.util.List;

public interface BooksByAuthorApplicationService {
    List<DisplayBooksByAuthorDto> findAll();
}
