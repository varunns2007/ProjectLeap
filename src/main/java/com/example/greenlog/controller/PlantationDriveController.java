package com.example.greenlog.controller;

import com.example.greenlog.dto.PlantationDriveRequest;
import com.example.greenlog.entity.PlantationDrive;
import com.example.greenlog.service.PlantationDriveService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class PlantationDriveController {

    private final PlantationDriveService service;

    public PlantationDriveController(
            PlantationDriveService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PlantationDrive> createDrive(
            @Valid @RequestBody PlantationDriveRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createDrive(request));
    }

    @GetMapping
    public ResponseEntity<List<PlantationDrive>> getAllDrives() {

        return ResponseEntity.ok(
                service.getAllDrives());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantationDrive> getDrive(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getDriveById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantationDrive> updateDrive(
            @PathVariable Long id,
            @Valid @RequestBody PlantationDriveRequest request) {

        return ResponseEntity.ok(
                service.updateDrive(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrive(
            @PathVariable Long id) {

        service.deleteDrive(id);

        return ResponseEntity.ok(
                "Plantation drive deleted successfully");
    }
}