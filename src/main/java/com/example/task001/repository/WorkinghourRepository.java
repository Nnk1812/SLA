package com.example.task001.repository;

import com.example.task001.entity.Workinghour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkinghourRepository extends JpaRepository<Workinghour, Long> {
}
