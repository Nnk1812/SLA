package com.example.task001.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SLAResponse {
    private LocalDateTime triggerTime;
    private String slaType;
    private BigDecimal slaValue;
    private LocalDateTime dueTime;
}
