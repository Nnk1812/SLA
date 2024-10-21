package com.example.task001.service;

import com.example.task001.entity.Dayoff;
import com.example.task001.repository.DayoffRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DayoffService {
    DayoffRepository dayoffRepository;
    public List<Dayoff> getDayoff() {
       return dayoffRepository.findAll();
    }
}
