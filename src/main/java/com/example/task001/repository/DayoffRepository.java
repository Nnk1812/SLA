package com.example.task001.repository;

import com.example.task001.entity.Dayoff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DayoffRepository extends JpaRepository<Dayoff,Integer> {

}
