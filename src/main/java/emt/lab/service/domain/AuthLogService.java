package emt.lab.service.domain;

import emt.lab.model.domain.AuthLog;

import java.util.List;

public interface AuthLogService {
    List<AuthLog> findAll();
    AuthLog save(AuthLog authLog);
}
