package com.example.greenlog.service;

import com.example.greenlog.dto.CheckInRequest;
import com.example.greenlog.entity.CheckIn;
import com.example.greenlog.entity.Tree;
import com.example.greenlog.entity.Volunteer;
import com.example.greenlog.enums.SurvivalStatus;
import com.example.greenlog.exception.ResourceNotFoundException;
import com.example.greenlog.exception.TreeAlreadyDeadException;
import com.example.greenlog.repository.CheckInRepository;
import com.example.greenlog.repository.TreeRepository;
import com.example.greenlog.repository.VolunteerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckInService {

    private final CheckInRepository checkInRepository;
    private final TreeRepository treeRepository;
    private final VolunteerRepository volunteerRepository;

    public CheckInService(CheckInRepository checkInRepository,
                          TreeRepository treeRepository,
                          VolunteerRepository volunteerRepository) {
        this.checkInRepository = checkInRepository;
        this.treeRepository = treeRepository;
        this.volunteerRepository = volunteerRepository;
    }

    @Transactional
    public CheckIn createCheckIn(CheckInRequest request) {
        Tree tree = treeRepository.findById(request.getTreeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tree not found with id: " + request.getTreeId()));

        if (tree.getStatus() == SurvivalStatus.DEAD) {
            throw new TreeAlreadyDeadException("Cannot record a check-in for a tree that is already marked DEAD.");
        }

        CheckIn checkIn = new CheckIn();
        checkIn.setTree(tree);
        checkIn.setCheckInDate(LocalDate.now());
        checkIn.setStatusRecorded(request.getStatus());

        if (request.getVolunteerId() != null) {
            Volunteer volunteer = volunteerRepository.findById(request.getVolunteerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Volunteer not found with id: " + request.getVolunteerId()));
            checkIn.setVolunteer(volunteer);
        }

        tree.setStatus(request.getStatus());
        treeRepository.save(tree);

        return checkInRepository.save(checkIn);
    }

    @Transactional(readOnly = true)
    public List<CheckIn> getAllCheckIns() {
        return checkInRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CheckIn getCheckInById(Long id) {
        return checkInRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CheckIn not found with id: " + id));
    }

    @Transactional
    public void deleteCheckIn(Long id) {
        CheckIn checkIn = getCheckInById(id);
        checkInRepository.delete(checkIn);
    }
}