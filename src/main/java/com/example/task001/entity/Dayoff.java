package com.example.task001.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="day_off")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Dayoff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ref_id")
    private int refId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "year")
    private short year;

    @Column(name = "month", length = 10)
    private String month;

    @Column(name = "month_of_year")
    private byte monthOfYear;

    @Column(name = "day_of_month")
    private byte dayOfMonth;

    @Column(name = "day", length = 10)
    private String day;

    @Column(name = "day_of_week")
    private byte dayOfWeek;

    @Column(name = "weekend", length = 10)
    private String weekend;

    @Column(name = "day_of_year")
    private short dayOfYear;

    @Column(name = "week_of_year")
    private byte weekOfYear;

    @Column(name = "quarter")
    private byte quarter;

    @Column(name = "previous_day")
    private LocalDate previousDay;

    @Column(name = "next_day")
    private LocalDate nextDay;

    @Column(name = "is_deleted")
    private byte isDeleted;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;
}
