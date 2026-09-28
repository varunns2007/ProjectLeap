package com.example.greenlog.service;

import com.example.greenlog.dto.VolunteerRequest;
import com.example.greenlog.entity.PlantationDrive;
import com.example.greenlog.entity.Volunteer;
import com.example.greenlog.exception.ResourceNotFoundException;
import com.example.greenlog.repository.PlantationDriveRepository;
import com.example.greenlog.repository.VolunteerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VolunteerService {

    private final VolunteerRepository volunteerRepository;
    private final PlantationDriveRepository driveRepository;

    public VolunteerService(VolunteerRepository volunteerRepository, PlantationDriveRepository driveRepository) {
        this.volunteerRepository = volunteerRepository;
        this.driveRepository = driveRepository;
    }

    @Transactional
    public Volunteer createVolunteer(Volunteer volunteer) {
        return volunteerRepository.save(volunteer);
    }

    @Transactional
    public Volunteer registerVolunteer(VolunteerRequest request) {
        Volunteer volunteer = new Volunteer();
        volunteer.setName(request.getName());
        volunteer.setEmail(request.getEmail());
        volunteer.setPhone(request.getPhone());
        return volunteerRepository.save(volunteer);
    }

    @Transactional(readOnly = true)
    public List<Volunteer> getAllVolunteers() {
        return volunteerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Volunteer getVolunteerById(Long id) {
        return volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + id));
    }

    @Transactional
    public Volunteer updateVolunteer(Long id, VolunteerRequest request) {
        Volunteer volunteer = getVolunteerById(id);
        volunteer.setName(request.getName());
        volunteer.setEmail(request.getEmail());
        volunteer.setPhone(request.getPhone());
        return volunteerRepository.save(volunteer);
    }

    @Transactional
    public Volunteer assignToDrive(Long volunteerId, Long driveId) {
        Volunteer volunteer = getVolunteerById(volunteerId);
        PlantationDrive drive = driveRepository.findById(driveId)
                .orElseThrow(() -> new ResourceNotFoundException("PlantationDrive not found with id: " + driveId));

        volunteer.setPlantationDrive(drive);
        return volunteerRepository.save(volunteer);
    }

    @Transactional
    public void deleteVolunteer(Long id) {
        Volunteer volunteer = getVolunteerById(id);
        volunteerRepository.delete(volunteer);
    }
}