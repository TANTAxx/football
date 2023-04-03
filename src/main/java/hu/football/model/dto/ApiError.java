package hu.football.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private LocalDateTime timestamp;
    private String message;

    private Integer status;

    public ApiError(LocalDateTime timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }
}

