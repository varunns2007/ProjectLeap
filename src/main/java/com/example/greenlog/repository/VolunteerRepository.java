package com.example.greenlog.repository;

import com.example.greenlog.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VolunteerRepository
        extends JpaRepository<Volunteer, Long> {
}