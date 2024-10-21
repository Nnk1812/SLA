package com.example.task001.controller;

import com.example.task001.dto.response.SLAResponse;
import com.example.task001.service.SLAService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/sla")
public class SLAController {
    @Autowired
    SLAService slaService;
    @PostMapping("/calculate")
    public SLAResponse calculateSLADueTime(
            @RequestParam("triggerTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime triggerTime,
            @RequestParam("slaType") String slaType,
            @RequestParam("slaValue") BigDecimal slaValue) {

       // Gọi hàm từ service để tính toán thời gian hết hạn SLA
        LocalDateTime dueTime = slaService.calculateSLADueTime(triggerTime, slaType, slaValue);

        // Trả về kết quả dưới dạng JSON
        return new SLAResponse(triggerTime, slaType, slaValue, dueTime);
    }
}
