package com.example.greenlog.controller;

import com.example.greenlog.entity.Volunteer;
import com.example.greenlog.service.VolunteerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteers")
public class VolunteerController {

    private final VolunteerService service;

    public VolunteerController(VolunteerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Volunteer> createVolunteer(
            @RequestBody Volunteer volunteer) {

        Volunteer savedVolunteer =
                service.createVolunteer(volunteer);

        return new ResponseEntity<>(
                savedVolunteer,
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Volunteer>> getAllVolunteers() {

        return ResponseEntity.ok(
                service.getAllVolunteers()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Volunteer> getVolunteerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getVolunteerById(id)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVolunteer(
            @PathVariable Long id) {

        service.deleteVolunteer(id);

        return ResponseEntity.ok(
                "Volunteer deleted successfully"
        );
    }
}