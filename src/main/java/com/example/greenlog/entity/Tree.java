package com.example.greenlog.entity;

import com.example.greenlog.enums.SurvivalStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trees")
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String species;

    private String location;

    private LocalDate datePlanted;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurvivalStatus status = SurvivalStatus.ALIVE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drive_id")
    @JsonIgnoreProperties({"trees", "volunteers"})
    private PlantationDrive plantationDrive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "volunteer_id")
    @JsonIgnoreProperties({"plantationDrive", "trees"})
    private Volunteer volunteer;

    @OneToMany(mappedBy = "tree", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("tree")
    private List<CheckIn> checkIns = new ArrayList<>();

    public Tree() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getDatePlanted() { return datePlanted; }
    public void setDatePlanted(LocalDate datePlanted) { this.datePlanted = datePlanted; }

    public SurvivalStatus getStatus() { return status; }
    public void setStatus(SurvivalStatus status) { this.status = status; }

    public PlantationDrive getPlantationDrive() { return plantationDrive; }
    public void setPlantationDrive(PlantationDrive plantationDrive) { this.plantationDrive = plantationDrive; }

    public Volunteer getVolunteer() { return volunteer; }
    public void setVolunteer(Volunteer volunteer) { this.volunteer = volunteer; }

    public List<CheckIn> getCheckIns() { return checkIns; }
    public void setCheckIns(List<CheckIn> checkIns) { this.checkIns = checkIns; }
}