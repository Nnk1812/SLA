package com.example.task001.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="working_hour")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workinghour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    @Column(name = "shift")
    private byte shift;

    @Column(name = "start")
    private LocalTime start;

    @Column(name = "start_integer")
    private int startInteger;

    @Column(name = "end")
    private LocalTime end;

    @Column(name = "end_integer")
    private int endInteger;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_by", length = 100)
    private String updatedBy;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "is_active")
    private byte isActive;

    @Column(name = "is_deleted")
    private byte isDeleted;

}
