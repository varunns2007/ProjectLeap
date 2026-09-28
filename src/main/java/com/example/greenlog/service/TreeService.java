package com.example.greenlog.service;

import com.example.greenlog.dto.TreeRequest;
import com.example.greenlog.entity.PlantationDrive;
import com.example.greenlog.entity.Tree;
import com.example.greenlog.entity.Volunteer;
import com.example.greenlog.exception.ResourceNotFoundException;
import com.example.greenlog.repository.PlantationDriveRepository;
import com.example.greenlog.repository.TreeRepository;
import com.example.greenlog.repository.VolunteerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TreeService {

    private final TreeRepository treeRepository;
    private final VolunteerRepository volunteerRepository;
    private final PlantationDriveRepository driveRepository;

    public TreeService(TreeRepository treeRepository,
                       VolunteerRepository volunteerRepository,
                       PlantationDriveRepository driveRepository) {
        this.treeRepository = treeRepository;
        this.volunteerRepository = volunteerRepository;
        this.driveRepository = driveRepository;
    }

    @Transactional
    public Tree createTree(TreeRequest request) {
        Tree tree = new Tree();
        tree.setSpecies(request.getSpecies());
        tree.setLocation(request.getLocation());
        tree.setDatePlanted(request.getDatePlanted() != null ? request.getDatePlanted() : LocalDate.now());
        tree.setStatus(request.getStatus());

        if (request.getVolunteerId() != null) {
            Volunteer volunteer = volunteerRepository.findById(request.getVolunteerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + request.getVolunteerId()));
            tree.setVolunteer(volunteer);
        }

        if (request.getPlantationDriveId() != null) {
            PlantationDrive drive = driveRepository.findById(request.getPlantationDriveId())
                    .orElseThrow(() -> new ResourceNotFoundException("PlantationDrive not found with id: " + request.getPlantationDriveId()));
            tree.setPlantationDrive(drive);
        }

        return treeRepository.save(tree);
    }

    @Transactional(readOnly = true)
    public List<Tree> getAllTrees() {
        return treeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Tree getTreeById(Long id) {
        return treeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tree not found with id: " + id));
    }

    @Transactional
    public Tree updateTree(Long id, TreeRequest request) {
        Tree tree = getTreeById(id);
        tree.setSpecies(request.getSpecies());
        tree.setLocation(request.getLocation());
        if (request.getDatePlanted() != null) {
            tree.setDatePlanted(request.getDatePlanted());
        }
        tree.setStatus(request.getStatus());

        if (request.getVolunteerId() != null) {
            Volunteer volunteer = volunteerRepository.findById(request.getVolunteerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + request.getVolunteerId()));
            tree.setVolunteer(volunteer);
        }

        if (request.getPlantationDriveId() != null) {
            PlantationDrive drive = driveRepository.findById(request.getPlantationDriveId())
                    .orElseThrow(() -> new ResourceNotFoundException("PlantationDrive not found with id: " + request.getPlantationDriveId()));
            tree.setPlantationDrive(drive);
        }

        return treeRepository.save(tree);
    }

    @Transactional
    public void deleteTree(Long id) {
        Tree tree = getTreeById(id);
        treeRepository.delete(tree);
    }
}