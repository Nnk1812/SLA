package com.example.task001.controller;

import com.example.task001.entity.Dayoff;
import com.example.task001.service.DayoffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/dayoff")
public class DayoffController {
    @Autowired
    private DayoffService dayoffService;
    @GetMapping
    List<Dayoff> findall(){
        return dayoffService.getDayoff();
    }
}
