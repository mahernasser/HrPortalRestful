package com.llun.error;


import jakarta.json.bind.annotation.JsonbDateFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record ErrorDetails(
        @JsonbDateFormat(value = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}