package com.example.greenlog.controller;

import com.example.greenlog.dto.CheckInRequest;
import com.example.greenlog.entity.CheckIn;
import com.example.greenlog.service.CheckInService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {

    private final CheckInService checkInService;

    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @PostMapping
    public ResponseEntity<CheckIn> createCheckIn(@Valid @RequestBody CheckInRequest request) {
        return new ResponseEntity<>(checkInService.createCheckIn(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CheckIn>> getAllCheckIns() {
        return ResponseEntity.ok(checkInService.getAllCheckIns());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CheckIn> getCheckInById(@PathVariable Long id) {
        return ResponseEntity.ok(checkInService.getCheckInById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCheckIn(@PathVariable Long id) {
        checkInService.deleteCheckIn(id);
        return ResponseEntity.noContent().build();
    }
}