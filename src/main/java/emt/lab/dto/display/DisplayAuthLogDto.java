package emt.lab.dto.display;

import emt.lab.model.domain.AuthLog;
import emt.lab.model.domain.Author;
import emt.lab.model.domain.Country;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*
    private Long id;
    private String username;
    private String token;
    private Date expiration;
    private LocalDateTime timestamp;
 */

public record DisplayAuthLogDto(
        Long id,
        String username,
        String token,
        Date expiration,
        LocalDateTime timestamp
) {
    public static DisplayAuthLogDto from(AuthLog authLog) {
        return new DisplayAuthLogDto(authLog.getId(), authLog.getUsername(), authLog.getToken(), authLog.getExpiration(), authLog.getTimestamp());
    }
    public static List<DisplayAuthLogDto> from(List<AuthLog> authLogs) {
        return authLogs.stream().map(DisplayAuthLogDto::from).collect(Collectors.toList());
    }
}
