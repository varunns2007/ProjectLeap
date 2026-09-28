package com.example.greenlog.repository;

import com.example.greenlog.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
}