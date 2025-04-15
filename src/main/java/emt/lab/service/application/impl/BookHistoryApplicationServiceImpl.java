package emt.lab.service.application.impl;

import emt.lab.dto.display.DisplayBookHistoryDto;
import emt.lab.service.application.BookHistoryApplicationService;
import emt.lab.service.domain.BookHistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookHistoryApplicationServiceImpl implements BookHistoryApplicationService {
    private final BookHistoryService bookHistoryService;

    public BookHistoryApplicationServiceImpl(BookHistoryService bookHistoryService) {
        this.bookHistoryService = bookHistoryService;
    }

    @Override
    public List<DisplayBookHistoryDto> findAllById(Long id) {
        return DisplayBookHistoryDto.from(bookHistoryService.findAllById(id));
    }
}
