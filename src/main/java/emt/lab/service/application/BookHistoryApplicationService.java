package emt.lab.service.application;

import emt.lab.dto.display.DisplayBookHistoryDto;

import java.util.List;

public interface BookHistoryApplicationService {
    List<DisplayBookHistoryDto> findAllById(Long id);
}
