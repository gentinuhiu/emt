package emt.lab.dto.create;

import emt.lab.model.domain.AuthLog;
import emt.lab.model.domain.Author;
import emt.lab.model.domain.Country;
import java.util.Date;
import java.time.LocalDateTime;

public record CreateAuthLogDto(
        String username,
        String token,
        Date expiration
) {
    public AuthLog toAuthLog(){
        return new AuthLog(username, token, expiration);
    }
}
