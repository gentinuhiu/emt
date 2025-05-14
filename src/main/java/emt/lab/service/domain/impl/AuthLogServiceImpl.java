package emt.lab.service.domain.impl;

import emt.lab.model.domain.AuthLog;
import emt.lab.repository.AuthLogRepository;
import emt.lab.service.domain.AuthLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthLogServiceImpl implements AuthLogService {
    private final AuthLogRepository authLogRepository;

    public AuthLogServiceImpl(AuthLogRepository authLogRepository) {
        this.authLogRepository = authLogRepository;
    }


    @Override
    public List<AuthLog> findAll() {
        return authLogRepository.findAll();
    }

    @Override
    public AuthLog save(AuthLog authLog) {
        return authLogRepository.save(authLog);
    }
}
