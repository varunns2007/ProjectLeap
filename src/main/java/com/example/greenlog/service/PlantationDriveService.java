package com.example.greenlog.service;

import com.example.greenlog.dto.PlantationDriveRequest;
import com.example.greenlog.entity.PlantationDrive;
import com.example.greenlog.exception.ResourceNotFoundException;
import com.example.greenlog.repository.PlantationDriveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantationDriveService {

    private final PlantationDriveRepository repository;

    public PlantationDriveService(
            PlantationDriveRepository repository) {
        this.repository = repository;
    }

    public PlantationDrive createDrive(
            PlantationDriveRequest request) {

        PlantationDrive drive = new PlantationDrive();

        drive.setName(request.getName());
        drive.setLocation(request.getLocation());
        drive.setDate(request.getDate());

        return repository.save(drive);
    }

    public List<PlantationDrive> getAllDrives() {
        return repository.findAll();
    }

    public PlantationDrive getDriveById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Plantation drive not found with ID: "
                                        + id));
    }

    public PlantationDrive updateDrive(
            Long id,
            PlantationDriveRequest request) {

        PlantationDrive drive = getDriveById(id);

        drive.setName(request.getName());
        drive.setLocation(request.getLocation());
        drive.setDate(request.getDate());

        return repository.save(drive);
    }

    public void deleteDrive(Long id) {

        PlantationDrive drive = getDriveById(id);

        repository.delete(drive);
    }
}