package emt.lab.service.application.impl;

import emt.lab.dto.create.CreateAuthLogDto;
import emt.lab.dto.display.DisplayAuthLogDto;
import emt.lab.service.application.AuthLogApplicationService;
import emt.lab.service.domain.AuthLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthLogApplicationServiceImpl implements AuthLogApplicationService {
    private final AuthLogService authLogService;

    public AuthLogApplicationServiceImpl(AuthLogService authLogService) {
        this.authLogService = authLogService;
    }

    @Override
    public List<DisplayAuthLogDto> findAll() {
        return DisplayAuthLogDto.from(authLogService.findAll());
    }

    @Override
    public DisplayAuthLogDto save(CreateAuthLogDto createAuthLogDto) {
        return DisplayAuthLogDto.from(authLogService.save(createAuthLogDto.toAuthLog()));
    }
}
