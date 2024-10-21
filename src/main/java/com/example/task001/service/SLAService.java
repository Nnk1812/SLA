package com.example.task001.service;

import com.example.task001.repository.DayoffRepository;
import com.example.task001.repository.WorkinghourRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SLAService {
    DayoffRepository dayoffRepository;
    WorkinghourRepository workinghourRepository;
    private static final LocalTime MORNING_START = LocalTime.of(8, 0);
    private static final LocalTime MORNING_END = LocalTime.of(12, 0);
    private static final LocalTime AFTERNOON_START = LocalTime.of(13, 30);
    private static final LocalTime AFTERNOON_END = LocalTime.of(17, 30);
    private List<LocalDateTime> holidays;

    // Hàm tính thời gian hết hạn SLA
    public LocalDateTime calculateSLADueTime(LocalDateTime triggerTime, String slaType, BigDecimal slaValue) {
        LocalDateTime dueTime = triggerTime;

        if (slaType.equalsIgnoreCase("hour")) {
            dueTime = addWorkingHours(dueTime, slaValue);
        } else if (slaType.equalsIgnoreCase("day")) {
            dueTime = addWorkingDays(dueTime, slaValue);
        }

        return dueTime;
    }

    // Hàm cộng giờ làm việc
    private LocalDateTime addWorkingHours(LocalDateTime dateTime, BigDecimal hoursToAdd) {
        BigDecimal remainingHours = hoursToAdd;

        while (remainingHours.compareTo(BigDecimal.ZERO) > 0) {
            if (isWorkingDay(dateTime)) {
                LocalTime time = dateTime.toLocalTime();

                // Nếu thời gian nằm trong khoảng buổi sáng
                if (isWithinWorkingHours(time, MORNING_START, MORNING_END)) {
                    BigDecimal availableMorningHours = BigDecimal.valueOf(ChronoUnit.MINUTES.between(time, MORNING_END) / 60.0);
                    if (remainingHours.compareTo(availableMorningHours) <= 0) {
                        return dateTime.plusMinutes(remainingHours.multiply(BigDecimal.valueOf(60)).longValue());
                    }
                    dateTime = dateTime.with(MORNING_END);
                    remainingHours = remainingHours.subtract(availableMorningHours);
                }

                // Nếu thời gian nằm trong khoảng buổi chiều
                if (isWithinWorkingHours(time, AFTERNOON_START, AFTERNOON_END)) {
                    BigDecimal availableAfternoonHours = BigDecimal.valueOf(ChronoUnit.MINUTES.between(time, AFTERNOON_END) / 60.0);
                    if (remainingHours.compareTo(availableAfternoonHours) <= 0) {
                        return dateTime.plusMinutes(remainingHours.multiply(BigDecimal.valueOf(60)).longValue());
                    }
                    dateTime = dateTime.with(AFTERNOON_END);
                    remainingHours = remainingHours.subtract(availableAfternoonHours);
                }
            }

            // Chuyển sang ngày làm việc tiếp theo
            dateTime = nextWorkingDay(dateTime);
            dateTime = dateTime.with(MORNING_START); // Reset thời gian về buổi sáng
        }

        return dateTime;
    }

    // Hàm cộng ngày làm việc
    private LocalDateTime addWorkingDays(LocalDateTime dateTime, BigDecimal daysToAdd) {
        int daysToAddInt = daysToAdd.intValue();
        while (daysToAddInt > 0) {
            dateTime = nextWorkingDay(dateTime);
            daysToAddInt--;
        }
        return dateTime.with(MORNING_START); // Bắt đầu từ 8h sáng của ngày làm việc tiếp theo
    }

    // Kiểm tra nếu ngày là ngày làm việc
    private boolean isWorkingDay(LocalDateTime dateTime) {
        return !(dateTime.getDayOfWeek().getValue() >= 6 || holidays.contains(dateTime.toLocalDate()));
    }

    // Kiểm tra nếu thời gian nằm trong giờ làm việc
    private boolean isWithinWorkingHours(LocalTime time, LocalTime start, LocalTime end) {
        return !time.isBefore(start) && !time.isAfter(end);
    }

    // Chuyển sang ngày làm việc tiếp theo
    private LocalDateTime nextWorkingDay(LocalDateTime dateTime) {
        do {
            dateTime = dateTime.plusDays(1);
        } while (!isWorkingDay(dateTime));
        return dateTime;
    }

}
